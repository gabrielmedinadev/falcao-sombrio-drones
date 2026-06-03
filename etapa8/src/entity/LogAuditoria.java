import java.time.LocalDateTime;
import java.util.*;

public class LogAuditoria {

    public static final class Registro {
        private final int id;
        private final LocalDateTime timestamp;
        private final String evento;

        public Registro(int id, String evento) {
            if (evento == null || evento.isBlank())
                throw new IllegalArgumentException("Evento não pode ser vazio.");
            this.id = id;
            this.timestamp = LocalDateTime.now();
            this.evento = evento;
        }

        @Override
        public String toString() {
            return String.format("[LOG #%d | %s] %s", id, timestamp, evento);
        }
    }

    private final LogAuditoriaDAO logDAO;
    private String missaoIdAtual = null;
    private String droneIdAtual  = null;
    private final List<Registro> registros = new ArrayList<>();
    private int proximoId = 1;

    public LogAuditoria(LogAuditoriaDAO logDAO) { this.logDAO = logDAO; }

    public void setContexto(String missaoId, String droneId) {
        this.missaoIdAtual = missaoId;
        this.droneIdAtual  = droneId;
    }

    public void persistirLog(String evento) {
        Registro r = new Registro(proximoId++, evento);
        registros.add(r);
        System.out.println(r);
        logDAO.salvar(missaoIdAtual, droneIdAtual, evento);
    }

    public List<Registro> getRegistros() {
        return Collections.unmodifiableList(registros);
    }
}
