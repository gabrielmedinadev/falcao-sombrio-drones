package interfaces;
 
/**
 * Contrato de registro de eventos.
 */
public interface IRegistravel {
    /**
     * @param evento descrição do evento — não deve conter PII
     * @throws IllegalArgumentException se evento for nulo ou vazio
     */
    void registrar(String evento);
}
