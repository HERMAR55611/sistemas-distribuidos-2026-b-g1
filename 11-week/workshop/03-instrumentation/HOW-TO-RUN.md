# How to run - reservation-service instrumentation

Minimal Spring Boot module (Java 21, the Futbolix stack) for `reservation-service`:

- `TraceContextFilter` reads `traceparent` or creates a new one and returns it in the response.
- `StructuredLogger` writes one JSON line per event with the fields of `02-log-contract.md`.
- `CourtClient` propagates the same `trace_id` to court-service
  `GET /courts/{id}/bookable-slots` with a new `span_id`.
- `ReservationController` exposes `POST /reservations` and records two metrics:
  `reservation_requests_total{outcome}` (counter) and
  `reservation_request_duration_seconds` (histogram), published at `GET /metrics`.
- `TracePropagationTest` starts a fake court-service and checks that the `trace_id`
  received by reservation-service is the same one sent downstream.

## Requirements

Docker Desktop running. No local Java or Maven needed.

## Install and test

Windows: double click `run-tests.bat`, or from this folder:

```
cd reservation-service
docker run --rm -v "%cd%":/app -v futbolix-m2:/root/.m2 -w /app maven:3.9-eclipse-temurin-21 mvn -B test
```

## Run the service

```
docker run --rm -p 8083:8083 -v "%cd%":/app -v futbolix-m2:/root/.m2 -w /app maven:3.9-eclipse-temurin-21 mvn -B spring-boot:run
```

Then `GET http://localhost:8083/metrics` and `GET http://localhost:8083/health`.

## What broke and how it was fixed

The first run failed: `metricsEndpointExposesCounterAndHistogram` got 404 on `/metrics`,
because `@SpringBootTest` turns off metrics export. Adding `@AutoConfigureObservability`
to the test fixed it.

## Real console output (my machine)

```
{"timestamp":"2026-10-21T01:03:26.747167458Z","level":"INFO","service":"reservation-service","version":"2.0.0","environment":"dev","trace_id":"4f8a2c1e9b7d40a6b3e5c7d9f1a2b4c6","span_id":"ca41e78ded55cf43","correlation_id":"req-4f8a2c1e","event":"http.request.received","message":"POST /reservations","duration_ms":0}
{"timestamp":"2026-10-21T01:03:26.753975285Z","level":"INFO","service":"reservation-service","version":"2.0.0","environment":"dev","trace_id":"4f8a2c1e9b7d40a6b3e5c7d9f1a2b4c6","span_id":"ca41e78ded55cf43","correlation_id":"req-4f8a2c1e","event":"court.slots.listed","message":"court-service returned bookable slots","duration_ms":5,"court_id":"c0000000-0000-4000-8000-000000000001"}
{"timestamp":"2026-10-21T01:03:26.754775023Z","level":"INFO","service":"reservation-service","version":"2.0.0","environment":"dev","trace_id":"4f8a2c1e9b7d40a6b3e5c7d9f1a2b4c6","span_id":"ca41e78ded55cf43","correlation_id":"req-4f8a2c1e","event":"reservation.created","message":"reservation stored as PENDING","duration_ms":0,"slot":"18:00-20:00","reservation_date":"2026-10-25","court_id":"c0000000-0000-4000-8000-000000000001"}
{"timestamp":"2026-10-21T01:03:26.756366376Z","level":"INFO","service":"reservation-service","version":"2.0.0","environment":"dev","trace_id":"4f8a2c1e9b7d40a6b3e5c7d9f1a2b4c6","span_id":"ca41e78ded55cf43","correlation_id":"req-4f8a2c1e","event":"http.request.completed","message":"POST /reservations answered","duration_ms":9,"http_status":201}
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 19.82 s -- in co.futbolix.reservation.TracePropagationTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  24.963 s
[INFO] Finished at: 2026-10-21T01:03:27Z
[INFO] ------------------------------------------------------------------------
```
