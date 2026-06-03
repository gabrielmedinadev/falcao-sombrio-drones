import java.sql.*;

public class ConexaoDB {
    private static final String HOST     = "aws-1-us-east-1.pooler.supabase.com";
    private static final String PORT     = "5432";
    private static final String DATABASE = "postgres";
    private static final String USER     = "postgres.akcthaxdeyrmboingwec";
    private static final String PASSWORD = "Projetodrones";

    private static final String URL =
            "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE + "?sslmode=require";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void inicializarTabelas() {
        try (Connection conn = conectar()) {
            System.out.println("[DB] Conexão verificada com sucesso.");
        } catch (SQLException e) {
            System.err.println("[DB] Erro ao verificar conexão: " + e.getMessage());
        }
    }
}
