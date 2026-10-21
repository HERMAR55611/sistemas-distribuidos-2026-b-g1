package co.futbolix.reservation.observability;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** Writes one JSON line per event, following 02-log-contract.md. */
@Component
public class StructuredLogger {

    private final ObjectMapper mapper = new ObjectMapper();
    private final String service;
    private final String version;
    private final String environment;

    public StructuredLogger(@Value("${futbolix.service:reservation-service}") String service,
                            @Value("${futbolix.version:2.0.0}") String version,
                            @Value("${futbolix.environment:dev}") String environment) {
        this.service = service;
        this.version = version;
        this.environment = environment;
    }

    public void info(String event, String message, long durationMs, Map<String, ?> fields) {
        write("INFO", event, message, durationMs, fields);
    }

    public void warn(String event, String message, long durationMs, Map<String, ?> fields) {
        write("WARN", event, message, durationMs, fields);
    }

    private void write(String level, String event, String message, long durationMs,
                       Map<String, ?> fields) {
        TraceContext ctx = TraceContext.current();
        Map<String, Object> line = new LinkedHashMap<>();
        line.put("timestamp", Instant.now().toString());
        line.put("level", level);
        line.put("service", service);
        line.put("version", version);
        line.put("environment", environment);
        line.put("trace_id", ctx == null ? null : ctx.traceId());
        line.put("span_id", ctx == null ? null : ctx.spanId());
        line.put("correlation_id", ctx == null ? null : "req-" + ctx.traceId().substring(0, 8));
        line.put("event", event);
        line.put("message", message);
        line.put("duration_ms", durationMs);
        line.putAll(fields);
        try {
            System.out.println(mapper.writeValueAsString(line));
        } catch (JsonProcessingException e) {
            System.out.println("{\"level\":\"ERROR\",\"event\":\"log.serialization.failed\"}");
        }
    }
}
