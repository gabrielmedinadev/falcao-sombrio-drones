package service;
 
import entity.Missao;
import model.Drone;
 
/**
 * DroneAquila: implementação concreta do Drone Aquila-X.
 *
 * Implementa executarMissao() conforme o contrato abstrato.
 * Ciclo de vida reflete o diagrama de estados:
 *   Ocioso → Em preparo → Em missão → (condição crítica) → Retornando → Finalizado
 */
public class DroneAquila extends Drone {
 
    private Missao missaoAtual;
    private final SistemaNavegacao sistemaNavegacao;
 
    public DroneAquila(int id, float nivelBateria, SistemaNavegacao sistemaNavegacao) {
        super(id, nivelBateria);
        if (sistemaNavegacao == null) {
            throw new IllegalArgumentException("SistemaNavegacao não pode ser nulo.");
        }
        this.sistemaNavegacao = sistemaNavegacao;
    }
 
    /**
     * Implementação do contrato abstrato.
     * Segue o fluxo do diagrama de sequência:
     *   executarMissao() → calcularRota() → [loop telemetria] → finalizarMissao()
     */
    @Override
    public void executarMissao() {
        if (missaoAtual == null) {
            throw new IllegalStateException("Nenhuma missão atribuída ao drone #" + id);
        }
 
        // Em preparo
        this.status = "em_preparo";
        System.out.printf("[DRONE #%d] Preparando missão #%d...%n", id, missaoAtual.getId());
 
        // Calcular rota
        String rota = sistemaNavegacao.calcularRota("BASE", "ALVO_" + missaoAtual.getId());
        System.out.printf("[DRONE #%d] Rota definida: %s%n", id, rota);
 
        // Em missão
        this.status = "em_missao";
        missaoAtual.iniciar();
 
        // Loop de telemetria (simulado — 3 ciclos)
        for (int ciclo = 1; ciclo <= 3; ciclo++) {
            enviarTelemetria();
            consumirBateria(10.0f);
 
            // Condição crítica: otimizar consumo se bateria < 20%
            if (monitorarBateria() < 20.0f) {
                this.status = "condicao_critica";
                otimizarConsumoEnergia();
            }
        }
 
        // Finalizar missão
        missaoAtual.finalizar();
        retornarBase();
    }
 
    public void atribuirMissao(Missao missao) {
        if (missao == null) throw new IllegalArgumentException("Missão não pode ser nula.");
        this.missaoAtual = missao;
        System.out.printf("[DRONE #%d] Missão #%d atribuída.%n", id, missao.getId());
    }
 
    private void otimizarConsumoEnergia() {
        System.out.printf("[DRONE #%d] Otimizando consumo de energia...%n", id);
        // reduz consumo nos próximos ciclos (simulação)
    }
 
    private void retornarBase() {
        this.status = "retornando";
        System.out.printf("[DRONE #%d] Retornando à base...%n", id);
        this.status = "finalizado";
        System.out.printf("[DRONE #%d] Missão concluída. Drone na base.%n", id);
    }
}
