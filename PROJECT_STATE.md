# Project State

## Current phase and step
- Phase 1: Project skeleton (user's numbered build order).
- Current step: Step 14, add Actuator health and Swagger. Next action: add and verify the Actuator health endpoint.
- Phase 0 complete: MySQL, RabbitMQ, Qdrant, Ollama, and Redis passed the local checks. User's Redis check returned `PONG`.
- Ollama check: User ran a prompt with `llama3.2:3b` in the Ollama container and received `ready`.
- Qdrant check: User confirmed the dashboard opens on Windows port 6333.
- Step 7 progress: User verified MySQL credentials and queried the `docassist` database from inside the MySQL container. The query returned `docassist` and `connection_ok = 1`. User also confirmed signing in to the RabbitMQ management dashboard on Windows port 15672 using the configured account.
- Step 6 complete: User's `docker compose ps -a` output confirmed all five containers are Up. MySQL publishes Windows port 3307 to container port 3306. Redis publishes Windows port 6380 to container port 6379. User's Ollama output confirmed successful downloads of `llama3.2:3b` and `nomic-embed-text`.
- Status: Step 5 complete. The API module's `/.env` ignore rule, local credential file, and `.env.example` were verified. Compose validation with `.env` returned exit 0. User's IntelliJ terminal confirmed Docker Desktop's Linux engine is reachable (Docker Engine 29.8.1, `desktop-linux` context).

## Completed steps
- Numbered Step 13: Verified API startup with the `local` and `docker` profiles and a passing API context test with the `test` profile.
- Numbered Step 12: Verified the Commons response wrapper, error codes, enums, base exception, and correlation ID header constant.
- Numbered Step 2: Verified that the Commons, Entity, and Model `.gitignore` rules match the API's and that Git ignores new `target/` output in all three modules. Existing tracked build artifacts and the ignore-file commit remain pending.
- Read CODEX_INSTRUCTIONS.md and initialized this project tracker.
- Phase 0, Step 1 passed: user-provided terminal output confirms java and javac version 21.0.10.
- Phase 0, Step 2 passed: user-provided output confirms Maven 3.9.16 uses Java 21.0.10.
- Phase 0, Step 3 passed: Docker engine responds with version 29.8.1; Docker Compose version v5.5.1 confirmed by user output.
- User reports completion of Initializr configuration for docassist-api with Spring Web; generated POM review is pending.
- Generated project extracted and reviewed: group com.docassist, artifact docassist-api, Spring Boot parent 4.1.2, Java 21, Web MVC and Web MVC test starters. No build run yet.
- API context test passed, confirmed by local Surefire report. IntelliJ now uses installed Maven to avoid the wrapper path quoting error.
- Main and test packages corrected to com.docassist.api and verified; corresponding Surefire report confirms successful test.
- docassist-commons created beside API; initial POM reviewed and requires parent/version correction.
- Commons parent, artifact ID, and project version corrected and reviewed; Maven validate confirmation pending.
- User confirmed successful commons Maven validation in IntelliJ.
- docassist-model created in correct sibling location with com.docassist:docassist-model:0.0.1-SNAPSHOT and Java 21 settings; shared Spring Boot parent not yet added.
- Model parent added and verified; user confirmed Maven validation completed.
- Entity module created and POM reviewed with correct coordinates, parent, and Java settings; Maven validation confirmation pending.
- User confirmed all existing module builds succeed, including entity validation.
- Processor regenerated as a Spring Boot application; entry point, context test, application properties, and runtime/build dependencies verified. Package correction and test run pending.
- Processor package correction verified and context test passed (1 test, 0 failures, 0 errors).
- User confirmed successful commons Maven install in IntelliJ.
- API -> commons dependency added and reviewed; user confirmed API test completed successfully.
- Processor -> commons dependency added and reviewed; context test passed (1 test, 0 failures/errors).
- User confirmed successful model Maven install in IntelliJ.
- API -> model/entity and processor -> entity dependencies reviewed; user confirmed entity install. API/processor context tests each passed. Extra processor -> model dependency found and requires removal.
- Forbidden processor -> model dependency removed and verified; processor context test passed again. Module wiring complete.
- ServiceResponse<T> source and BOP-style package reviewed; compilation confirmed by the class file under target/classes.
- Commons reinstall verified: local Maven JAR contains ServiceResponse.class in the expected package.

## Decisions
- Keep API configuration in `.properties` files. Select the runtime profile in the base `application.properties`, following DAO BOP. The file currently selects `docker` from its startup check. Use `local` for development.
- Use MySQL in place of SQL Server for DocAssist. Keep Compose and local environment files inside `docassist-api`.
- Use DAO BOP module-local `.gitignore` files and keep the API README in `docassist-api`. Follow the user's numbered build order for remaining work.
- Follow CODEX_INSTRUCTIONS.md as the project architecture and mentoring guide.
- Teach one small step at a time; wait for verification before advancing.
- The user writes application code. Provide code only when requested.
- Use Java 21 and the specified Maven multi-module architecture and build order.
- Keep dependencies acyclic and all API responses wrapped in ServiceResponse<T>.
- Externalize configuration and secrets.
- Keep mentoring responses very concise, as requested by the user.
- Use IntelliJ UI for build/test guidance; user does not want command-line execution.
- Match D:/DAO_BOP/merchant_digital_onboarding layout: sibling Maven projects with individual POMs, no required root aggregator. Reference dao-apis is an application JAR, not a parent POM; all five projects inherit Spring Boot directly.
- User's requested reference layout supersedes the shared root parent plan. Preserve DocAssist's acyclic dependency rules: do not copy reference commons -> model or model -> entity dependencies.
- User reiterated matching BOP DAO package organization. ServiceResponse belongs in com.docassist.commons.utils, matching reference com.paysyslabs.dao.commons.utils; supersedes previously proposed commons.response package. Preserve generic ServiceResponse<T> contract.
- User requested starting the Spring Boot project through start.spring.io before Compose setup; subsequent decision uses sibling projects without a shared root parent.

## File/class list
- CODEX_INSTRUCTIONS.md: project scope, architecture, and mentoring rules.
- PROJECT_STATE.md: progress, decisions, files, and next steps.
- docassist-api/pom.xml: generated API Maven configuration.
- docassist-commons/pom.xml: reviewed library POM; validation confirmed by user.
- docassist-commons/src/main/java/com/docassist/commons/utils/ServiceResponse.java: reviewed and compiled.
- docassist-commons/src/main/java/com/docassist/commons/enums/ErrorCode.java: reviewed and compiled. Defines `VALIDATION_ERROR` and `INTERNAL_ERROR`.
- docassist-commons/src/main/java/com/docassist/commons/enums/DocumentStatus.java: reviewed and compiled. Defines `UPLOADED`, `PROCESSING`, `READY`, and `FAILED`.
- docassist-commons/src/main/java/com/docassist/commons/enums/Role.java: reviewed and compiled. Defines `USER`.
- docassist-commons/src/main/java/com/docassist/commons/enums/SummaryType.java: reviewed and compiled. Defines `SHORT` and `DETAILED`.
- docassist-commons/src/main/java/com/docassist/commons/exception/DocAssistException.java: reviewed and compiled. Carries an `ErrorCode` and uses `RuntimeException` to store the message.
- docassist-commons/src/main/java/com/docassist/commons/constants/Constants.java: reviewed and compiled. Defines `CORRELATION_ID_HEADER` as `X-Correlation-ID`.
- docassist-model/pom.xml: reviewed library POM; validation confirmed by user.
- docassist-entity/pom.xml: reviewed library POM; validation confirmed by user.
- docassist-processor/pom.xml: generated Boot application POM reviewed.
- docassist-processor/src/main/java/com/docassist/processor/DocassistProcessorApplication.java: verified entry point.
- docassist-processor/src/test/java/com/docassist/processor/DocassistProcessorApplicationTests.java: passing context test.
- docassist-processor/src/main/resources/application.properties: processor application name.
- docassist-api/src/main/java/com/docassist/api/DocassistApiApplication.java: application entry point.
- docassist-api/src/test/java/com/docassist/api/DocassistApiApplicationTests.java: passing context test.
- docassist-api/src/main/resources/application.properties: application name and active profile selection.
- docassist-api/src/main/resources/application-local.properties: sets port `8081`; user verified API startup on that port in IntelliJ.
- docassist-api/src/main/resources/application-docker.properties: sets port `8080`; user verified that `docker` was active and the API started on that port in IntelliJ.
- docassist-api/src/test/resources/application-test.properties: test-only settings. Surefire reported one passing context test with `test` active.

## Next steps
1. Step 14: Add and verify Actuator health and Swagger in the API.
2. Continue with the remaining Phase 1 checks.
