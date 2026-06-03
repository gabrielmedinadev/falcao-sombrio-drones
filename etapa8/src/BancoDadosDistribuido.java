import java.util.*;

public class BancoDadosDistribuido {
    private String status;
    private final Map<String, String> dados = new HashMap<>();
    private final ServidorBackup servidorBackup;
    private final LogAuditoriaDAO logDAO;

    public BancoDadosDistribuido(ServidorBackup servidorBackup, LogAuditoriaDAO logDAO) {
        this.status = "ativo";
        this.servidorBackup = servidorBackup;
        this.logDAO = logDAO;
    }

    public void armazenarDados(String chave, String valor) {
        if (chave == null || chave.isBlank()) throw new IllegalArgumentException("Chave vazia.");
        if (valor == null) throw new IllegalArgumentException("Valor nulo.");
        if (!"ativo".equals(status)) {
            System.out.println("[BD] Falha — acionando fallback ServidorBackup.");
            servidorBackup.assumirControle();
            servidorBackup.sincronizar(chave, valor);
            logDAO.salvar(null, null, "[FALLBACK] Dado via backup: " + chave);
            return;
        }
        dados.put(chave, "HASH[" + valor.hashCode() + "]");
        System.out.printf("[BD] Dados armazenados: chave=%s%n", chave);
        logDAO.salvar(null, null, "Dado armazenado: " + chave);
    }

    public void replicarDados() {
        System.out.printf("[BD] Replicando %d registros...%n", dados.size());
        servidorBackup.sincronizarTodos(dados);
    }

    public void simularFalha() {
        this.status = "falha";
        System.out.println("[BD] Simulação de falha ativada.");
    }

    public String getStatus() { return status; }
}
