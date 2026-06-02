package entity;
 
/**
 * Entidade Missao: ciclo de vida: criada → ativa → finalizada.
 * Status reflete o diagrama de estados do Drone.
 */
public class Missao {
 
    private final int id;
    private final String tipo;
    private String status;
 
    public Missao(int id, String tipo) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID da missão deve ser positivo.");
        }
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("Tipo da missão não pode ser vazio.");
        }
        this.id = id;
        this.tipo = tipo;
        this.status = "criada";
    }
 
    public void iniciar() {
        if (!"criada".equals(status)) {
            throw new IllegalStateException("Missão só pode ser iniciada a partir do estado 'criada'.");
        }
        this.status = "ativa";
        System.out.printf("[MISSAO #%d] Iniciada. Tipo: %s%n", id, tipo);
    }
 
    public void finalizar() {
        if (!"ativa".equals(status)) {
            throw new IllegalStateException("Missão só pode ser finalizada a partir do estado 'ativa'.");
        }
        this.status = "finalizada";
        System.out.printf("[MISSAO #%d] Finalizada.%n", id);
    }
 
    public int getId()       { return id; }
    public String getTipo()  { return tipo; }
    public String getStatus() { return status; }
}
