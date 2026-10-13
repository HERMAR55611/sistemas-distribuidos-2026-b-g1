<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       10-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 10

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Hernando Antonio Martín Herrera
- GITHUB_USER: HERMAR55611
- TEAM: Futbolix
- SPRINT_GOAL: Ship MVP 2 (release 2.0.0): build the schedules database with Flyway on the single PostgreSQL instance (Annex J) and promote it through develop, qa and main with cherry-pick -x.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-ADM-001 | Manage courts, schedules, prices and status — schedules database (ftx-schedules-db) | done | https://github.com/code-corhuila/ftx-schedules-db/pull/2 · https://github.com/code-corhuila/ftx-schedules-db/pull/3 · https://github.com/code-corhuila/ftx-schedules-db/pull/4 · https://github.com/code-corhuila/ftx-schedules-db/pull/5 · https://github.com/code-corhuila/ftx-schedules-db/releases/tag/v2.0.0 |

## 2. My individual contribution
- Built `ftx-schedules-db` following Annex A (Flyway) and Annex J (single database): schema `schedules`, table `court_schedule` with named CHECK constraints (days, opening < closing, whole hours), unique index per court and day, `updated_at` trigger and an idempotent seed (4 courts x 7 days, 06:00-22:00).
- Wrote one `U<n>` rollback script per `V<n>` migration in `05_rollbacks/`, and NOLOGIN roles `schedules_reader` / `schedules_writer` granted to `schedules_app` (least privilege, no access to the migration history).
- Configured Flyway with its own schema and control table (`schedules.flyway_schema_history`) so it does not collide with other domains in the single database (Annex J.6).
- Added the `db-ci` workflow: migrate from an empty database, second migrate applies nothing, rollback with the U scripts, migrate again, 11 post-migration checks and the Annex J.10 privilege query. Fixed a Flyway TOML configuration error found by the CI.
- Promoted the change per environment: PR #2 to develop, `git cherry-pick -x` to `hu-adm-001-qa` (PR #3 to qa), `git cherry-pick -x` to `hu-adm-001-release` (PR #4 to release.2.0.0) and PR #5 release.2.0.0 → main, approved by the code owner.
- Published release `v2.0.0` on `main` (commit 5920344f4b3b206fa7c54b497d00917badcfd05b) and delivered its zip in c2-release.

## 3. Blockers and risks
- The review bot raised findings on PR #5 (silent grant when `schedules_app` does not exist yet, no CI on main, court-id contract with `ftx-courts-db` not verified automatically, PR size, manual step in U001). Each one is answered in the PR with a decision.
- `ftx-infra-postgres` must create the login user `schedules_app` in its init script before the migrations run.
- The court ids in the seed are a contract with `ftx-courts-db`, which is still empty.
- Most service repositories (api, portal, worker, workflow) are still empty; MVP 2 relies on the repositories that reached main.

## 4. Plan for next week
- Apply the accepted bot findings in a new release (2.0.1): fail-loud check for the `schedules_app` grant and the history cleanup inside U001.
- Coordinate with the team so `ftx-infra-postgres` includes the schedules migration runner and creates `schedules_app`.
- Start `ftx-schedules-api` (hexagonal) reading `schedules.court_schedule` for the availability user story (HU-RES-001).
- Record in an ADR in `ftx-docs` the Flyway choice and the split of schedules from court-service.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [x] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [x] Testable acceptance criteria
- [x] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

## 6. Evidence links
- PR to develop: https://github.com/code-corhuila/ftx-schedules-db/pull/2
- PR to qa (cherry-pick -x): https://github.com/code-corhuila/ftx-schedules-db/pull/3
- PR to release.2.0.0 (cherry-pick -x): https://github.com/code-corhuila/ftx-schedules-db/pull/4
- PR release.2.0.0 to main (approved): https://github.com/code-corhuila/ftx-schedules-db/pull/5
- Release v2.0.0: https://github.com/code-corhuila/ftx-schedules-db/releases/tag/v2.0.0
- CI runs (db-ci): https://github.com/code-corhuila/ftx-schedules-db/actions
