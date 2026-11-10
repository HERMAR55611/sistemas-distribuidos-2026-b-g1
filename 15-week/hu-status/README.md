<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       15-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 15

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Hernando Antonio Martín Herrera
- GITHUB_USER: HERMAR55611
- TEAM: Futbolix
- SPRINT_GOAL: Prepare MVP 3 release v3.0.0: review and unblock the open pull requests of the team and check the release risks.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| ftx-docs#55 | Review: threat model TB4 reservation-service <-> RabbitMQ <-> payment-service | done | https://github.com/code-corhuila/ftx-docs/pull/55#pullrequestreview-5850473448 |
| ftx-front#6 | Review: enable the fourth court in the mock data | done | https://github.com/code-corhuila/ftx-front/pull/6#pullrequestreview-5850491773 |
| HU-RES-002 (ftx-front#8) | Review: reject reservations that start before the current time | done | https://github.com/code-corhuila/ftx-front/pull/8#pullrequestreview-5850518231 |
| HU-RES-002 (ftx-front#10) | Review: tests for reservation code, slot conflict, lookup 404 and admin guard | done | https://github.com/code-corhuila/ftx-front/pull/10#pullrequestreview-5850542367 |
| Release v3.0.0 | MVP 3 release of the team repositories | doing | - |

## 2. My individual contribution
- As tech lead I reviewed and approved the four pull requests waiting for me; each one now has two approvals and no conflicts with its base branch.
- In ftx-docs#55 I asked to keep the traceparent in the AMQP headers and to store the eventId in processed_events, so a duplicate ReservationCreated is not processed twice.
- In ftx-front#8 I checked that its dependency #7 was merged and flagged that the minute-level rule is still missing in HU-RES-002 and in the 422 of reservation-service.yaml.
- In ftx-front#10 I linked the tests to rows 1 and 6 of the TB1 STRIDE (same "not found" message for 404 and RequireAdmin).
- I checked why the ftx-docs workflows fail on main: it is a billing problem of the organization, not a code error.

## 3. Blockers and risks
- The CI of the code-corhuila repositories does not start: "recent account payments have failed or your spending limit needs to be increased" (GitHub Actions minutes). Tests must be run locally with Docker.
- Release risks: ftx-infra-postgres README must allow starting the system only by following it, and the seed data must not contain an administrator with a fixed password.

## 4. Plan for next week
- Merge ftx-front#8 and then #10 into develop with the author, promote to qa and main, and tag v3.0.0.
- Run the live demo with an injected failure and defend the release.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [x] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [x] Testable acceptance criteria
- [x] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

## 6. Evidence links
- Reviews requested to me: https://github.com/pulls/review-requested
- Failed workflow run (billing): https://github.com/code-corhuila/ftx-docs/actions/runs/42832354532
