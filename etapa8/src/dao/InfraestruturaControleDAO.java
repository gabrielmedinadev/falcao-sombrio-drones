import java.sql.*;

public class InfraestruturaControleDAO {
    public String registrarServidor(String tipoServidor, String statusServidor) {
        String sql = "INSERT INTO infraestrutura_controle (tipo_servidor, status_servidor) VALUES (?, ?) RETURNING id";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tipoServidor);
            ps.setString(2, statusServidor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                System.out.printf("[DB] Servidor '%s' registrado com ID=%s%n", tipoServidor, id);
                return id;
            }
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao registrar servidor: " + e.getMessage());
        }
        return null;
    }

    public void assumirControle(String servidorPrincipalId) {
        String sql = "INSERT INTO infraestrutura_controle (tipo_servidor, status_servidor, assumiu_controle_em, servidor_principal_id) VALUES ('Backup', 'ativo', NOW(), ?::uuid)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, servidorPrincipalId);
            ps.executeUpdate();
            System.out.println("[DB] Servidor Backup registrado como ativo no Supabase.");
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao registrar fallback: " + e.getMessage());
        }
    }
}
