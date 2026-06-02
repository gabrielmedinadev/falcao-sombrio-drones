package interfaces;
 
/**
 * Contrato restrito de autenticação
 * Apenas classes que precisam de autenticação implementam esta interface.
 */
public interface IAutenticavel {
    /**
     * @param token token de autenticação 
     * @return true se autenticado com sucesso
     */
    boolean autenticar(String token);
}
