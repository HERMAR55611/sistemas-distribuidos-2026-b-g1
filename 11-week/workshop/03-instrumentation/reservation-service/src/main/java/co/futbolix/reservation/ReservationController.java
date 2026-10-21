package co.futbolix.reservation;

import co.futbolix.reservation.observability.StructuredLogger;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    record CreateReservationRequest(String courtId, String reservationDate,
                                    String startTime, String endTime) { }

    private final CourtClient courts;
    private final StructuredLogger log;
    private final MeterRegistry registry;
    private final Timer latency;

    public ReservationController(CourtClient courts, StructuredLogger log, MeterRegistry registry) {
        this.courts = courts;
        this.log = log;
        this.registry = registry;
        this.latency = Timer.builder("reservation.request.duration")
                .description("POST /reservations latency")
                .tag("route", "/reservations")
                .publishPercentileHistogram()
                .register(registry);
    }

    @PostMapping("/reservations")
    public ResponseEntity<Map<String, String>> create(@RequestBody CreateReservationRequest req) {
        return latency.record(() -> {
            try {
                courts.bookableSlots(req.courtId(), req.reservationDate());
                log.info("reservation.created", "reservation stored as PENDING", 0,
                        Map.of("court_id", req.courtId(), "reservation_date", req.reservationDate(),
                               "slot", req.startTime() + "-" + req.endTime()));
                count("success");
                return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("status", "PENDING"));
            } catch (RuntimeException e) {
                log.warn("court.slots.failed", "court-service call failed", 0,
                        Map.of("court_id", req.courtId(), "error_code", "SERVICE_UNAVAILABLE"));
                count("error");
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(Map.of("error", "SERVICE_UNAVAILABLE"));
            }
        });
    }

    private void count(String outcome) {
        Counter.builder("reservation.requests")
                .description("POST /reservations by outcome")
                .tag("route", "/reservations")
                .tag("outcome", outcome)
                .register(registry)
                .increment();
    }
}
