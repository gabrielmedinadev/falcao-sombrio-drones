package interfaces;
 
/**
 * Contrato de atualização de firmware do drone.
 */
public interface IAtualizavel {
    /**
     * @param versao string de versão — ex.: "2.1.0". Não pode ser nulo.
     * @throws IllegalArgumentException se versao for nulo ou vazio
     */
    void atualizarFirmware(String versao);
}
