package co.futbolix.reservation;

import co.futbolix.reservation.observability.StructuredLogger;
import co.futbolix.reservation.observability.TraceContext;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/** Outgoing call to court-service GET /courts/{id}/bookable-slots, with traceparent. */
@Component
public class CourtClient {

    private final RestClient http;
    private final StructuredLogger log;

    public CourtClient(@Value("${court.base-url}") String baseUrl, StructuredLogger log) {
        this.http = RestClient.builder().baseUrl(baseUrl).build();
        this.log = log;
    }

    public String bookableSlots(String courtId, String date) {
        TraceContext child = TraceContext.current().childSpan();
        long start = System.currentTimeMillis();
        String body = http.get()
                .uri("/courts/{id}/bookable-slots?date={date}", courtId, date)
                .header("traceparent", child.toTraceparent())
                .retrieve()
                .body(String.class);
        log.info("court.slots.listed", "court-service returned bookable slots",
                System.currentTimeMillis() - start, Map.of("court_id", courtId));
        return body;
    }
}
