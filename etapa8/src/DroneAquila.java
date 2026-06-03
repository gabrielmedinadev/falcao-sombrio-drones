public class DroneAquila extends Drone {
    private Missao missaoAtual;
    private final SistemaNavegacao nav;

    public DroneAquila(int id, int nivelBateria, String nome, String modelo,
                       SistemaNavegacao nav, DroneDAO droneDAO, TelemetriaDAO telemetriaDAO) {
        super(id, nivelBateria, nome, modelo, droneDAO, telemetriaDAO);
        if (nav == null) throw new IllegalArgumentException("Nav nulo.");
        this.nav = nav;
    }

    @Override
    public void executarMissao() {
        if (missaoAtual == null)
            throw new IllegalStateException("Nenhuma missão atribuída ao drone #" + id);

        nav.setDroneId(dbId);
        this.status = "em_missao";
        if (dbId != null) droneDAO.atualizarStatus(dbId, status, nivelBateria);
        System.out.printf("[DRONE #%d] Preparando missão #%d...%n", id, missaoAtual.getId());

        String rota = nav.calcularRota("BASE", "ALVO_" + missaoAtual.getId());
        System.out.printf("[DRONE #%d] Rota: %s%n", id, rota);

        missaoAtual.iniciar();

        for (int ciclo = 1; ciclo <= 3; ciclo++) {
            enviarTelemetria();
            consumirBateria(10);
            if (nivelBateria < 20 && dbId != null) {
                droneDAO.atualizarStatus(dbId, "critico", nivelBateria);
            }
        }

        missaoAtual.finalizar();

        this.status = "manutencao";
        if (dbId != null) droneDAO.atualizarStatus(dbId, status, nivelBateria);
        System.out.printf("[DRONE #%d] Retornando à base...%n", id);

        this.status = "disponivel";
        if (dbId != null) droneDAO.atualizarStatus(dbId, status, nivelBateria);
        System.out.printf("[DRONE #%d] Missão concluída.%n", id);
    }

    public void atribuirMissao(Missao missao) {
        if (missao == null) throw new IllegalArgumentException("Missão nula.");
        this.missaoAtual = missao;
        System.out.printf("[DRONE #%d] Missão #%d atribuída.%n", id, missao.getId());
    }
}
