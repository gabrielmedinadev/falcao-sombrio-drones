import java.sql.*;

public class ComunicacaoSeguraDAO {
    public void registrar(String droneId, String protocolo) {
        String sql = "INSERT INTO comunicacao_segura (drone_id, protocolo, ultima_transmissao) VALUES (?::uuid, ?, NOW())";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, droneId);
            ps.setString(2, protocolo);
            ps.executeUpdate();
            System.out.println("[DB] Comunicação segura registrada no Supabase.");
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao registrar comunicação: " + e.getMessage());
        }
    }
}
