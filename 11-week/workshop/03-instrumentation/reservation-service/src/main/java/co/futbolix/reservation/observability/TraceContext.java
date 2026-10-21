package co.futbolix.reservation.observability;

import java.security.SecureRandom;
import java.util.HexFormat;
import java.util.regex.Pattern;

/** W3C trace context: traceparent = 00-<trace_id>-<span_id>-<flags>. */
public record TraceContext(String traceId, String spanId, String parentSpanId) {

    private static final Pattern TRACEPARENT =
            Pattern.compile("^00-([0-9a-f]{32})-([0-9a-f]{16})-([0-9a-f]{2})$");
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final ThreadLocal<TraceContext> CURRENT = new ThreadLocal<>();

    public static TraceContext fromHeaderOrNew(String header) {
        if (header != null) {
            var m = TRACEPARENT.matcher(header.trim());
            if (m.matches()) {
                return new TraceContext(m.group(1), randomHex(8), m.group(2));
            }
        }
        return new TraceContext(randomHex(16), randomHex(8), null);
    }

    /** New span for an outgoing call, same trace_id. */
    public TraceContext childSpan() {
        return new TraceContext(traceId, randomHex(8), spanId);
    }

    public String toTraceparent() {
        return "00-" + traceId + "-" + spanId + "-01";
    }

    public static TraceContext current() {
        return CURRENT.get();
    }

    static void set(TraceContext ctx) {
        CURRENT.set(ctx);
    }

    static void clear() {
        CURRENT.remove();
    }

    private static String randomHex(int bytes) {
        byte[] b = new byte[bytes];
        RANDOM.nextBytes(b);
        return HexFormat.of().formatHex(b);
    }
}
