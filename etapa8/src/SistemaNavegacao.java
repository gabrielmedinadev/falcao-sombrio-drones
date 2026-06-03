public class SistemaNavegacao {
    private final IA_Navegacao ia;

    public SistemaNavegacao(IA_Navegacao ia) { this.ia = ia; }

    public void setDroneId(String droneId) { ia.setDroneId(droneId); }

    public String calcularRota(String origem, String destino) {
        System.out.printf("[NAV] %s → %s%n", origem, destino);
        return ia.tomarDecisao(ia.processarDados(origem + "-" + destino));
    }

    public boolean detectarAmeacas(String regiao) {
        System.out.printf("[NAV] Verificando: %s%n", regiao);
        return false;
    }
}
