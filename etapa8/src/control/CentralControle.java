import java.util.*;

public class CentralControle implements IComandar, IAutenticavel, IRegistravel {
    private final int id;
    private final Autenticacao auth;
    private final ComunicacaoSegura comm;
    private final BancoDadosDistribuido bd;
    private final LogAuditoria log;
    private final List<Drone> drones = new ArrayList<>();

    public CentralControle(int id, Autenticacao auth, ComunicacaoSegura comm,
                           BancoDadosDistribuido bd, LogAuditoria log) {
        if (id <= 0) throw new IllegalArgumentException("ID inválido.");
        this.id = id; this.auth = auth; this.comm = comm; this.bd = bd; this.log = log;
    }

    @Override public void enviarComando(String cmd) {
        if (cmd == null || cmd.isBlank()) throw new IllegalArgumentException("Comando vazio.");
        System.out.printf("[CENTRAL #%d] Cmd: %s%n", id, cmd);
        String enc = comm.criptografarDados(cmd);
        drones.forEach(d -> comm.transmitirDados(enc, d.getId()));
        registrar("Comando enviado: " + cmd);
    }

    @Override public boolean autenticar(String token) {
        boolean ok = auth.autenticar(token);
        registrar(ok ? "MFA bem-sucedido." : "Falha MFA.");
        return ok;
    }

    @Override public void registrar(String evento) {
        if (evento != null && !evento.isBlank()) log.persistirLog(evento);
    }

    public void executarMissao(Drone drone, Missao missao) {
        System.out.printf("[CENTRAL #%d] Iniciando missão #%d → drone #%d%n",
                id, missao.getId(), drone.getId());
        registrar("Missão #" + missao.getId() + " iniciada.");
        drone.executarMissao();
    }

    public void registrarDadosMissao(String chave, String valor) {
        bd.armazenarDados(chave, valor);
        registrar("Dados registrados: " + chave);
    }

    public void salvarLogFinal(String missaoId) {
        bd.armazenarDados("missao_" + missaoId + "_log", "concluida");
        bd.replicarDados();
        registrar("Log final missão " + missaoId + " salvo.");
    }

    public void visualizarTelemetria() {
        System.out.printf("[CENTRAL #%d] Telemetria:%n", id);
        drones.forEach(Drone::enviarTelemetria);
    }

    public void adicionarDrone(Drone d) {
        drones.add(d);
        System.out.printf("[CENTRAL #%d] Drone #%d adicionado.%n", id, d.getId());
    }
}
