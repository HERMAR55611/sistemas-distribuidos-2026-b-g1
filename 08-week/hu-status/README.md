<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       08-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 08

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Hernando Antonio Martín Herrera
- GITHUB_USER: HERMAR55611
- TEAM: Futbolix
- SPRINT_GOAL: Mature the Futbolix API contract in ftx-docs 07-api: close open shared rules (errors, pagination, validation) and complete the service API descriptions, with one individual PR per student.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| HU-API-001 | Close shared API contract rules (07-api) | doing | https://github.com/code-corhuila/ftx-docs/pull/32 |
| HU-API-002 | Update user-service API description | done | https://github.com/code-corhuila/ftx-docs/pull/27 |
| HU-DOC-001 | Improve microservice runbook template | done | https://github.com/code-corhuila/ftx-docs/pull/25 |

## 2. My individual contribution
- Opened my individual PR to `ftx-docs` 07-api to close shared API contract rules, following the instructor's reference contract (closed decisions with justification, endpoint sheets, common error envelope, declared gaps with owner) (PR #32).
- Updated the user-service API description so it matches the authentication decision (administrators only) and the common contract (PR #27).
- Improved the microservice runbook template used by every service folder in `09-microservices/` (PR #25).

## 3. Blockers and risks
- PR #32 is approved by the review bot but not merged yet; the shared rules must be closed before services can implement the contract.
- Several service contracts still depend on decisions owned by other team members (payments, reservations).
- No service code exists yet: the contract is the prerequisite to start building and integrating microservices.

## 4. Plan for next week
- Merge PR #32 after applying the review recommendations, keeping the branch updated with main.
- Keep the data model (06-data) aligned with the API contract for the schedules and courts domains.
- Prepare the schedules database structure as my next individual contribution.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [x] Testable acceptance criteria
- [ ] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

## 6. Evidence links
- https://github.com/code-corhuila/ftx-docs/pull/32
- https://github.com/code-corhuila/ftx-docs/pull/27
- https://github.com/code-corhuila/ftx-docs/pull/25
