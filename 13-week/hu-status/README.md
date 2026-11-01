<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       13-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 13

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Hernando Antonio Martín Herrera
- GITHUB_USER: HERMAR55611
- TEAM: Futbolix
- SPRINT_GOAL: Threat model of trust boundary TB1 (React web app -> api-gateway) and turn its main threats into security stories and a negative authorization test.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| #48 | Threat model: React web app -> api-gateway (claim) | done | https://github.com/code-corhuila/ftx-docs/issues/48 |
| PR #49 | STRIDE for TB1, reviewed by @emelendez20201-ship-it, @soymiguelgomez and @Dannamo34 | done (open for review) | https://github.com/code-corhuila/ftx-docs/pull/49 |
| #51 | [security] Limit PENDING reservations created from the internet (P0) | todo | https://github.com/code-corhuila/ftx-docs/issues/51 |
| #52 | [security] Only ADMINISTRATOR can call /admin routes (P1) | doing | https://github.com/code-corhuila/ftx-docs/issues/52 |
| #53 | [security] Protect reservation cancellation by code (P1) | todo | https://github.com/code-corhuila/ftx-docs/issues/53 |

## 2. My individual contribution
- Mapped the attack surface of Futbolix (`01-attack-surface.md`): all services, PostgreSQL, RabbitMQ, Wompi and five trust boundaries; I claimed TB1.
- Wrote the STRIDE table for TB1 in PR #49 with 8 threats, risk ranking, residual risk and the library book cited with pages ([B] rows).
- Answered three reviews: rate limit per IP on cancellation (row 1), forged X-User-Role header (row 7), XSS reading the token in sessionStorage (row 8) and the role name ADMINISTRATOR.
- Created three security stories (#51 P0, #52 P1, #53 P1) with testable criteria, labels and the Futbolix - Backlog board.
- Proposed `security.yml` (gitleaks, OWASP dependency-check, authorization tests) and wrote a negative test for court-service `PATCH /admin/courts/{id}`: 4 tests pass (403 without token, with CUSTOMER token and with a forged token).
- Reviewed the STRIDE PR #59 of @emelendez20201-ship-it (webhook replay inside the time window and the contract path).

## 3. Blockers and risks
- The final delivery in Moodle closed on Friday 23:59 before I could submit; I asked the teacher by email to enable it.
- The negative test runs on a minimal court-service module; the real court-service and api-gateway repositories are still empty.
- The access token stays in sessionStorage for MVP 3 (row 8 residual risk); moving it to an HttpOnly cookie needs changes in api-gateway and ftx-front.

## 4. Plan for next week
- Implement #51 first (P0): rate limit per IP on `POST /reservations` at the api-gateway and expiry of PENDING reservations.
- Move the authorization filter and its negative test into the real court-service and add `security.yml` to the service pipeline.
- Defend the work in class (two questions).

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [x] Testable acceptance criteria
- [x] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

## 6. Evidence links
- Workshop folder: https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/tree/main/13-week/workshop
- STRIDE PR: https://github.com/code-corhuila/ftx-docs/pull/49
- Review comment on PR #59: https://github.com/code-corhuila/ftx-docs/pull/59/changes#r4432509349
- Negative test output: https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/blob/main/13-week/workshop/04-security-ci/HOW-TO-RUN.md
