package control;
 
import entity.BancoDadosDistribuido;
import entity.LogAuditoria;
import entity.Missao;
import interfaces.IAutenticavel;
import interfaces.IComandar;
import interfaces.IRegistravel;
import model.Drone;
import security.Autenticacao;
import security.ComunicacaoSegura;
 
import java.util.ArrayList;
import java.util.List;
 
/**
 * CentralControle: ponto de mediação completa do sistema Falcão Sombrio.
 *
 * Implementa IComandar, IAutenticavel e IRegistravel.
 * Segurança: nunca expõe BD diretamente ao Operador (menor privilégio).
 * Diagrama de colaboração: mensagens 2, 2.1, 2.3, 2.4, 3, 3.1, 3.2.
 */
public class CentralControle implements IComandar, IAutenticavel, IRegistravel {
 
    private final int id;
    private final Autenticacao autenticacao;
    private final ComunicacaoSegura comunicacao;
    private final BancoDadosDistribuido bancoDados;
    private final LogAuditoria logAuditoria;
    private final List<Drone> drones = new ArrayList<>();
 
    public CentralControle(int id,
                           Autenticacao autenticacao,
                           ComunicacaoSegura comunicacao,
                           BancoDadosDistribuido bancoDados,
                           LogAuditoria logAuditoria) {
        if (id <= 0) throw new IllegalArgumentException("ID da central deve ser positivo.");
        this.id = id;
        this.autenticacao = autenticacao;
        this.comunicacao = comunicacao;
        this.bancoDados = bancoDados;
        this.logAuditoria = logAuditoria;
    }
 
    // ── IComandar ──────────────────────────────────────────────
    @Override
    public void enviarComando(String cmd) {
        if (cmd == null || cmd.isBlank()) {
            throw new IllegalArgumentException("Comando não pode ser vazio.");
        }
        System.out.printf("[CENTRAL #%d] Enviando comando: %s%n", id, cmd);
        String cmdCriptografado = comunicacao.criptografarDados(cmd);
        drones.forEach(d -> comunicacao.transmitirDados(cmdCriptografado, d.getId()));
        registrar("Comando enviado: " + cmd);
    }
 
    // ── IAutenticavel ──────────────────────────────────────────
    @Override
    public boolean autenticar(String token) {
        boolean ok = autenticacao.autenticar(token);
        registrar(ok ? "Autenticação MFA bem-sucedida." : "Falha de autenticação MFA.");
        return ok;
    }
 
    // ── IRegistravel ───────────────────────────────────────────
    @Override
    public void registrar(String evento) {
        if (evento == null || evento.isBlank()) return;
        logAuditoria.persistirLog(evento);
    }
 
    // ── Operações principais ───────────────────────────────────
 
    /** Diagrama de sequência msg 2.1: executarMissao() → Drone. */
    public void executarMissao(Drone drone, Missao missao) {
        if (drone == null || missao == null) {
            throw new IllegalArgumentException("Drone e missão são obrigatórios.");
        }
        System.out.printf("[CENTRAL #%d] Iniciando missão #%d no drone #%d%n",
                id, missao.getId(), drone.getId());
        registrar("Missão #" + missao.getId() + " iniciada no drone #" + drone.getId());
        drone.executarMissao();
    }
 
    /** Diagrama de sequência msg 2.4: registrar dados da missão no BD. */
    public void registrarDadosMissao(String chave, String valor) {
        bancoDados.armazenarDados(chave, valor);
        registrar("Dados da missão registrados: " + chave);
    }
 
    /** Diagrama de sequência msg 3.2: salvarLogFinal(). */
    public void salvarLogFinal(int missaoId) {
        bancoDados.armazenarDados("missao_" + missaoId + "_log", "concluida");
        bancoDados.replicarDados();
        registrar("Log final da missão #" + missaoId + " salvo e replicado.");
    }
 
    /** Monitoramento de drones (dashboard telemetria). */
    public void monitorarDrones() {
        System.out.printf("[CENTRAL #%d] Monitorando %d drone(s):%n", id, drones.size());
        drones.forEach(Drone::enviarTelemetria);
    }
 
    public void visualizarTelemetria() {
        monitorarDrones();
    }
 
    public void adicionarDrone(Drone drone) {
        if (drone == null) throw new IllegalArgumentException("Drone não pode ser nulo.");
        drones.add(drone);
        System.out.printf("[CENTRAL #%d] Drone #%d registrado na frota.%n", id, drone.getId());
    }
 
    public int getId() { return id; }
}
