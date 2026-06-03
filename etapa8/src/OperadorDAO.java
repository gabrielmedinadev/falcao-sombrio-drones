import java.sql.*;

public class OperadorDAO {
    public String inserir(String nome, String nivelAcesso) {
        String sql = "INSERT INTO operadores (nome, nivel_acesso) VALUES (?, ?) RETURNING id";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, nivelAcesso);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                System.out.printf("[DB] Operador '%s' salvo com ID=%s%n", nome, id);
                return id;
            }
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao inserir operador: " + e.getMessage());
        }
        return null;
    }
}
