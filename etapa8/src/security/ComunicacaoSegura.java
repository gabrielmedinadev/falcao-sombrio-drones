public class ComunicacaoSegura {
    private final String protocolo;

    public ComunicacaoSegura(String protocolo) {
        if (protocolo == null || protocolo.isBlank())
            throw new IllegalArgumentException("Protocolo inválido.");
        this.protocolo = protocolo;
    }

    public String criptografarDados(String dados) {
        String enc = "ENC[" + dados.hashCode() + "]";
        System.out.printf("[COMM/%s] Dados criptografados.%n", protocolo);
        return enc;
    }

    public void transmitirDados(String enc, int droneId) {
        System.out.printf("[COMM/%s] → Drone #%d: %s%n", protocolo, droneId, enc);
    }
}
