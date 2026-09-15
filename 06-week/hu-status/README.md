<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.

```
 Your weekly grade is read AUTOMATICALLY from this file:
   06-week/hu-status/README.md  (inside YOUR fork). English. -->
```

# Weekly Status - Week 06

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->

* FULL_NAME: Hernando Antonio Martín Herrera
* GITHUB_USER: HERMAR55611
* TEAM: Futbolix
* SPRINT_GOAL: Plan and organize the implementation of the Futbolix user stories and prepare the development of the microservices following DDD and Hexagonal Architecture.

<!-- CONFIG-END -->

## 1. User stories worked this week

| HU ID      | Title                                | Status (todo/doing/done) | Evidence (PR or commit URL) |
| ---------- | ------------------------------------ | ------------------------ | --------------------------- |
| HU-CAN-001 | Consultar cancha                     | todo                     | -                           |
| HU-CAN-002 | Actualizar cancha                    | todo                     | -                           |
| HU-CAN-003 | Consultar estado de cancha           | todo                     | -                           |
| HU-RES-001 | Crear reserva                        | todo                     | -                           |
| HU-RES-002 | Consultar reserva                    | todo                     | -                           |
| HU-RES-003 | Cancelar reserva                     | todo                     | -                           |
| HU-RES-004 | Consultar disponibilidad             | todo                     | -                           |
| HU-CLI-001 | Registrar cliente                    | todo                     | -                           |
| HU-CLI-002 | Consultar cliente                    | todo                     | -                           |
| HU-CLI-003 | Actualizar cliente                   | todo                     | -                           |
| HU-CLI-004 | Consultar reservas del cliente       | todo                     | -                           |
| HU-HOR-001 | Consultar horarios                   | todo                     | -                           |
| HU-HOR-002 | Definir duración de horario          | todo                     | -                           |
| HU-HOR-003 | Consultar disponibilidad de horarios | todo                     | -                           |
| HU-HOR-004 | Bloquear horarios no disponibles     | todo                     | -                           |

## 2. My individual contribution

* Participé en la planeación y organización de las historias de usuario de Futbolix.
* Revisé la distribución de funcionalidades entre los dominios de Canchas, Reservas, Clientes y Horarios.
* Apoyé la definición de las actividades necesarias para iniciar la implementación de los microservicios.
* Revisé la relación entre las historias de usuario, los casos de uso y la Arquitectura Hexagonal.
* Apoyé la organización de las actividades técnicas relacionadas con TDD, SOLID, Clean Code y SDD.

## 3. Blockers and risks

* Aún se requiere validar la prioridad definitiva de las historias de usuario antes de iniciar su implementación.
* Algunas historias pueden presentar dependencias con los servicios de otros dominios.
* Se debe evitar duplicar responsabilidades entre microservicios.
* Es necesario mantener alineados los requisitos, dominios, contratos y arquitectura durante la implementación.

## 4. Plan for next week

* Priorizar las historias de usuario para iniciar el desarrollo.
* Definir los casos de uso correspondientes a las historias priorizadas.
* Preparar la estructura inicial de los microservicios.
* Implementar las primeras funcionalidades siguiendo Arquitectura Hexagonal.
* Definir y ejecutar las primeras pruebas bajo el enfoque TDD.
* Validar las fronteras de los dominios y mantener el dominio libre de I/O.
* Registrar las decisiones técnicas y avances en la documentación SDD.

## 5. Compliance self-check

* [ ] Conventional Commits - `type(scope): summary`
* [ ] Per-environment HU branch + PR to that environment (hu-xxx-dev -> develop, ...)
* [x] Testable acceptance criteria
* [ ] Tests added/updated (unit / integration)
* [x] DDD / hexagonal boundaries respected (domain has no I/O)
* [x] No secrets; config via environment variables

## 6. Evidence links

* Futbolix repository: https://github.com/HERMAR55611
* UX/UI documentation: `12-ux-ui/`
* Domain documentation: `02-domain/`
* Architecture documentation: `05-architecture/`
