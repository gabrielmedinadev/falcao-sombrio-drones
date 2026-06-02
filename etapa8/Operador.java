package model;
 
import control.CentralControle;
import entity.Missao;
import security.Autenticacao;
 
/**
 * Operador: boundary do sistema.
 * Interage com a CentralControle; nunca acessa o BD diretamente (menor privilégio).
 * Diagrama de colaboração: mensagens 1, 1.2, 2, 3.
 */
public class Operador {
 
    private final int id;
    private final String nome;
    private final String nivelAcesso;
    private final Autenticacao autenticacao;
 
    public Operador(int id, String nome, String nivelAcesso, Autenticacao autenticacao) {
        if (id <= 0)             throw new IllegalArgumentException("ID do operador deve ser positivo.");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome não pode ser vazio.");
        if (nivelAcesso == null) throw new IllegalArgumentException("Nível de acesso não pode ser nulo.");
        this.id = id;
        this.nome = nome;
        this.nivelAcesso = nivelAcesso;
        this.autenticacao = autenticacao;
    }
 
    /**
     * Fluxo completo de autenticação: biometria + MFA.
     * Diagrama de sequência: 1 validarBiometria() → 1.1 validarMFA()
     */
    public boolean autenticarCompleto(String dadoBiometrico, String tokenMFA) {
        System.out.printf("[OPERADOR %s] Iniciando autenticação...%n", nome);
 
        boolean bioOk = autenticacao.validarBiometria(String.valueOf(id), dadoBiometrico);
        if (!bioOk) {
            System.out.println("[OPERADOR] Biometria inválida — acesso negado.");
            return false;
        }
 
        boolean mfaOk = autenticacao.autenticar(tokenMFA);
        if (!mfaOk) {
            System.out.println("[OPERADOR] MFA inválido — acesso negado.");
            return false;
        }
 
        System.out.printf("[OPERADOR %s] Acesso concedido.%n", nome);
        return true;
    }
 
    /**
     * Diagrama de sequência msg 2: enviarComando(IniciarMissão).
     */
    public void iniciarMissao(CentralControle central, Drone drone, Missao missao) {
        System.out.printf("[OPERADOR %s] Solicitando início de missão #%d...%n",
                nome, missao.getId());
        central.executarMissao(drone, missao);
    }
 
    /**
     * Diagrama de sequência msg 3: finalizarMissao().
     */
    public void finalizarMissao(CentralControle central, int missaoId) {
        System.out.printf("[OPERADOR %s] Solicitando encerramento da missão #%d...%n",
                nome, missaoId);
        central.salvarLogFinal(missaoId);
    }
 
    public int getId()             { return id; }
    public String getNome()        { return nome; }
    public String getNivelAcesso() { return nivelAcesso; }
}
