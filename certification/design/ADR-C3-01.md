# ADR-C3-01: Run the five Futbolix services on CCE with autoscaling

## Status

Proposed

## Context

Futbolix has five services (api-gateway, user-service, court-service, reservation-service and
payment-service) that today run with Docker Compose on one machine. If that machine fails,
the whole system stops, and no service can grow when many users reserve at the same time.
Sections 1 and 3 of the design note show that api-gateway and reservation-service receive the
most traffic and need to scale on their own.

## Decision

I run the five services as containers on CCE, the managed Kubernetes of Huawei Cloud, with the
worker nodes spread over two availability zones. Each service has at least two replicas, one
per zone, and HPA adds replicas when the average CPU of the pods goes above 70 % (up to 6 for
api-gateway and 8 for reservation-service). The images come from SWR.

## Alternatives considered

- ECS virtual machines with Docker Compose: rejected because I would have to install, patch
  and watch each machine by hand, and scaling one service means scaling a whole machine.
- CCI (serverless containers): rejected because the team already works with Kubernetes-style
  deployments and health checks, and CCI gives less control over the network between services
  and the rolling updates we need for rollback.

## Consequences

- Positive: each service scales on its own demand, failed containers restart automatically,
  and losing one availability zone does not stop the system.
- Negative: the team must learn Kubernetes manifests and pay for a cluster that runs all the
  time, which costs more than one machine with Docker Compose.
