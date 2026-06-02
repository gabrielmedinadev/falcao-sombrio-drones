package security;
 
/**
 * ComunicacaoSegura: criptografa e transmite dados entre drone e central.
 * Protocolo: canal TLS simulado (em produção: TLS 1.3 + certificados mTLS).
 */
public class ComunicacaoSegura {
 
    private final String protocolo;
 
    public ComunicacaoSegura(String protocolo) {
        if (protocolo == null || protocolo.isBlank()) {
            throw new IllegalArgumentException("Protocolo não pode ser vazio.");
        }
        this.protocolo = protocolo;
    }
 
    public String criptografarDados(String dados) {
        if (dados == null) throw new IllegalArgumentException("Dados não podem ser nulos.");
        // Simulação: em produção usar AES-256-GCM
        String criptografado = "ENC[" + dados.hashCode() + "]";
        System.out.printf("[COMM/%s] Dados criptografados.%n", protocolo);
        return criptografado;
    }
 
    public void transmitirDados(String dadosCriptografados, int droneId) {
        if (dadosCriptografados == null || dadosCriptografados.isBlank()) {
            throw new IllegalArgumentException("Dados criptografados inválidos.");
        }
        System.out.printf("[COMM/%s] Transmitindo para drone #%d: %s%n",
                protocolo, droneId, dadosCriptografados);
    }
 
    public String getProtocolo() { return protocolo; }
}
