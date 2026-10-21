package co.futbolix.reservation.observability;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/** Reads or creates the trace context and returns it in the response. */
@Component
public class TraceContextFilter extends OncePerRequestFilter {

    public static final String TRACEPARENT = "traceparent";
    private final StructuredLogger log;

    public TraceContextFilter(StructuredLogger log) {
        this.log = log;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain chain) throws ServletException, IOException {
        TraceContext ctx = TraceContext.fromHeaderOrNew(req.getHeader(TRACEPARENT));
        TraceContext.set(ctx);
        res.setHeader(TRACEPARENT, ctx.toTraceparent());
        long start = System.currentTimeMillis();
        try {
            log.info("http.request.received", req.getMethod() + " " + req.getRequestURI(), 0, Map.of());
            chain.doFilter(req, res);
        } finally {
            long ms = System.currentTimeMillis() - start;
            log.info("http.request.completed", req.getMethod() + " " + req.getRequestURI() + " answered",
                    ms, Map.of("http_status", res.getStatus()));
            TraceContext.clear();
        }
    }
}
