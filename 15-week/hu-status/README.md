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
| HU-RES-002 | Reservations schema with engine-level double-booking guarantee (ftx-reservations-db) | done | https://github.com/code-corhuila/ftx-reservations-db/pull/1 |
| HU-RES-002 | Reservations API: idempotency, outbox events, admin guard (ftx-reservations-api) | done | https://github.com/code-corhuila/ftx-reservations-api/pull/1 |
| ADR-004 | API gateway: routing, header hygiene, rate limiting (ftx-api-gateway) | done | https://github.com/code-corhuila/ftx-api-gateway/pull/1 |
| ADR-010 | Platform composition and failure-injection demo (ftx-workflow) | done | https://github.com/code-corhuila/ftx-workflow/pull/1 |
| Release v3.0.0 | Promotion develop -> qa -> release.3.0.0 -> main (cherry-pick -x) | done | https://github.com/code-corhuila/ftx-reservations-api/pull/4 |
| Release v3.0.0 | Tag v3.0.0 on main in the four repositories | done | https://github.com/code-corhuila/ftx-reservations-api/releases/tag/v3.0.0 |
| Release v3.0.0 | CHANGELOG 3.0.0 and ADR-013 | done | https://github.com/code-corhuila/ftx-docs/pull/68 |

## 2. My individual contribution
- As tech lead I reviewed and approved the four pull requests waiting for me; each one now has two approvals and no conflicts with its base branch.
- In ftx-docs#55 I asked to keep the traceparent in the AMQP headers and to store the eventId in processed_events, so a duplicate ReservationCreated is not processed twice.
- In ftx-front#8 I checked that its dependency #7 was merged and flagged that the minute-level rule is still missing in HU-RES-002 and in the 422 of reservation-service.yaml.
- In ftx-front#10 I linked the tests to rows 1 and 6 of the TB1 STRIDE (same "not found" message for 404 and RequireAdmin).
- I checked why the ftx-docs workflows fail on main: it is a billing problem of the organization, not a code error.
- On Saturday Nov 14 I delivered my four repositories for MVP 3 (with AI assistance, commits co-authored): ftx-reservations-db, ftx-reservations-api, ftx-api-gateway and ftx-workflow, each with Conventional Commits on hu-res-002-dev and a PR to develop.
- I ran the tests locally with Docker because the CI is blocked: ftx-reservations-api `mvn verify` BUILD SUCCESS (8 concurrent requests for one slot leave exactly one reservation), ftx-api-gateway 10/10, ftx-reservations-db 17 checks. I fixed the failing tests before merging.
- I promoted the four repositories develop (#1) -> qa (#2) -> release.3.0.0 (#3) with `git cherry-pick -x` and opened the PRs to main (#4) for the instructor's approval.
- I recorded ADR-013 and the CHANGELOG 3.0.0 entry in ftx-docs (#68).
- On Sunday Nov 15, after the instructor approved the PRs #4, I merged them to main in the four repositories and created the release v3.0.0 on main in each one; ftx-docs#68 was merged too.
- I tried to run the ftx-infra-postgres platform of Edwin on my computer and sent him the literal result, so he could check his changes.

## 3. Blockers and risks
- The CI of the code-corhuila repositories does not start: "recent account payments have failed or your spending limit needs to be increased" (GitHub Actions minutes). Tests must be run locally with Docker.
- Release risks: ftx-infra-postgres README must allow starting the system only by following it, and the seed data must not contain an administrator with a fixed password.
- Docker Desktop does not start on my computer (engine error 500), so the team demo runs on Edwin's computer.
- Edwin needs write access to ftx-infra-postgres to push the reservations_app login user, README and runbook RB-001.

## 4. Plan for next week
- Defend the release on Tuesday Nov 17: team demo end to end with a controlled failure, and answer the individual questions about my contribution.
- Run the demo with the ftx-infra-postgres platform of Edwin, with my repositories taken from main (tag v3.0.0).
- Defend my Huawei certification design note in the week 16 class.

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
- My PRs in ftx-reservations-api: https://github.com/code-corhuila/ftx-reservations-api/pulls?q=author%3AHERMAR55611
- My PRs in ftx-api-gateway: https://github.com/code-corhuila/ftx-api-gateway/pulls?q=author%3AHERMAR55611
- Release v3.0.0: https://github.com/code-corhuila/ftx-reservations-api/releases/tag/v3.0.0
- ftx-docs#68 (ADR-013 and CHANGELOG 3.0.0): https://github.com/code-corhuila/ftx-docs/pull/68
