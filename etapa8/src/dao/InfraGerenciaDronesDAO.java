import java.sql.*;

public class InfraGerenciaDronesDAO {
    public void vincular(String infraId, String droneId) {
        String sql = "INSERT INTO infra_gerencia_drones (infra_id, drone_id, vinculado_em) VALUES (?::uuid, ?::uuid, NOW())";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, infraId);
            ps.setString(2, droneId);
            ps.executeUpdate();
            System.out.println("[DB] Drone vinculado à infraestrutura no Supabase.");
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao vincular drone à infraestrutura: " + e.getMessage());
        }
    }
}
