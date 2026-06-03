import java.sql.*;

public class DroneDAO {
    public String inserir(String nome, String modelo, int nivelBateria) {
        String sql = "INSERT INTO drones (nome, modelo, status, nivel_bateria) VALUES (?, ?, 'disponivel', ?) RETURNING id";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, modelo);
            ps.setInt(3, nivelBateria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                System.out.printf("[DB] Drone '%s' salvo com ID=%s%n", nome, id);
                return id;
            }
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao inserir drone: " + e.getMessage());
        }
        return null;
    }

    public void atualizarStatus(String droneId, String status, int nivelBateria) {
        String sql = "UPDATE drones SET status = ?, nivel_bateria = ?, ultima_atualizacao = NOW() WHERE id = ?::uuid";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, nivelBateria);
            ps.setString(3, droneId);
            ps.executeUpdate();
            System.out.printf("[DB] Drone atualizado: status=%s bateria=%d%%%n", status, nivelBateria);
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao atualizar drone: " + e.getMessage());
        }
    }

    public void atualizarLocalizacao(String droneId, double lat, double lon) {
        String sql = "UPDATE drones SET ultima_latitude = ?, ultima_longitude = ?, ultima_atualizacao = NOW() WHERE id = ?::uuid";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, lat);
            ps.setDouble(2, lon);
            ps.setString(3, droneId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao atualizar localização: " + e.getMessage());
        }
    }
}
