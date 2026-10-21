# CP2 - Structured log contract

All Futbolix services (api-gateway, reservation-service, court-service, payment-service) write
one JSON object per line to stdout, with the same field names. With this, one query by
`trace_id` returns the full story of a reservation across services.

## Mandatory fields

| Field | Type | Required | Example | Why it exists |
|-------|------|----------|---------|---------------|
| timestamp | string, ISO-8601 UTC | yes | `2026-10-20T23:41:05.112Z` | Order events across services in one timeline |
| level | string | yes | `INFO` | Filter by severity |
| service | string | yes | `reservation-service` | Know which container wrote the line |
| version | string | yes | `2.0.0` | Link a problem to a release tag |
| environment | string | yes | `qa` | Separate dev, qa and main |
| trace_id | string, 32 hex | yes | `7d1c9a3e5b2f4c60a8e1d2b3c4f5a6b7` | Join all lines of one request (W3C traceparent) |
| span_id | string, 16 hex | yes | `a1b2c3d4e5f60718` | Know which hop wrote the line |
| correlation_id | string | yes | `FTX7Q2K9M4` | Business id the support team can search (reservation code) |
| event | string, dot.case | yes | `reservation.created` | Stable name for dashboards and alerts |
| message | string | yes | `reservation stored as PENDING` | Short human text |
| duration_ms | integer | yes on request end / outgoing call | `184` | Latency of that step |

## Domain fields (Futbolix)

| Field | Type | Required | Example | Why it exists |
|-------|------|----------|---------|---------------|
| reservation_id | uuid | optional | `5f0c...e21` | Id of the `Reservation` aggregate in schema `reservation` |
| court_id | uuid | optional | `7c0f4c3e-2a55-4a3b-9f0d-1b8f6e2a9c11` | Which of the four courts was requested |
| reservation_date | date | optional | `2026-10-25` | Detect problems for one day (schedule, double booking) |
| slot | string | optional | `18:00-20:00` | Whole-hour range requested (BR-004) |
| payment_id | uuid | optional | `9a1e...4b0` | Id of the `Payment` aggregate in payment-service |
| event_id | uuid | optional | `3b6d...91c` | `eventId` of `ReservationCreated`; idempotency key in `processed_events` |
| http_status | integer | optional | `201` | Result of the HTTP call |
| error_code | string | optional | `RESERVATION_SLOT_UNAVAILABLE` | Same `error` value as the `ErrorResponse` in `_shared.yaml` |

## Never log

| Data | Where it appears in Futbolix | How it is masked |
|------|------------------------------|------------------|
| customerName | `POST /reservations` body, `ReservationCreated` payload | Not logged. Only `reservation_id` |
| customerEmail | same | Masked: `j***@example.com` only in DEBUG, never in INFO |
| customerPhone | `POST /reservations` body | Not logged at all |
| JWT access / refresh token | `Authorization` header, auth-service | Not logged. Log only `token_present=true` |
| Admin password | `POST /auth/login` | Never logged, not even masked |
| Wompi keys and webhook signature | payment-service | Not logged. Log only `signature_valid=true/false` |
| Idempotency-Key header value | `POST /reservations` | Log only the first 8 chars |

## Example: one failed reservation (same trace_id)

```json
{"timestamp":"2026-10-20T23:41:05.112Z","level":"INFO","service":"api-gateway","version":"1.0.0","environment":"qa","trace_id":"7d1c9a3e5b2f4c60a8e1d2b3c4f5a6b7","span_id":"a1b2c3d4e5f60718","correlation_id":"req-20231","event":"http.request.received","message":"POST /reservations","duration_ms":0,"http_status":null}
{"timestamp":"2026-10-20T23:41:05.130Z","level":"INFO","service":"reservation-service","version":"2.0.0","environment":"qa","trace_id":"7d1c9a3e5b2f4c60a8e1d2b3c4f5a6b7","span_id":"b2c3d4e5f6071829","correlation_id":"req-20231","event":"reservation.requested","message":"create reservation requested","duration_ms":0,"court_id":"7c0f4c3e-2a55-4a3b-9f0d-1b8f6e2a9c11","reservation_date":"2026-10-25","slot":"18:00-20:00"}
{"timestamp":"2026-10-20T23:41:08.141Z","level":"WARN","service":"reservation-service","version":"2.0.0","environment":"qa","trace_id":"7d1c9a3e5b2f4c60a8e1d2b3c4f5a6b7","span_id":"c3d4e5f60718293a","correlation_id":"req-20231","event":"court.slots.timeout","message":"court-service did not answer bookable-slots, retrying once","duration_ms":3002,"court_id":"7c0f4c3e-2a55-4a3b-9f0d-1b8f6e2a9c11"}
{"timestamp":"2026-10-20T23:41:08.322Z","level":"INFO","service":"court-service","version":"1.0.0","environment":"qa","trace_id":"7d1c9a3e5b2f4c60a8e1d2b3c4f5a6b7","span_id":"d4e5f60718293a4b","correlation_id":"req-20231","event":"court.slots.listed","message":"bookable slots returned","duration_ms":161,"court_id":"7c0f4c3e-2a55-4a3b-9f0d-1b8f6e2a9c11","http_status":200}
{"timestamp":"2026-10-20T23:41:08.351Z","level":"WARN","service":"reservation-service","version":"2.0.0","environment":"qa","trace_id":"7d1c9a3e5b2f4c60a8e1d2b3c4f5a6b7","span_id":"b2c3d4e5f6071829","correlation_id":"req-20231","event":"reservation.rejected","message":"slot overlaps an active reservation","duration_ms":3221,"court_id":"7c0f4c3e-2a55-4a3b-9f0d-1b8f6e2a9c11","slot":"18:00-20:00","http_status":409,"error_code":"RESERVATION_SLOT_UNAVAILABLE"}
{"timestamp":"2026-10-20T23:41:08.360Z","level":"INFO","service":"api-gateway","version":"1.0.0","environment":"qa","trace_id":"7d1c9a3e5b2f4c60a8e1d2b3c4f5a6b7","span_id":"a1b2c3d4e5f60718","correlation_id":"req-20231","event":"http.request.completed","message":"POST /reservations answered","duration_ms":3248,"http_status":409}
{"timestamp":"2026-10-20T23:41:08.362Z","level":"ERROR","service":"api-gateway","version":"1.0.0","environment":"qa","trace_id":"7d1c9a3e5b2f4c60a8e1d2b3c4f5a6b7","span_id":"a1b2c3d4e5f60718","correlation_id":"req-20231","event":"http.request.slow","message":"request over 3000 ms budget","duration_ms":3248,"http_status":409}
```

Story: the request enters the gateway, reservation-service asks court-service for slots, the
first call times out after 3 s (downstream timeout), the retry answers, and the reservation is
rejected because the slot overlaps an active one (business rule, 409).

## Log levels in Futbolix

| Level | When | Futbolix example |
|-------|------|------------------|
| ERROR | The request or event failed and needs a person | payment-service cannot store the PENDING payment; message goes to the DLQ |
| WARN | Something went wrong but the system handled it | court-service timeout with retry; 409 `RESERVATION_SLOT_UNAVAILABLE` |
| INFO | Normal business milestone, one per step | `reservation.created`, `payment.pending.created` |
| DEBUG | Detail for local troubleshooting, off in qa and main | full `bookable-slots` response with the list of slots |
