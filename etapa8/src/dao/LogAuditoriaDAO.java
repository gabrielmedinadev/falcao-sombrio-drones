import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDateTime;

public class LogAuditoriaDAO {
    public void salvar(String missaoId, String droneId, String evento) {
        String hash = gerarHash(evento + LocalDateTime.now());
        String sql = "INSERT INTO logs_auditoria (missao_id, drone_id, evento, hash_imutavel) VALUES (?::uuid, ?::uuid, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (missaoId != null) ps.setString(1, missaoId); else ps.setNull(1, Types.OTHER);
            if (droneId  != null) ps.setString(2, droneId);  else ps.setNull(2, Types.OTHER);
            ps.setString(3, evento);
            ps.setString(4, hash);
            ps.executeUpdate();
            System.out.printf("[DB] Log persistido: %s%n", evento);
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao salvar log: " + e.getMessage());
        }
    }

    public void listarTodos() {
        String sql = "SELECT id, missao_id, drone_id, evento, criado_em FROM logs_auditoria ORDER BY criado_em DESC LIMIT 20";
        try (Connection conn = ConexaoDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n[DB] ══ LOGS DE AUDITORIA (PostgreSQL) ══");
            while (rs.next()) {
                System.out.printf("  [#%d | %s] drone=%s — %s%n",
                        rs.getInt("id"), rs.getTimestamp("criado_em"),
                        rs.getString("drone_id"), rs.getString("evento"));
            }
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao listar logs: " + e.getMessage());
        }
    }

    private String gerarHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) { return "HASH_ERRO"; }
    }
}
