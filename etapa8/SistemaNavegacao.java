package service;
 
/**
 * SistemaNavegacao: calcula rotas e detecta ameaças.
 * Usa IA_Navegacao para otimização da rota (diagrama de sequência: 2.2.1).
 */
public class SistemaNavegacao {
 
    private final IA_Navegacao ia;
 
    public SistemaNavegacao(IA_Navegacao ia) {
        if (ia == null) throw new IllegalArgumentException("IA_Navegacao não pode ser nula.");
        this.ia = ia;
    }
 
    /**
     * Calcula rota otimizada para a missão.
     * Diagrama de sequência: 2.2 calcularRota() → 2.2.1 processarDados()
     */
    public String calcularRota(String origem, String destino) {
        if (origem == null || destino == null) {
            throw new IllegalArgumentException("Origem e destino não podem ser nulos.");
        }
        System.out.printf("[NAV] Calculando rota: %s → %s%n", origem, destino);
        String dadosProcessados = ia.processarDados(origem + "-" + destino);
        return ia.tomarDecisao(dadosProcessados);
    }
 
    /** Detecta ameaças no trajeto via sensores LIDAR/câmeras/GPS. */
    public boolean detectarAmeacas(String regiao) {
        if (regiao == null || regiao.isBlank()) return false;
        System.out.printf("[NAV] Verificando ameaças na região: %s%n", regiao);
        // Simulação: sem ameaças detectadas
        return false;
    }
}
