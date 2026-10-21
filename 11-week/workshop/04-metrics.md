# CP4 - Golden signals and metric catalog

Journey: Create a court reservation (`01-journey.md`). One table per service of the journey.
Metric names follow Prometheus rules: snake_case, unit suffix, `_total` for counters.
Micrometer in Spring Boot exports them at `GET /metrics` (see `03-instrumentation/`).

## api-gateway

| Signal | Metric | Type | Labels | PromQL |
|--------|--------|------|--------|--------|
| Latency | `gateway_request_duration_seconds` | histogram | route, method | `histogram_quantile(0.95, sum by (le, route) (rate(gateway_request_duration_seconds_bucket{route="/reservations"}[5m])))` |
| Traffic | `gateway_requests_total` | counter | route, method, status_class | `sum by (route) (rate(gateway_requests_total[5m]))` |
| Errors | `gateway_requests_total` | counter | route, status_class | `sum(rate(gateway_requests_total{status_class="5xx"}[5m])) / sum(rate(gateway_requests_total[5m]))` |
| Saturation | `gateway_rate_limited_total` | counter | route | `sum by (route) (rate(gateway_rate_limited_total[5m]))` |

## reservation-service

| Signal | Metric | Type | Labels | PromQL |
|--------|--------|------|--------|--------|
| Latency | `reservation_request_duration_seconds` | histogram | route | `histogram_quantile(0.95, sum by (le) (rate(reservation_request_duration_seconds_bucket{route="/reservations"}[5m])))` |
| Traffic | `reservation_requests_total` | counter | route, outcome | `sum(rate(reservation_requests_total[5m]))` |
| Errors | `reservation_requests_total` | counter | route, outcome | `sum(rate(reservation_requests_total{outcome="error"}[5m])) / sum(rate(reservation_requests_total[5m]))` |
| Saturation | `hikaricp_connections_pending` | gauge | pool | `max(hikaricp_connections_pending{pool="reservation"})` |

Business rejections (409 `RESERVATION_SLOT_UNAVAILABLE`, 422 `BUSINESS_RULE_VIOLATION`) use
`outcome="rejected"`. They are counted as traffic but NOT as errors: the service worked.

## court-service

| Signal | Metric | Type | Labels | PromQL |
|--------|--------|------|--------|--------|
| Latency | `court_slots_request_duration_seconds` | histogram | route | `histogram_quantile(0.95, sum by (le) (rate(court_slots_request_duration_seconds_bucket[5m])))` |
| Traffic | `court_requests_total` | counter | route, outcome | `sum by (route) (rate(court_requests_total[5m]))` |
| Errors | `court_requests_total` | counter | route, outcome | `sum(rate(court_requests_total{route="/courts/{id}/bookable-slots",outcome="error"}[5m]))` |
| Saturation | `jvm_threads_live_threads` | gauge | (none) | `max(jvm_threads_live_threads{service="court-service"})` |

## payment-service and RabbitMQ (async part)

| Signal | Metric | Type | Labels | PromQL |
|--------|--------|------|--------|--------|
| Latency | `payment_event_lag_seconds` | histogram | event_type | `histogram_quantile(0.95, sum by (le) (rate(payment_event_lag_seconds_bucket{event_type="ReservationCreated"}[5m])))` |
| Traffic | `payment_events_consumed_total` | counter | event_type, outcome | `sum(rate(payment_events_consumed_total{event_type="ReservationCreated"}[5m]))` |
| Errors | `payment_events_consumed_total` | counter | event_type, outcome | `sum(rate(payment_events_consumed_total{outcome="dlq"}[5m]))` |
| Saturation | `rabbitmq_queue_messages_ready` | gauge | queue | `max(rabbitmq_queue_messages_ready{queue="payment.reservation-created"})` |

`payment_event_lag_seconds` = time between `occurredAt` of `ReservationCreated` and the moment
payment-service stores the `PENDING` payment. It shows a slow consumer before the queue grows.

## Label cardinality

| Label | Values | Estimate |
|-------|--------|----------|
| route | route templates only: `/reservations`, `/availability`, `/courts/{id}/bookable-slots`, ... | ~12 |
| method | GET, POST | 2 |
| status_class | 2xx, 4xx, 5xx | 3 |
| outcome | success, rejected, error (and dlq for consumers) | 4 |
| event_type | ReservationCreated, PaymentApproved, PaymentRejected | 3 |
| queue / pool | one per service | < 5 |

Worst case for `gateway_requests_total`: 12 x 2 x 3 = 72 series. Fine for Prometheus.

**Forbidden labels** (unbounded):

| Forbidden | Why | Replaced by |
|-----------|-----|-------------|
| `reservation_code`, `reservation_id` | one new value per reservation | log field `correlation_id` / `reservation_id` |
| `customer_email`, `customer_phone` | personal data and unbounded | not stored anywhere in metrics |
| raw URL (`/courts/7c0f.../bookable-slots`) | one series per court id and date | route template `/courts/{id}/bookable-slots` |
| `court_id` | only 4 courts today, but it grows with the business | log field `court_id`; dashboards by route |
| `trace_id` | unique per request | logs and traces, never metrics |

## Latency histogram buckets

Buckets for `reservation_request_duration_seconds` and `gateway_request_duration_seconds`:

`0.05, 0.1, 0.25, 0.5, 0.75, 1, 1.5, 2, 3, 5` (seconds)

Why: a customer booking a court from the web app expects the "Reserve" button to answer in
about 1 second. Most requests should land under 0.5 s (one REST call to court-service plus one
insert). The 0.75, 1 and 1.5 buckets give detail around the target. 3 s matches the timeout
to court-service used in `02-log-contract.md`, so a downstream timeout falls into the 3-5 s
bucket and is easy to see. Nothing above 5 s is useful: the gateway cuts the request before.
