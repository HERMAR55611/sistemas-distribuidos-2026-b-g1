<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       16-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 16

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Hernando Antonio Martín Herrera
- GITHUB_USER: HERMAR55611
- TEAM: Futbolix
- SPRINT_GOAL: Present and defend release v3.0.0 with a live demo that includes injected failures, and leave a handover that lets another team run, operate and extend Futbolix.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| Release v3.0.0 | Run the whole platform from the clones of main with one command (database, migrations, broker, four APIs, gateway) | done | https://github.com/code-corhuila/ftx-workflow/releases/tag/v3.0.0 |
| Release v3.0.0 | Failure-injection demo `demo-falla.bat`: 20 PASS, 0 FAIL (broker down, schedules down, double booking, edge security) | done | https://github.com/code-corhuila/ftx-workflow/blob/main/scripts/demo-falla.ps1 |
| Release v3.0.0 | Final presentation and documentation set (README, architecture, ADRs, OpenAPI, runbooks, CHANGELOG) | done | https://github.com/code-corhuila/ftx-docs/pull/68 |
| Release v3.0.0 | Live defense of the release on Tuesday Nov 17 | doing | https://github.com/code-corhuila/ftx-reservations-api/releases/tag/v3.0.0 |
| Certification C3 | HCCDP-Solution Architectures: certificate, design note for Huawei Cloud and two ADRs | done | https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/tree/main/certification |

## 2. My individual contribution
- I started the whole Futbolix platform on my computer from the repositories on main (tag v3.0.0): PostgreSQL, the migrations of the four domains, RabbitMQ, customers, courts, schedules and reservations APIs and the gateway. The gateway health answered "ok" for the four services.
- I ran the release demo `demo-falla.bat` and got 20 PASS and 0 FAIL: with RabbitMQ down a reservation is still created and its event waits in the outbox until the broker returns; with ftx-schedules-api down availability fails fast with 503 while look-up and cancellation by code keep working; five simultaneous requests for one slot give one 201 and four 409; requests without a token, with a spoofed X-User-Role or with a client amount are rejected.
- I found and worked around two start-up problems that a new engineer would hit: the `platform` Docker network must exist before `start-platform.ps1` checks it (that check fails on Windows PowerShell 5.1), and port 8080 must be free (another local container was using it).
- I prepared the final presentation in the university template (problem, architecture and ADRs, reservation flow, resilience, live demo with failure, quality, metrics, lessons) and the documentation package of the project.
- I finished the Huawei certification: certificate with my name, design note that moves Futbolix to CCE, RDS for PostgreSQL and DMS for RabbitMQ, and two ADRs.

## 3. Blockers and risks
- GitHub Actions is still blocked by the billing of the organization, so the tests and the demo run locally with Docker.
- `start-platform.ps1` fails on Windows PowerShell 5.1 when the `platform` network does not exist yet; until it is fixed, run `docker network create platform` once.
- The demo needs ports 8080, 8081, 8082, 8083, 8085, 5432 and 15672 free on the computer that runs it.

## 4. Plan for next week
- Fix the network check of `ftx-workflow/scripts/start-platform.ps1` in a branch from develop and add a "Troubleshooting" section to its README (network and busy ports), so the handover works on any Windows computer.
- Final retrospective with the team: write down what made the system reliable (outbox, exclusion constraint, idempotency) and what we would do from day one next time (contracts first, CI that does not depend on paid minutes).
- Add the demo video and my contribution summary to my portfolio.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [x] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [x] Testable acceptance criteria
- [x] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

## 6. Evidence links
- Release v3.0.0 (reservations API): https://github.com/code-corhuila/ftx-reservations-api/releases/tag/v3.0.0
- Release v3.0.0 (gateway): https://github.com/code-corhuila/ftx-api-gateway/releases/tag/v3.0.0
- Release v3.0.0 (workflow and demo): https://github.com/code-corhuila/ftx-workflow/releases/tag/v3.0.0
- Documentation and ADR-013: https://github.com/code-corhuila/ftx-docs/pull/68
- Certification folder: https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/tree/main/certification
- Demo videos and transcript: https://corhuilaeduco-my.sharepoint.com/:f:/g/personal/hamartin20201_corhuila_edu_co/IgDze8F5fePiSKu8aZxmYcHsAdMPkuLfyo0ymFReANkHlzo?e=Y2WG4N
