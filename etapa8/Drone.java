package model;
 
import interfaces.IMonitoravel;
import interfaces.IAtualizavel;
 
/**
 * Classe abstrata Drone: núcleo comum da frota Aquila-X.
 *
 * Invariantes de domínio (validadas no construtor):
 *   - id > 0
 *   - nivelBateria > 0
 *
 * executarMissao() é abstrato: cada tipo de drone detalha como executa.
 * enviarTelemetria() é concreto: comportamento comum a todos.
 */
public abstract class Drone implements IMonitoravel, IAtualizavel {
 
    protected final int id;
    protected String status;
    protected float nivelBateria;
 
    private static final float BATERIA_CRITICA = 20.0f;
 
    public Drone(int id, float nivelBateria) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do drone deve ser positivo.");
        }
        if (nivelBateria <= 0) {
            throw new IllegalArgumentException("Nível de bateria deve ser maior que zero.");
        }
        this.id = id;
        this.nivelBateria = nivelBateria;
        this.status = "inativo";
    }
 
    // ── Método abstrato: cada subclasse implementa ─────────────
    public abstract void executarMissao();
 
    // ── Comportamento comum ────────────────────────────────────
    public void enviarTelemetria() {
        System.out.printf("[TELEMETRIA] Drone #%d | status=%s | bateria=%.1f%%%n",
                id, status, nivelBateria);
    }
 
    // ── IMonitoravel ───────────────────────────────────────────
    @Override
    public float monitorarBateria() {
        if (nivelBateria < BATERIA_CRITICA) {
            System.out.printf("[ALERTA] Drone #%d — bateria crítica: %.1f%%%n",
                    id, nivelBateria);
        }
        return nivelBateria;
    }
 
    // ── IAtualizavel ───────────────────────────────────────────
    @Override
    public void atualizarFirmware(String versao) {
        if (versao == null || versao.isBlank()) {
            throw new IllegalArgumentException("Versão do firmware não pode ser nula ou vazia.");
        }
        System.out.printf("[FIRMWARE] Drone #%d atualizado para versão %s%n", id, versao);
    }
 
    // ── Getters (sem expor setters desnecessários) ─────────────
    public int getId()           { return id; }
    public String getStatus()    { return status; }
    public float getNivelBateria() { return nivelBateria; }
 
    // consumo simulado durante missão
    protected void consumirBateria(float consumo) {
        this.nivelBateria = Math.max(0, this.nivelBateria - consumo);
    }
}
