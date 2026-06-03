import java.sql.*;

public class NavegacaoIADAO {
    public void salvar(String droneId, String algoritmo, String decisao, String rotaCalculada) {
        String sql = "INSERT INTO navegacao_ia (drone_id, algoritmo_ia, decisao_tomada, rota_calculada, processado_em) VALUES (?::uuid, ?, ?, ?::jsonb, NOW())";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, droneId);
            ps.setString(2, algoritmo);
            ps.setString(3, decisao);
            ps.setString(4, rotaCalculada);
            ps.executeUpdate();
            System.out.printf("[DB] Navegação IA salva: %s%n", decisao);
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao salvar navegação IA: " + e.getMessage());
        }
    }
}
