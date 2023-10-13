# hibernate-6-custom-functions

Demo project to showcase how to implement custom SQL functions in Hibernate 6 using:

- Java 21
- Maven
- Spring Boot 3
- Spring Data JPA
- Hibernate 6
- Flyway
- PostgreSQL
- Docker & Testcontainers

Articles that were written based in this project, in order:

- [Hibernate 6: custom SQL functions in Spring Data JPA repositories](https://aregall.tech/hibernate-6-custom-functions)

----

## Requirements

The application requires JDK 21.

````shell
$ sdk install java 21-amzn
$ sdk use java 21-amzn
````
----

## Build and test

````
./mvnw clean verify
````
----
## Run the application

````
./mvnw spring-boot:run
````

*Note: by default requires Postgres to run locally on port 5432.*