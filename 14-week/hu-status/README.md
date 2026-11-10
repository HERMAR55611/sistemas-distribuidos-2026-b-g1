<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.
     Your weekly grade is read AUTOMATICALLY from this file:
       14-week/hu-status/README.md  (inside YOUR fork). English. -->

# Weekly Status - Week 14

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->
- FULL_NAME: Hernando Antonio Martín Herrera
- GITHUB_USER: HERMAR55611
- TEAM: Futbolix
- SPRINT_GOAL: Prepare the defence of the observability and security work of Futbolix and keep the corte 3 certification on schedule.
<!-- CONFIG-END -->

## 1. User stories worked this week
| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|---|---|---|---|
| W13 defence | Prepare the defence of the TB1 threat model and the week 11 observability work | done | https://github.com/code-corhuila/ftx-docs/pull/49 |
| #51 | [security] Limit PENDING reservations created from the internet (P0) | todo | https://github.com/code-corhuila/ftx-docs/issues/51 |
| #52 | [security] Only ADMINISTRATOR can call /admin routes (P1) | todo | https://github.com/code-corhuila/ftx-docs/issues/52 |
| #53 | [security] Protect reservation cancellation by code (P1) | todo | https://github.com/code-corhuila/ftx-docs/issues/53 |

## 2. My individual contribution
- Prepared the defence of the project risks: the STRIDE of TB1 (flood of PENDING reservations with risk 9, forged X-User-Role header, XSS on the token in sessionStorage) and how traceparent travels in the AMQP headers of ReservationCreated.
- Reviewed the week 11 and week 13 deliverables of my fork to answer questions about them.
- I did not open or review pull requests in the team repositories this week.

## 3. Blockers and risks
- Most of my time this week went to the corte 3 certification (HCCDP-Solution Architectures, chapters 2 to 6), so the security stories #51 to #53 did not move.
- The service repositories are still mostly empty, so the stories cannot be implemented in the real services yet.

## 4. Plan for next week
- Review the open pull requests of the team as tech lead and prepare release v3.0.0.

## 5. Compliance self-check
- [x] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
- [x] Testable acceptance criteria
- [ ] Tests added/updated (unit / integration)
- [x] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

## 6. Evidence links
- STRIDE PR: https://github.com/code-corhuila/ftx-docs/pull/49
- Security stories: https://github.com/code-corhuila/ftx-docs/issues/51 , https://github.com/code-corhuila/ftx-docs/issues/52 , https://github.com/code-corhuila/ftx-docs/issues/53
