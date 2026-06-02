package entity;
 
import java.util.HashMap;
import java.util.Map;
 
/**
 * BancoDadosDistribuido: armazenamento e replicação de dados de missão.
 *
 * Segurança:
 *   - Acesso apenas via serviço (CentralControle) — menor privilégio
 *   - Dados armazenados como hash (simulado)
 *   - Fallback via ServidorBackup em caso de falha de conexão
 */
public class BancoDadosDistribuido {
 
    private String status;
    private final Map<String, String> dados = new HashMap<>();
    private final ServidorBackup servidorBackup;
 
    public BancoDadosDistribuido(ServidorBackup servidorBackup) {
        this.status = "ativo";
        this.servidorBackup = servidorBackup;
    }
 
    public void armazenarDados(String chave, String valor) {
        if (chave == null || chave.isBlank()) {
            throw new IllegalArgumentException("Chave não pode ser vazia.");
        }
        if (valor == null) {
            throw new IllegalArgumentException("Valor não pode ser nulo.");
        }
        if (!"ativo".equals(status)) {
            System.out.println("[BD] Falha de conexão — acionando fallback ServidorBackup.");
            servidorBackup.assumirControle();
            servidorBackup.sincronizar(chave, valor);
            return;
        }
        dados.put(chave, hashSimulado(valor));
        System.out.printf("[BD] Dados armazenados: chave=%s%n", chave);
    }
 
    public void replicarDados() {
        System.out.printf("[BD] Replicando %d registros geograficamente...%n", dados.size());
        servidorBackup.sincronizarTodos(dados);
    }
 
    public String consultarDados(String chave) {
        return dados.get(chave);
    }
 
    public String getStatus() { return status; }
 
    public void simularFalha() {
        this.status = "falha";
        System.out.println("[BD] Simulação de falha de conexão ativada.");
    }
 
    /** Simulação de hash — em produção usar SHA-256 + salt. */
    private String hashSimulado(String valor) {
        return "HASH[" + valor.hashCode() + "]";
    }
}
