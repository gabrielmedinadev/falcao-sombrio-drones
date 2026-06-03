import java.sql.*;

public class AutenticacaoDAO {
    public void registrar(String operadorId, boolean biometriaValidada, boolean mfaValidado) {
        String sql = "INSERT INTO autenticacao (operador_id, biometria_validada, mfa_validado, ultima_validacao) VALUES (?::uuid, ?, ?, NOW())";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, operadorId);
            ps.setBoolean(2, biometriaValidada);
            ps.setBoolean(3, mfaValidado);
            ps.executeUpdate();
            System.out.println("[DB] Autenticação registrada no Supabase.");
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao registrar autenticação: " + e.getMessage());
        }
    }
}
