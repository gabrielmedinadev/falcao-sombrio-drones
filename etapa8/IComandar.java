package interfaces;
 
/**
 * Contrato mínimo para envio de comandos (princípio do menor privilégio).
 * Expõe apenas o necessário — evita interface gorda.
 */
public interface IComandar {
    /**
     * @param cmd comando a enviar — não pode ser nulo ou vazio
     * @throws IllegalArgumentException se cmd for nulo ou vazio
     */
    void enviarComando(String cmd);
}
