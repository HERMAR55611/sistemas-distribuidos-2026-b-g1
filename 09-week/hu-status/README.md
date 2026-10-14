<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       09-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 09

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Hernando Antonio Martín Herrera
- GITHUB_USER: HERMAR55611
- TEAM: Futbolix
- SPRINT_GOAL: Follow up the API contract PR and prepare the data layer for MVP 2, applying the data isolation rules (each domain owns its schema, no cross-domain foreign keys).
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-API-001 | Close shared API contract rules (07-api) | doing | https://github.com/code-corhuila/ftx-docs/pull/32 |
| HU-ADM-001 | Manage courts, schedules, prices and status — schedules data model | doing | https://github.com/code-corhuila/ftx-schedules-db/tree/chore/schedules-db-baseline |

## 2. My individual contribution
- Followed up PR #32 (shared API contract rules) after the review bot approval, keeping it aligned with the team's service contracts.
- Reviewed the schedules data model from `06-data/models.md` against the instructor's data isolation warning (28/09): the schedules table references courts by id without a foreign key, because courts belong to another domain.
- Analysed the baseline branch `chore/schedules-db-baseline` to plan its adaptation to the norm (Flyway families, rollbacks, migration runner), which I delivered in week 10.

## 3. Blockers and risks
- PR #32 still waiting to be merged.
- The data isolation rule changed with Annex J (single database per engine, one schema per domain); the schedules repository had to be adapted before reaching main.
- Most service repositories were still empty, so MVP 2 had to focus on the repositories that could reach main.

## 4. Plan for next week
- Build `ftx-schedules-db` following Annex A and Annex J and promote it through develop, qa and main with `cherry-pick -x`.
- Add a CI workflow that rebuilds the schema from an empty database and reverts it.
- Tag the release `v2.0.0` for the c2 delivery.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [x] Testable acceptance criteria
- [ ] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

## 6. Evidence links
- https://github.com/code-corhuila/ftx-docs/pull/32
- https://github.com/code-corhuila/ftx-schedules-db/tree/chore/schedules-db-baseline
