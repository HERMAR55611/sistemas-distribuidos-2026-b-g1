<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.

Your weekly grade is read AUTOMATICALLY from this file:
07-week/hu-status/README.md (inside YOUR fork). English. -->

# Weekly Status - Week 07

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->

* FULL_NAME: Hernando Antonio Martín Herrera
* GITHUB_USER: HERMAR55611
* TEAM: Futbolix
* SPRINT_GOAL: Define and document inter-service communication for Futbolix using REST and RabbitMQ, and establish the basis for versioned API contracts and contract testing.

<!-- CONFIG-END -->

## 1. User stories worked this week

| HU ID | Title | Status (todo/doing/done) | Evidence (PR or commit URL) |
| ---------- | ------------------------------------ | ------------------------ | --------------------------- |
| HU-COM-001 | Define synchronous REST communication | done | https://github.com/code-corhuila/ftx-docs/pull/26 |
| HU-COM-002 | Define asynchronous communication with RabbitMQ | done | https://github.com/code-corhuila/ftx-docs/pull/24 |
| HU-COM-003 | Document microservice event ownership | done | https://github.com/code-corhuila/ftx-docs/pull/24 |
| HU-COM-004 | Document reservation and payment communication flow | done | https://github.com/code-corhuila/ftx-docs/pull/26 |
| HU-COM-005 | Define microservice data ownership | done | https://github.com/code-corhuila/ftx-docs/pull/19 |
| HU-COM-006 | Define reusable microservice documentation structure | done | https://github.com/code-corhuila/ftx-docs/pull/18 |
| HU-COM-007 | Document service-level architectural decisions | done | https://github.com/code-corhuila/ftx-docs/pull/21 |
| HU-COM-008 | Establish the operational runbook structure | done | https://github.com/code-corhuila/ftx-docs/pull/25 |
| HU-API-001 | Review API contract documentation structure | doing | 07-api/contracts/openapi/ |
| HU-API-002 | Define versioned API contract strategy | doing | 07-api/contracts/openapi/ |
| HU-API-003 | Plan contract testing | todo | - |

## 2. My individual contribution

* Participated in the documentation and architectural definition of the Futbolix microservices.
* Documented the communication model between the current Futbolix services.
* Supported the definition of synchronous communication using HTTP/REST.
* Supported the definition of asynchronous communication using RabbitMQ for reservation and payment events.
* Documented event ownership, publishers, consumers and communication responsibilities.
* Updated the reusable microservice documentation templates for README, data model, decisions, events and runbook.
* Updated the Futbolix microservices catalog with service responsibilities, ports, communication relationships and data ownership.
* Maintained the documentation according to the current bounded contexts and Hexagonal Architecture.
* Used Pull Requests to maintain traceability of the documentation changes.
* Reviewed the basis for versioned API contracts and future contract testing.

## 3. Blockers and risks

* The documentation repository branch model requires validation against the course-specific branching convention before final integration.
* API contracts must remain synchronized with the responsibilities and boundaries of each microservice.
* Changes to service boundaries may require updates across domain, architecture, API and microservice documentation.
* Event contracts require consistent ownership, versioning and traceability between publishers and consumers.
* Contract testing has not yet been implemented and remains a future technical activity.
* The distinction between infrastructure components and business bounded contexts must be maintained to avoid incorrect service boundaries.

## 4. Plan for next week

* Continue the definition and refinement of OpenAPI contracts for the Futbolix services.
* Establish the versioning convention for API contracts.
* Define the initial strategy for consumer-provider contract testing.
* Validate REST communication contracts between services.
* Review the RabbitMQ event contracts between reservation and payment services.
* Maintain traceability between requirements, architecture decisions, API contracts and microservice documentation.
* Continue documenting implementation evidence through commits and Pull Requests.

## 5. Compliance self-check

* [x] Conventional Commits - `type(scope): summary`
* [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
* [x] Testable acceptance criteria
* [ ] Tests added/updated (unit / integration)
* [x] DDD / hexagonal boundaries respected (domain has no I/O)
* [x] No secrets; config via environment variables

## 6. Evidence links

* Futbolix repository: https://github.com/HERMAR55611
* Microservices documentation: `09-microservices/`
* Microservices catalog: `09-microservices/service-catalog.md`
* Microservice template: `09-microservices/_template/service/`
* API contracts: `07-api/contracts/openapi/`
* Domain documentation: `02-domain/`
* Architecture documentation: `05-architecture/`
* UX/UI documentation: `12-ux-ui/`

## 7. Weekly result

During Week 07, the Futbolix team defined and documented the main communication mechanisms between microservices.

Synchronous communication was documented using REST, particularly for interactions involving the reservation and court services. Asynchronous communication was documented using RabbitMQ for the reservation and payment flow.

The team also strengthened the reusable microservice documentation by defining service responsibilities, data ownership, event ownership, architectural decisions and operational guidance.

Versioned API contracts and contract testing were identified as the next technical activities. These activities remain in progress or planned because their complete technical implementation was not part of the evidence produced during this week.
