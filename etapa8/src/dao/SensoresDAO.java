import java.sql.*;

public class SensoresDAO {
    public void registrar(String droneId, String tipoSensor, String status, String dadosJson) {
        String sql = "INSERT INTO sensores (drone_id, tipo_sensor, status, ultima_leitura) VALUES (?::uuid, ?, ?, ?::jsonb)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, droneId);
            ps.setString(2, tipoSensor);
            ps.setString(3, status);
            ps.setString(4, dadosJson);
            ps.executeUpdate();
            System.out.printf("[DB] Sensor %s registrado no Supabase.%n", tipoSensor);
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao registrar sensor: " + e.getMessage());
        }
    }
}
