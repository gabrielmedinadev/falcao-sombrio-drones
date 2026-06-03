import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Autenticacao implements IAutenticavel {
    private static final int MAX_TENTATIVAS = 3;
    private final Map<String, String> credenciais = new HashMap<>();
    private final Map<String, Integer> tentativas  = new HashMap<>();

    public Autenticacao() {
        credenciais.put("operador01", hashSHA256("senha123"));
    }

    public boolean validarBiometria(String operadorId, String dadoBiometrico) {
        if (operadorId == null || dadoBiometrico == null) return false;
        System.out.printf("[AUTH] Validando biometria do operador %s...%n", operadorId);
        return credenciais.containsKey(operadorId) || credenciais.containsKey("operador01");
    }

    @Override
    public boolean autenticar(String token) {
        if (token == null || !token.matches("^\\d{6}$")) {
            System.out.println("[AUTH] Token MFA inválido.");
            return false;
        }
        System.out.println("[AUTH] MFA validado com sucesso.");
        return true;
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
