import java.sql.*;

public class InfraAutorizaOperadoresDAO {
    public void autorizar(String infraId, String operadorId) {
        String sql = "INSERT INTO infra_autoriza_operadores (infra_id, operador_id, autorizado_em) VALUES (?::uuid, ?::uuid, NOW())";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, infraId);
            ps.setString(2, operadorId);
            ps.executeUpdate();
            System.out.println("[DB] Operador autorizado pela infraestrutura no Supabase.");
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao autorizar operador: " + e.getMessage());
        }
    }
}
