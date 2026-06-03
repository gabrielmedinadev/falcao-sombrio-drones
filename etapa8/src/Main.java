public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA FALCÃO SOMBRIO — Securus Dynamics ===\n");

        System.out.println("--- [0] VERIFICANDO CONEXÃO COM SUPABASE ---");
        ConexaoDB.inicializarTabelas();

        // DAOs
        InfraAutorizaOperadoresDAO infraAutorizaDAO = new InfraAutorizaOperadoresDAO();
        InfraGerenciaDronesDAO     infraGerenciaDAO = new InfraGerenciaDronesDAO();
        InfraestruturaControleDAO  infraDAO         = new InfraestruturaControleDAO();
        SensoresDAO          sensoresDAO    = new SensoresDAO();
        ComunicacaoSeguraDAO comunicacaoDAO = new ComunicacaoSeguraDAO();
        AutenticacaoDAO      autenticacaoDAO = new AutenticacaoDAO();
        OperadorDAO          operadorDAO    = new OperadorDAO();
        DroneDAO             droneDAO       = new DroneDAO();
        MissaoDAO            missaoDAO      = new MissaoDAO();
        TelemetriaDAO        telemetriaDAO  = new TelemetriaDAO();
        LogAuditoriaDAO      logDAO         = new LogAuditoriaDAO();
        NavegacaoIADAO       navegacaoDAO   = new NavegacaoIADAO();

        // Registra servidor principal no Supabase
        String servidorPrincipalId = infraDAO.registrarServidor("Principal", "ativo");

        // Infraestrutura
        ServidorBackup        backup  = new ServidorBackup();
        BancoDadosDistribuido bd      = new BancoDadosDistribuido(backup, logDAO);
        LogAuditoria          log     = new LogAuditoria(logDAO);
        Autenticacao          auth    = new Autenticacao();
        ComunicacaoSegura     comm    = new ComunicacaoSegura("TLS-1.3");
        CentralControle       central = new CentralControle(1, auth, comm, bd, log);
        ServidorControle servidorControle = new ServidorControle(bd);

        // Navegação
        IA_Navegacao     ia  = new IA_Navegacao(navegacaoDAO);
        SistemaNavegacao nav = new SistemaNavegacao(ia);

        // Sensores
        Sensor lidar  = new Lidar();
        Sensor camera = new Camera();
        Sensor gps    = new GPS();

        // Drone
        DroneAquila drone = new DroneAquila(42, 100, "Aquila-X-001", "Aquila-X",
                nav, droneDAO, telemetriaDAO);
        central.adicionarDrone(drone);

        if (servidorPrincipalId != null && drone.getDbId() != null)
            infraGerenciaDAO.vincular(servidorPrincipalId, drone.getDbId());

        // Operador
        Operador operador = new Operador(1, "Rodrigo", "Administrador", auth, operadorDAO);

        if (servidorPrincipalId != null && operador.getDbId() != null)
            infraAutorizaDAO.autorizar(servidorPrincipalId, operador.getDbId());

        System.out.println("\n--- [1] AUTENTICAÇÃO ---");
        boolean autenticado = operador.autenticarCompleto("operador01", "123456");
        autenticacaoDAO.registrar(operador.getDbId(), true, autenticado);
        if (!autenticado) { System.out.println("Acesso negado. Encerrando."); return; }
        log.setContexto(null, drone.getDbId());

        System.out.println("\n--- [2] SENSORES ---");
        lidar.coletarDados();
        sensoresDAO.registrar(drone.getDbId(), "Lidar", "operacional",
                "{\"tipo\": \"Lidar\", \"dados\": \"mapa_3d\", \"unidade\": \"metros\"}");
        camera.coletarDados();
        sensoresDAO.registrar(drone.getDbId(), "Camera", "operacional",
                "{\"tipo\": \"Camera\", \"dados\": \"imagem_capturada\", \"resolucao\": \"4K\"}");
        gps.coletarDados();
        sensoresDAO.registrar(drone.getDbId(), "GPS", "operacional",
                "{\"tipo\": \"GPS\", \"dados\": \"coordenadas\", \"precisao\": \"2m\"}");

        System.out.println("\n--- [3] COMANDO ---");
        central.enviarComando("IniciarMissao");
        comunicacaoDAO.registrar(drone.getDbId(), "TLS-1.3");

        System.out.println("\n--- [4] MISSÃO ---");
        Missao missao = new Missao(1, "reconhecimento", missaoDAO);
        missao.registrarNoBanco(drone.getDbId(), operador.getDbId());
        log.setContexto(missao.getDbId(), drone.getDbId());
        drone.atribuirMissao(missao);
        operador.iniciarMissao(central, drone, missao);

        System.out.println("\n--- [5] FIRMWARE ---");
        drone.atualizarFirmware("2.1.0");

        System.out.println("\n--- [6] LOG FINAL ---");
        central.registrarDadosMissao("missao_resultado", "sucesso");
        operador.finalizarMissao(central, missao.getDbId());

        System.out.println("\n--- [7] TELEMETRIA ---");
        central.visualizarTelemetria();

        System.out.println("\n--- [8] FALLBACK ---");
        servidorControle.verificarConectividade();
        bd.simularFalha();
        servidorControle.verificarConectividade();
        infraDAO.assumirControle(servidorPrincipalId);
        central.registrarDadosMissao("missao_1_failover", "emergencia");

        System.out.println("\n--- [9] LOGS MEMÓRIA ---");
        log.getRegistros().forEach(System.out::println);

        System.out.println("\n--- [10] LOGS SUPABASE ---");
        logDAO.listarTodos();

        System.out.println("\n=== FIM ===");
    }
}
