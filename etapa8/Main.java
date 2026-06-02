import control.CentralControle;
import entity.BancoDadosDistribuido;
import entity.LogAuditoria;
import entity.Missao;
import entity.ServidorBackup;
import model.Drone;
import model.Operador;
import security.Autenticacao;
import security.ComunicacaoSegura;
import service.DroneAquila;
import service.IA_Navegacao;
import service.SistemaNavegacao;
 
/**
 * Main: demonstra o fluxo completo do sistema Falcão Sombrio.
 *
 * Fluxo (espelha o diagrama de sequência):
 *   1. Operador autentica (biometria + MFA)
 *   2. Central recebe comando de iniciar missão
 *   3. Drone executa missão (calcula rota, envia telemetria, retorna)
 *   4. Central registra dados e salva log final
 */
public class Main {
 
    public static void main(String[] args) {
 
        System.out.println("=== SISTEMA FALCÃO SOMBRIO — Securus Dynamics ===\n");
 
        // ── Instanciar infraestrutura ──────────────────────────
        ServidorBackup backup           = new ServidorBackup();
        BancoDadosDistribuido bd        = new BancoDadosDistribuido(backup);
        LogAuditoria log                = new LogAuditoria();
        Autenticacao auth               = new Autenticacao();
        ComunicacaoSegura comm          = new ComunicacaoSegura("TLS-1.3");
 
        // ── Central de Controle ────────────────────────────────
        CentralControle central = new CentralControle(1, auth, comm, bd, log);
 
        // ── Sistema de Navegação + IA ──────────────────────────
        IA_Navegacao ia                 = new IA_Navegacao();
        SistemaNavegacao nav            = new SistemaNavegacao(ia);
 
        // ── Drone Aquila-X ─────────────────────────────────────
        DroneAquila drone = new DroneAquila(42, 100.0f, nav);
        central.adicionarDrone(drone);
 
        // ── Operador ───────────────────────────────────────────
        Operador operador = new Operador(1, "Rodrigo", "admin", auth);
 
        System.out.println("\n--- [1] AUTENTICAÇÃO DO OPERADOR ---");
        boolean autenticado = operador.autenticarCompleto("BIO_RODRIGO", "123456");
        if (!autenticado) {
            System.out.println("Operador não autenticado. Encerrando.");
            return;
        }
 
        System.out.println("\n--- [2] ENVIO DE COMANDO ---");
        central.enviarComando("IniciarMissao");
 
        System.out.println("\n--- [3] EXECUÇÃO DA MISSÃO ---");
        Missao missao = new Missao(1, "reconhecimento");
        drone.atribuirMissao(missao);
        operador.iniciarMissao(central, drone, missao);
 
        System.out.println("\n--- [4] REGISTRO E LOG FINAL ---");
        central.registrarDadosMissao("missao_1_resultado", "sucesso");
        operador.finalizarMissao(central, missao.getId());
 
        System.out.println("\n--- [5] TELEMETRIA FINAL ---");
        central.visualizarTelemetria();
 
        System.out.println("\n--- [6] SIMULAÇÃO DE FALLBACK ---");
        bd.simularFalha();
        central.registrarDadosMissao("missao_1_failover", "dado_emergencia");
 
        System.out.println("\n--- [7] LOG DE AUDITORIA ---");
        log.getRegistros().forEach(System.out::println);
 
        System.out.println("\n=== FIM DA DEMONSTRAÇÃO ===");
    }
}
