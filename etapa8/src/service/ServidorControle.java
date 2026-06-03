public class ServidorControle {
    private String status;
    private final BancoDadosDistribuido bancoDados;

    public ServidorControle(BancoDadosDistribuido bancoDados) {
        this.status = "ativo";
        this.bancoDados = bancoDados;
    }

    public void verificarConectividade() {
        System.out.printf("[SERVIDOR_CONTROLE] Status: %s%n", status);
        if (!"ativo".equals(bancoDados.getStatus())) {
            System.out.println("[SERVIDOR_CONTROLE] BD indisponível — acionando protocolo de fallback.");
            this.status = "fallback";
        }
    }

    public String getStatus() { return status; }
}
