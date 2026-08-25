# Weekly Status - Week 03

**FULL_NAME:** Hernando Antonio Martin Herrera
**GITHUB_USER:** HERMAR55611
**TEAM:** Futbolix
**SPRINT_GOAL:** Complete the discovery phase — fully document `01-context`, `02-domain`,
and `03-product`, aligning the whole team on the domain, scope, and product vision before
starting requirements and architecture.

---

## 1. User stories worked this week

> No formal user stories have been created yet (`04-requirements/user-stories.md` is planned
> for next week). This week's work was documentation-only, so it is logged with descriptive
> IDs instead of service-specific HU-XXX codes.

| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
|-------|-------|---------------------------|------------------------------|
| DOCS-001 | Complete `01-context` (overview, scope, glossary) | done | https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/tree/main/03-week/hu-status |
| DOCS-002 | Complete `02-domain` (domain-map, entities-and-rules, domain-events) | done | https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/tree/main/03-week/hu-status |
| DOCS-003 | Complete `03-product` (problem-framing, vision) | done | https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/tree/main/03-week/hu-status |
| DOCS-004 | Review teammates' `03-product` files for consistency with `02-domain` | done | https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/tree/main/03-week/hu-status |

---

## 2. My individual contribution

- Defined and documented the full `01-context` folder: system overview, in/out of scope,
  and glossary for Futbolix (4 football courts, single sports complex).
- Defined and documented the full `02-domain` folder: 5 bounded contexts (User, Court,
  Reservation, Payment, Notification), entities and business rules (including the
  no-double-booking invariant), and the domain event catalog.
- Adjusted the notification design mid-sprint: replaced the immediate WhatsApp confirmation
  with a time-triggered reminder sent one hour before the reservation (new domain event
  `ReservationReminderDue`), and propagated this change across `01-context` and `02-domain`.
- Reviewed teammates' `vision.md` and `problem-framing.md` (`03-product`) and corrected
  inconsistencies: named the payment gateway explicitly (Wompi), aligned the WhatsApp
  reminder behavior, and unified terminology (`court` instead of `field`).

---

## 3. Blockers and risks

- The team is still building familiarity with DDD/microservices terminology; some
  confusion slowed down reviewing `02-domain` and `03-product`.
- `00-governance` is not finalized yet (sprint duration, backlog tool, and team size are
  still pending), which may delay setting up the actual sprint board for `04-requirements`.
- Risk: without `04-requirements` yet, this week's "user stories" are documentation tasks
  only, not real HUs tied to a service — this must not be mistaken for sprint progress on
  the backlog.

---

## 4. Plan for next week

- Finish `00-governance` (agile-conventions.md pending team inputs).
- Start `04-requirements/user-stories.md` with the first 10-15 MVP user stories, derived
  directly from `03-product/problem-framing.md` (customer and administrator capabilities).
- Start `04-requirements/non-functional.md` with basic, realistic NFRs for an academic MVP.

---

## 5. Compliance self-check

- [ ] Conventional Commits - `type(scope): summary`
- [ ] Per-environment HU branch + PR to that environment (`hu-xxx-dev` -> `develop`, ...)
- [ ] Testable acceptance criteria
- [ ] Tests added/updated (unit / integration)
- [ ] DDD / hexagonal boundaries respected (domain has no I/O)
- [x] No secrets; config via environment variables

> Most boxes are unchecked because no code has been written yet — this week was
> documentation-only (discovery phase). They will start applying once `04-requirements`
> and implementation begin.

---

## 6. Evidence links

- `01-context`, `02-domain`, `03-product` folders (this week's work):
  https://github.com/HERMAR55611/sistemas-distribuidos-2026-b-g1/tree/main/03-week/hu-status
