public abstract class Drone implements IMonitoravel, IAtualizavel {
    protected final int id;
    protected String status;
    protected int nivelBateria;
    private static final int BATERIA_CRITICA = 20;

    protected DroneDAO droneDAO;
    protected TelemetriaDAO telemetriaDAO;
    protected String dbId = null;

    public Drone(int id, int nivelBateria, String nome, String modelo,
                 DroneDAO droneDAO, TelemetriaDAO telemetriaDAO) {
        if (id <= 0)           throw new IllegalArgumentException("ID inválido.");
        if (nivelBateria <= 0) throw new IllegalArgumentException("Bateria deve ser > 0.");
        this.id = id; this.nivelBateria = nivelBateria;
        this.status = "inativo";
        this.droneDAO = droneDAO;
        this.telemetriaDAO = telemetriaDAO;
        this.dbId = droneDAO.inserir(nome, modelo, nivelBateria);
    }

    public abstract void executarMissao();

    public void enviarTelemetria() {
        System.out.printf("[TELEMETRIA] Drone #%d | %s | %d%%%n", id, status, nivelBateria);
        if (dbId != null) telemetriaDAO.salvar(dbId, status, nivelBateria);
    }

    @Override
    public float monitorarBateria() {
        if (nivelBateria < BATERIA_CRITICA)
            System.out.printf("[ALERTA] Drone #%d — bateria crítica: %d%%%n", id, nivelBateria);
        return nivelBateria;
    }

    @Override
    public void atualizarFirmware(String versao) {
        if (versao == null || versao.isBlank()) throw new IllegalArgumentException("Versão inválida.");
        System.out.printf("[FIRMWARE] Drone #%d → versão %s%n", id, versao);
        if (dbId != null) droneDAO.atualizarStatus(dbId, status, nivelBateria);
    }

    protected void consumirBateria(int consumo) {
        nivelBateria = Math.max(0, nivelBateria - consumo);
        if (nivelBateria < 20 && !"critico".equals(status)) {
            status = "critico";
        }
    }

    public int getId()             { return id; }
    public String getDbId()        { return dbId; }
    public String getStatus()      { return status; }
    public float getNivelBateria() { return nivelBateria; }
}
