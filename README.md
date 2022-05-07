[![Java CI with Maven](https://github.com/Brest-Java-Course-2021-2/Vladimir-Petranovski-MD-SpringBoot/actions/workflows/maven.yml/badge.svg)](https://github.com/Brest-Java-Course-2021-2/Vladimir-Petranovski-multimodule/actions/workflows/maven.yml)

# Brest Java Course 2021 2

# Vladimir-Petranovski-MD-SpringBoot

<sub>Developed by <a href="https://github.com/Brest-Java-Course-2021-2/Vladimir-Petranovski-MD-SpringBoot">Uladzimir Petranouski</a>


This is sample 'Motor depot' web application.

## Project Information

- [Software requirements specification](documents/src/motor_depot.md)

## Technology Stack

- **Programming Language:** [Java](https://www.java.com)
  <a href="https://www.java.com" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="20" height="20"/> </a>
- **Core Framework:** [Spring boot](https://spring.io/projects/spring-boot)
  <a href="https://spring.io/projects/spring-boot" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="18" height="18"/> </a>
- - **Data Access:**
- [Spring JDBC](https://docs.spring.io/spring-framework/docs/5.3.x/reference/html/data-access.html#jdbc)
<a href="https://spring.io/projects/spring-boot" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="18" height="18"/> </a>
- **Build System:** [Maven](https://maven.apache.org/) <img height="20" width="20" src="https://raw.githubusercontent.com/vscode-icons/vscode-icons/master/icons/file_type_maven.svg"/>
- **Control System:** [Git](https://git-scm.com/) <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="18" height="18"/> </a>
- **License:** [Apache license, version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
- **Automated Testing:**
  - [JUnit5](https://junit.org/junit5/) <img height="20" width="20" src="https://unpkg.com/simple-icons@v6/icons/junit5.svg"/>
  - [Mockito](http://site.mockito.org/) <img height="20" width="40" src="documentation/img/mokito.svg"/>
- **Log:** [Log4j 2](https://logging.apache.org/log4j/2.x/)
- **Database:**
  - [H2](http://www.h2database.com/html/main.html) <img height="20" width="20" src="documentation/img/h2.svg"/>
  - [PostgreSQL](https://www.postgresql.org/) <a href="https://www.postgresql.org" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/postgresql/postgresql-original-wordmark.svg" alt="postgresql" width="20" height="20"/> </a>
- **API documentation generation:**
  - [Swagger UI](https://swagger.io/tools/swagger-ui/) <img height="20" width="20" src="https://raw.githubusercontent.com/AliasIO/wappalyzer/master/src/drivers/webextension/images/icons/Swagger%20UI.svg"/>
- **Code generation:**
  - [Swagger OpenApi](https://swagger.io/specification/) <img height="20" width="20" src="https://raw.githubusercontent.com/AliasIO/wappalyzer/master/src/drivers/webextension/images/icons/Swagger%20UI.svg"/>
- **Template Engine:** [Thymeleaf](https://www.thymeleaf.org/) <img height="20" width="20" src="https://unpkg.com/simple-icons@v6/icons/thymeleaf.svg"/>
- **CSS Framework:** [Bootstrap](https://getbootstrap.com/) <a href="https://getbootstrap.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/bootstrap/bootstrap-plain-wordmark.svg" alt="bootstrap" width="20" height="20"/> </a>

## Requirements

* JDK 11+
* Git 2.25.1+
* Apache Maven 3.6.3+

## Build application:
```bash
$ git clone https://github.com/Brest-Java-Course-2021-2/Vladimir-Petranovski-MD-SpringBoot
$ cd Vladimir-Petranovski-MD-SpringBoot
Run for production:
$ mvn clean install -Dspring.profiles.active=prod
Run for testing:
$ mvn clean install -Dspring.profiles.active=test
Run for developing:
$ mvn clean install -Dspring.profiles.active=dev
```

## Run tests:
```bash
$ mvn clean test
```

## Run integration tests:
```bash
$ mvn clean verify
```

## Rest server

### Start Rest

## Rest app configure

Setup [rest-app](/rest-app) in [application.properties](/rest-app/src/main/resources/application.properties):

| Profile | Description                                         |
|---------|-----------------------------------------------------|
| *dev*   | Run application with PostgresSQL database           |                                   |
| *test*  | Run application with embedded H2 database in memory |
| *prod*  | Run application with MySQL database                 |                              |

Example:
```
spring.profiles.active=dev
```

In the root directory of the project:
```bash
$ java -jar -Dspring.profiles.active=dev rest-app/target/rest-app-1.0-SNAPSHOT.jar
```

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/18999675-2eae966d-2b48-4890-91ff-a0d7f181caa6?action=collection%2Ffork&collection-url=entityId%3D18999675-2eae966d-2b48-4890-91ff-a0d7f181caa6%26entityType%3Dcollection%26workspaceId%3D56c614c7-7bb5-44fe-b171-746dba387b30)

[![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=black)](http://localhost:8088/swagger-ui/)

The rest application will be accessible at [http://localhost:8088](http://localhost:8088).
The swagger ui will be accessible at [http://localhost:8088](http://localhost:8088/swagger-ui/).

## Web

### Start Web
#### Run local tests (H2 in memory)

In the root directory of the project:
```bash
$ java -jar -Dspring.profiles.active=dev web-app/target/web-app-1.0-SNAPSHOT.jar
```
The web application will be accessible at [http://localhost:8080](http://localhost:8080).

## Available REST endpoints

## version

```bash
$ curl --request GET 'http://localhost:8088/version'
```

## drivers_dto

```bash
$ curl --request GET 'http://localhost:8088/drivers_dto'
```

#### Pretty print json:

```bash
$ curl --request GET 'http://localhost:8088/drivers_dto' | json_pp
```

## drivers

#### findAll

```bash
$ curl --request GET 'http://localhost:8088/drivers' | json_pp
```

#### findById

```bash
$ curl --request GET 'http://localhost:8088/drivers/1' | json_pp
```

### create

```bash
$ curl --request POST 'http://localhost:8088/drivers' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
"driverName": "TOLIA",
"driverDateStartWork": 1214677490.532700000,
"driverSalary": 980
}'
```

### update

```bash
$ curl --request PATCH 'http://localhost:8088/drivers/3' \
--header 'Content-Type: application/json' \
--data-raw '{
"driverName": "SERGEI",
"driverDateStartWork": 1014677990.532700000,
"driverSalary": 470
}'
```

### delete

```bash
$ curl --request DELETE 'http://localhost:8088/drivers/3'
```

## cars

#### findAll

```bash
$ curl --request GET 'http://localhost:8088/cars' | json_pp
```

#### findById

```bash
$ curl --request GET 'http://localhost:8088/cars/1' | json_pp
```

### create

```bash
$ curl --request POST 'http://localhost:8088/cars' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
"carModel": "GIGULI",
"driverId": 2
}'
```

### update

```bash
$ curl --request PATCH 'http://localhost:8088/cars/3' \
--header 'Content-Type: application/json' \
--data-raw '{
"carModel": "AUDI",
"driverId": 1
}'
```

## delete

```bash
$ curl --request DELETE 'http://localhost:8088/cars/3'
```