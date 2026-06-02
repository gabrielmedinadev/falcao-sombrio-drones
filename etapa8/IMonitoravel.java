package interfaces;
 
/**
 * Contrato de monitoramento de bateria do drone.
 */
public interface IMonitoravel {
    /**
     * @return nível atual da bateria (0.0 a 100.0)
     */
    float monitorarBateria();
}
