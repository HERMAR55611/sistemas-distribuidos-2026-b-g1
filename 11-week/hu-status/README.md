<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       11-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 11

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Hernando Antonio Martín Herrera
- GITHUB_USER: HERMAR55611
- TEAM: Futbolix
- SPRINT_GOAL: Make the "Create a court reservation" journey observable end to end: trace map, log contract, instrumentation code, metrics, SLOs, alerts, runbook and incident drill.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| W11-CP1 | Critical journey and trace map (Create a court reservation) | done | https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/blob/main/11-week/workshop/01-journey.md |
| W11-CP2 | Structured log contract | done | https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/blob/main/11-week/workshop/02-log-contract.md |
| W11-CP3 | Instrumentation code for reservation-service (trace context, JSON logger, propagation to court-service, metrics, test) | done | https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/tree/main/11-week/workshop/03-instrumentation |
| W11-CP4 | Golden signals and metric catalog | done | https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/blob/main/11-week/workshop/04-metrics.md |
| W11-CP6-CP10 | SLOs, burn-rate alerts, runbook, incident drill (delivery 2) | todo | - |

## 2. My individual contribution
- Chose the "Create a court reservation" journey from ftx-docs: web app, api-gateway, reservation-service, court-service, RabbitMQ (`ReservationCreated`) and payment-service, with a Mermaid sequence diagram and a span tree.
- Defined the JSON log contract shared by all Futbolix services, the Futbolix domain fields and the "never log" list (customer name, email, phone, JWT, Wompi keys).
- Built a Spring Boot module for reservation-service: `TraceContextFilter`, `StructuredLogger`, `CourtClient` that forwards `traceparent` to court-service, two metrics at `/metrics` and a test that checks the same `trace_id` goes downstream. Tests pass (2/2).
- Wrote the golden signals catalog for the four services of the journey, with cardinality limits and histogram buckets.

## 3. Blockers and risks
- My fork cannot be synced with the parent repo from the web (merge conflicts); I worked only inside `11-week/workshop`.
- Futbolix has no tracing today (technical debt AT-002) and the service repositories are still empty, so the instrumentation is a separate module, not yet inside the real reservation-service.
- The first test run failed (404 on `/metrics`); fixed with `@AutoConfigureObservability`.

## 4. Plan for next week
- Delivery 2: SLOs and error budget, burn-rate alerts, runbook and incident drill with postmortem.
- Propose to the team a user story to add the trace-context filter and JSON logger to the real reservation-service.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [x] Testable acceptance criteria
- [x] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

## 6. Evidence links
- Workshop folder: https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/tree/main/11-week/workshop
- Workshop commits: https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/commits/main/11-week/workshop
