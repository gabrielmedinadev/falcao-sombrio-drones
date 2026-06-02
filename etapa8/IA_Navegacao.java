package service;
 
/**
 * IA_Navegacao: processa dados e toma decisões de rota otimizada.
 * Diagrama de sequência: 2.2.1 processarDados() + 2.2.2 tomarDecisao().
 */
public class IA_Navegacao {
 
    public String processarDados(String dadosBrutos) {
        if (dadosBrutos == null || dadosBrutos.isBlank()) {
            throw new IllegalArgumentException("Dados brutos não podem ser nulos.");
        }
        System.out.printf("[IA] Processando dados: %s%n", dadosBrutos);
        return "DADOS_PROCESSADOS[" + dadosBrutos + "]";
    }
 
    public String tomarDecisao(String dadosProcessados) {
        if (dadosProcessados == null) {
            throw new IllegalArgumentException("Dados processados não podem ser nulos.");
        }
        String rotaOtimizada = "ROTA_OTIMIZADA[" + dadosProcessados.hashCode() + "]";
        System.out.printf("[IA] Decisão tomada: %s%n", rotaOtimizada);
        return rotaOtimizada;
    }
}
