public class Missao {
    private final int id;
    private final String tipo;
    private String status;
    private final MissaoDAO missaoDAO;
    private String dbId = null;

    public Missao(int id, String tipo, MissaoDAO missaoDAO) {
        if (id <= 0) throw new IllegalArgumentException("ID inválido.");
        if (tipo == null || tipo.isBlank()) throw new IllegalArgumentException("Tipo vazio.");
        this.id = id; this.tipo = tipo; this.status = "criada"; this.missaoDAO = missaoDAO;
    }

    public void registrarNoBanco(String droneDbId, String operadorDbId) {
        this.dbId = missaoDAO.criar(droneDbId, operadorDbId, tipo);
    }

    public void iniciar() {
        if (!"criada".equals(status)) throw new IllegalStateException("Estado inválido.");
        this.status = "ativa";
        System.out.printf("[MISSAO #%d] Iniciada. Tipo: %s%n", id, tipo);
        if (dbId != null) missaoDAO.iniciar(dbId);
    }

    public void finalizar() {
        if (!"ativa".equals(status)) throw new IllegalStateException("Estado inválido.");
        this.status = "finalizada";
        System.out.printf("[MISSAO #%d] Finalizada.%n", id);
        if (dbId != null) missaoDAO.finalizar(dbId);
    }

    public int getId()        { return id; }
    public String getDbId()   { return dbId; }
    public String getTipo()   { return tipo; }
    public String getStatus() { return status; }
}
