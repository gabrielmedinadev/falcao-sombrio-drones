import java.sql.*;

public class MissaoDAO {
    public String criar(String droneId, String operadorId, String tipo) {
        String sql = "INSERT INTO missoes (drone_id, operador_id, objetivo, status_missao) VALUES (?::uuid, ?::uuid, ?, 'em_andamento') RETURNING id";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, droneId);
            ps.setString(2, operadorId);
            ps.setString(3, tipo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                System.out.printf("[DB] Missão '%s' criada com ID=%s%n", tipo, id);
                return id;
            }
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao criar missão: " + e.getMessage());
        }
        return null;
    }

    public void iniciar(String missaoId) {
        String sql = "UPDATE missoes SET status_missao='em_andamento', inicio_missao=NOW() WHERE id=?::uuid";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, missaoId);
            ps.executeUpdate();
            System.out.printf("[DB] Missão iniciada: %s%n", missaoId);
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao iniciar missão: " + e.getMessage());
        }
    }

    public void finalizar(String missaoId) {
        String sql = "UPDATE missoes SET status_missao='concluida', fim_missao=NOW() WHERE id=?::uuid";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, missaoId);
            ps.executeUpdate();
            System.out.printf("[DB] Missão finalizada: %s%n", missaoId);
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao finalizar missão: " + e.getMessage());
        }
    }
}
