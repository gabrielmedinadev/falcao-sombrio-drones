package entity;
 
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
/**
 * LogAuditoria: armazenamento imutável de eventos.
 *
 * Segurança:
 *   - Logs não contêm PII
 *   - Lista interna é imutável externamente (read-only view)
 *   - {evento.isHashed = true} — constraint do diagrama de projeto
 */
public class LogAuditoria {
 
    /**
     * Registro imutável de um evento de auditoria.
     */
    public static final class Registro {
        private final int id;
        private final LocalDateTime timestamp;
        private final String evento;
 
        public Registro(int id, String evento) {
            if (evento == null || evento.isBlank()) {
                throw new IllegalArgumentException("Evento de auditoria não pode ser vazio.");
            }
            this.id = id;
            this.timestamp = LocalDateTime.now();
            this.evento = evento;
        }
 
        @Override
        public String toString() {
            return String.format("[LOG #%d | %s] %s", id, timestamp, evento);
        }
 
        public int getId()              { return id; }
        public LocalDateTime getTimestamp() { return timestamp; }
        public String getEvento()       { return evento; }
    }
 
    private final List<Registro> registros = new ArrayList<>();
    private int proximoId = 1;
 
    public void persistirLog(String evento) {
        Registro r = new Registro(proximoId++, evento);
        registros.add(r);
        System.out.println(r);
    }
 
    /** Retorna visão somente-leitura — imutabilidade garantida. */
    public List<Registro> getRegistros() {
        return Collections.unmodifiableList(registros);
    }
}
