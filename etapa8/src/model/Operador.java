public class Operador {
    private final int id;
    private final String nome;
    private final Autenticacao auth;
    private String dbId = null;

    public Operador(int id, String nome, String nivelAcesso,
                    Autenticacao auth, OperadorDAO operadorDAO) {
        if (id <= 0) throw new IllegalArgumentException("ID inválido.");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome vazio.");
        this.id = id; this.nome = nome; this.auth = auth;
        this.dbId = operadorDAO.inserir(nome, nivelAcesso);
    }

    public boolean autenticarCompleto(String dadoBiometrico, String tokenMFA) {
        System.out.printf("[OPERADOR %s] Autenticando...%n", nome);
        if (!auth.validarBiometria(String.valueOf(id), dadoBiometrico)) {
            System.out.println("[OPERADOR] Biometria inválida.");
            return false;
        }
        if (!auth.autenticar(tokenMFA)) {
            System.out.println("[OPERADOR] MFA inválido.");
            return false;
        }
        System.out.printf("[OPERADOR %s] Acesso concedido.%n", nome);
        return true;
    }

    public void iniciarMissao(CentralControle central, Drone drone, Missao missao) {
        System.out.printf("[OPERADOR %s] Iniciando missão #%d...%n", nome, missao.getId());
        central.executarMissao(drone, missao);
    }

    public void finalizarMissao(CentralControle central, String missaoId) {
        System.out.printf("[OPERADOR %s] Finalizando missão %s...%n", nome, missaoId);
        central.salvarLogFinal(missaoId);
    }

    public String getDbId() { return dbId; }
}
