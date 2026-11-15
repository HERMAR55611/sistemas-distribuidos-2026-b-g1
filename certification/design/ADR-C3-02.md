# ADR-C3-02: Move PostgreSQL to RDS for PostgreSQL in primary/standby mode

## Status

Proposed

## Context

Today one PostgreSQL instance in a container holds the four schemas of Futbolix (user, court,
reservation and payment). The architecture overview of our team registers this as technical
debt AT-001: a single point of failure. If it goes down, users cannot see courts, reserve or
pay. Sections 2 and 4 of the design note set an RTO of 30 minutes and an RPO of 5 minutes for
the reservation data, and a container with no standby cannot meet them.

## Decision

I move the database to RDS for PostgreSQL in primary/standby mode, with the primary in one
availability zone and the standby in the other. RDS replicates every change to the standby,
promotes it automatically when the primary fails, and keeps automatic backups with
point-in-time recovery. Each service keeps its own schema and its own database user, as
ADR-003 of our team says.

## Alternatives considered

- Keep PostgreSQL in a container with a daily backup: rejected because a daily backup can
  lose up to 24 hours of reservations, far from the RPO of 5 minutes, and recovery is manual.
- One RDS instance per service: rejected because it multiplies the cost by four for a system
  with only four courts, while separate schemas and users already isolate the data.

## Consequences

- Positive: the database survives the loss of one availability zone, and backups and
  point-in-time recovery are automatic, so the RTO and RPO are met.
- Negative: RDS costs more than a container, and the four schemas still share one instance, so
  a heavy load in one schema can slow down the others.
