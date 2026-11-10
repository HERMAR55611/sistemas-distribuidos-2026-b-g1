<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       12-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 12

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Hernando Antonio Martín Herrera
- GITHUB_USER: HERMAR55611
- TEAM: Futbolix
- SPRINT_GOAL: Make the "Create a court reservation" journey observable: trace map, structured log contract, reservation-service instrumentation and metric catalog.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| W11-CP1 | Critical journey and trace map (Create a court reservation) | done | https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/commit/38e1b26 |
| W11-CP2 | Structured log contract for all Futbolix services | done | https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/commit/f61d636 |
| W11-CP3 | reservation-service instrumentation: trace context, JSON logger, propagation to court-service | done | https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/commit/1d089f8 |
| W11-CP3 | Test: same trace_id received by court-service (2/2 pass) | done | https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/commit/e5c7eaf |
| W11-CP4 | Golden signals and metric catalog | done | https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/commit/bf94351 |

## 2. My individual contribution
- Mapped the reservation journey across api-gateway, reservation-service, court-service, RabbitMQ and payment-service, with W3C traceparent propagation over HTTP and AMQP headers.
- Defined one JSON log contract with trace_id and a "never log" list for customer data, JWT and Wompi keys.
- Built a Spring Boot module for reservation-service with a trace-context filter, a structured logger, a client that forwards traceparent to court-service, and two metrics at /metrics.
- Wrote and ran the propagation test; the first run failed with 404 on /metrics and I fixed it with @AutoConfigureObservability.

## 3. Blockers and risks
- Futbolix has no tracing yet (technical debt AT-002); the instrumentation lives in a separate module, not in the real reservation-service.
- The service repositories are still mostly empty.

## 4. Plan for next week
- Threat model of trust boundary TB1 (web app -> api-gateway) and security stories for the team backlog.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [x] Testable acceptance criteria
- [x] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

## 6. Evidence links
- Workshop folder: https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/tree/main/11-week/workshop
- Commits of the week: https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/commits/main/11-week
