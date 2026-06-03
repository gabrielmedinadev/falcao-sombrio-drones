public class IA_Navegacao {
    private NavegacaoIADAO navegacaoDAO;
    private String droneIdAtual = null;

    public IA_Navegacao(NavegacaoIADAO navegacaoDAO) {
        this.navegacaoDAO = navegacaoDAO;
    }

    public void setDroneId(String droneId) { this.droneIdAtual = droneId; }

    public String processarDados(String dados) {
        System.out.printf("[IA] Processando: %s%n", dados);
        return "PROC[" + dados + "]";
    }

    public String tomarDecisao(String proc) {
        String rota = "{\"id\": " + proc.hashCode() + ", \"origem\": \"BASE\", \"status\": \"otimizada\"}";
        System.out.printf("[IA] Rota otimizada: %s%n", rota);
        if (droneIdAtual != null) {
            navegacaoDAO.salvar(droneIdAtual, "RedeNeural-v2", "rota_otimizada", rota);
        }
        return rota;
    }
}
