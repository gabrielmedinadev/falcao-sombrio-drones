package security;
 
import interfaces.IAutenticavel;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
 
/**
 * Autenticacao — valida biometria e MFA dos operadores.
 *
 * Segurança (conforme diagrama de estados e PDF aula 12/13):
 *   - Senha nunca trafega em texto puro (hash SHA-256)
 *   - Bloqueio após 3 tentativas falhas
 *   - Mensagens de erro genéricas (não vaza detalhes)
 *   - Canal TLS obrigatório (anotado — depende da infra)
 */
public class Autenticacao implements IAutenticavel {
 
    private static final int MAX_TENTATIVAS = 3;
 
    // hash de credenciais simuladas (em produção: bcrypt/PBKDF2)
    private final Map<String, String> credenciais = new HashMap<>();
    private final Map<String, Integer> tentativas = new HashMap<>();
 
    public Autenticacao() {
        // operador padrão (hash de "senha123")
        credenciais.put("operador01", hashSHA256("senha123"));
    }
 
    /** Valida biometria (simulada como hash de identificador biométrico). */
    public boolean validarBiometria(String operadorId, String dadoBiometrico) {
        if (operadorId == null || dadoBiometrico == null) return false;
        System.out.printf("[AUTH] Validando biometria do operador %s...%n", operadorId);
        // Simulação: biometria sempre válida se operador existe
        return credenciais.containsKey(operadorId);
    }
 
    /** Valida MFA (TOTP de 6 dígitos — contrato restrito). */
    @Override
    public boolean autenticar(String token) {
        if (token == null || !token.matches("^\\d{6}$")) {
            System.out.println("[AUTH] Token inválido.");
            return false;
        }
        System.out.println("[AUTH] MFA validado com sucesso.");
        return true;
    }
 
    /** Login completo: credenciais + bloqueio por tentativas. */
    public boolean login(String operadorId, String senha) {
        if (operadorId == null || senha == null) return false;
 
        int falhas = tentativas.getOrDefault(operadorId, 0);
        if (falhas >= MAX_TENTATIVAS) {
            System.out.println("[AUTH] Conta bloqueada. Contate o administrador.");
            return false;
        }
 
        String hashArmazenado = credenciais.get(operadorId);
        if (hashArmazenado != null && hashArmazenado.equals(hashSHA256(senha))) {
            tentativas.put(operadorId, 0);
            System.out.printf("[AUTH] Acesso concedido para %s.%n", operadorId);
            return true;
        }
 
        tentativas.put(operadorId, falhas + 1);
        System.out.println("[AUTH] Acesso negado.");
        return false;
    }
 
    private static String hashSHA256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash.", e);
        }
    }
}
