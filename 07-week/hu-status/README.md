<!-- HU-STATUS TEMPLATE - do NOT remove the <!-- ... --> markers or the table headers.

```
 Your weekly grade is read AUTOMATICALLY from this file:
   07-week/hu-status/README.md  (inside YOUR fork). English. -->
```

# Weekly Status - Week 07

<!-- CONFIG-START - must match your profile repo (username/username) CONFIG -->

* FULL_NAME: Hernando Antonio Martín Herrera
* GITHUB_USER: HERMAR55611
* TEAM: Futbolix
* SPRINT_GOAL: Iniciar la implementación de las historias de usuario priorizadas de Futbolix, aplicando DDD, Arquitectura Hexagonal, TDD y buenas prácticas de desarrollo.

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

* Participaré en la organización y priorización de las historias de usuario que serán desarrolladas durante la semana.
* Apoyaré la definición de los casos de uso correspondientes a las funcionalidades priorizadas.
* Revisaré que las historias de usuario mantengan relación con los cuatro dominios definidos para Futbolix: Canchas, Reservas, Clientes y Horarios.
* Apoyaré la preparación de la estructura inicial de los microservicios bajo Arquitectura Hexagonal.
* Participaré en la definición de las primeras pruebas bajo el enfoque TDD.
* Mantendré la documentación técnica alineada con SDD, SOLID y Clean Code.

## 3. Blockers and risks

* Se requiere validar la prioridad definitiva de las historias de usuario antes de iniciar su implementación.
* Algunas funcionalidades presentan dependencias entre los dominios de Futbolix.
* Existe riesgo de duplicar responsabilidades entre microservicios si no se respetan los límites de cada dominio.
* La definición de contratos debe mantenerse alineada con los casos de uso y la arquitectura.
* Los cambios en requisitos o historias de usuario pueden generar ajustes en la implementación.

## 4. Plan for next week

* Continuar con la implementación de las historias de usuario priorizadas.
* Completar los primeros casos de uso de los microservicios.
* Implementar pruebas unitarias e integración de las funcionalidades desarrolladas.
* Validar la separación entre dominio, aplicación, puertos y adaptadores.
* Revisar los contratos de comunicación entre los servicios que presenten dependencias.
* Registrar los avances mediante commits y Pull Requests siguiendo las convenciones establecidas.

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
