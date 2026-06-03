import java.util.Map;

public class ServidorBackup {
    private String status;

    public ServidorBackup() { this.status = "standby"; }

    public void assumirControle() {
        this.status = "ativo";
        System.out.println("[BACKUP] Servidor backup assumiu o controle.");
    }

    public void sincronizar(String chave, String valor) {
        System.out.printf("[BACKUP] Sincronizando: chave=%s%n", chave);
    }

    public void sincronizarTodos(Map<String, String> dados) {
        System.out.printf("[BACKUP] Sincronizando %d registros.%n", dados.size());
    }

    public String getStatus() { return status; }
}
