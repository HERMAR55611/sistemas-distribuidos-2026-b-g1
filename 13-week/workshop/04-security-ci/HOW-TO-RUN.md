# How to run - security gates and negative authorization test

## What it covers

- Story: [#52 [security] Only ADMINISTRATOR can call /admin routes](https://github.com/code-corhuila/ftx-docs/issues/52)
  (STRIDE E, risk 6, P1, PR #49 rows 6 and 7).
- Acceptance criterion: `PATCH /admin/courts/{id}` without a JWT, with a CUSTOMER JWT or with a
  forged ADMINISTRATOR JWT returns 403 (automated negative test in CI).
- Service and endpoint: court-service, `PATCH /admin/courts/{id}` (`07-api/contracts/openapi/court-service.yaml`).

## Files

| File | What it does |
|------|--------------|
| `security.yml` | GitHub Actions proposal for the Futbolix services: gitleaks, OWASP dependency-check (fails on CVSS >= 7) and the authorization tests, on every pull request to develop, qa and main |
| `court-service/.../AdminCourtAuthorizationTest.java` | 3 negative cases (no token, CUSTOMER token, ADMINISTRATOR token signed with a wrong key) must get 403; 1 positive case with a real ADMINISTRATOR token gets 200 |
| `court-service/.../AdminRoleFilter.java` | Minimal filter: `/admin/*` only with a valid signed JWT whose role is ADMINISTRATOR |

## Run locally

Docker Desktop running. Windows: double click `run-tests.bat`, or:

```
cd court-service
docker run --rm -v "%cd%":/app -v futbolix-m2:/root/.m2 -w /app maven:3.9-eclipse-temurin-21 mvn -B test -Dtest=AdminCourtAuthorizationTest
```

## Workflow validation

`security.yml` checked with yamllint.com on 2026-10-31: Valid YAML. The first check showed that the step
name `Negative authorization test (issue #52)` was cut at `#` (YAML comment); fixed by quoting it.
Jobs: secret-scan, dependency-audit, authorization-tests (runs after secret-scan), each with `timeout-minutes: 10`.

## Real console output (my machine, 2026-10-31)

```
[INFO] Running co.futbolix.court.AdminCourtAuthorizationTest
2026-10-31T16:58:43.471Z  INFO 86 --- [           main] c.f.court.AdminCourtAuthorizationTest    : Started AdminCourtAuthorizationTest in 7.859 seconds (process running for 11.874)
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 15.70 s -- in co.futbolix.court.AdminCourtAuthorizationTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  24.107 s
[INFO] Finished at: 2026-10-31T16:58:48Z
[INFO] ------------------------------------------------------------------------
```
