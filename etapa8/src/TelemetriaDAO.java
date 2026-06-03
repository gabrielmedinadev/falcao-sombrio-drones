import java.sql.*;

public class TelemetriaDAO {
    public void salvar(String droneId, String statusDrone, int nivelBateria) {
        String sql = "INSERT INTO telemetria (drone_id, localizacao, velocidade, status_drone) VALUES (?::uuid, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, droneId);
            ps.setString(2, "BASE_ALPHA");
            ps.setDouble(3, 120.5);
            ps.setString(4, statusDrone);
            ps.executeUpdate();
            System.out.println("[DB] Telemetria salva.");
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao salvar telemetria: " + e.getMessage());
        }
    }
}
