# General explanation

Firstly this project was developed to secure the principles of design and development of modern web-applications.

Was studied on the course:

- Http-protocol:
    - Differences between **GET**, **POST**, **PUT**, **DELETE** requests.
    - Data transfer methods: **Query string**, **Headers**, **Body**
    - Princples of forming URLs according to **RESTful API**
- Working with databases using **PostgreSQL** as an example:
    - Differences between **DDL** and **DML**
    - **Transactions**
    - Differences in development approaches when using **JDBC** and **ORM**
- Working using MVC, which means dividing into layers:
    - **Controller** - access layer in which work was implemented using the **HTTP** protocol through the use of the **Spring Web MVC** library within which **DispatcherServlet** works
    - **Service** - service layer in which business logic and conversion from **DTO** to **Entity** were implemented
    - **Dao** - data access layer in which work with **PostgreSQL** was implemented through the use of the **Spring Data JPA** library
- Working with **Maven**
     - **pom.xml** - as a file with meta information about the project
     - Using the **property** tag to create variables that can be used in pom.xml
     - Automated launch of **JUnit** tests via **maven-surefire-plugin**
     - Automatic deployment of **WAR** file using the tomcat7-maven-plugin plugin and the mvn clean package **tomcat7:deploy command**
     - Using **parent pom** to build several Maven projects in a row
- Git
- Docker
- OpenAPI
- Spring framework
- Async

# Used technologies and frameworks:
- [Java 17](https://docs.oracle.com/en/java/javase/17/docs/api/)
- [Maven](https://maven.apache.org/)
- [Spring Boot 3.2.0](https://spring.io/projects/spring-boot)
- [Spring Data JPA 3.2.0](https://spring.io/projects/spring-data-jpa)
- [Spring Security 3.2.0](https://spring.io/projects/spring-security)
- [Spring Cloud 3.2.0](https://spring.io/projects/spring-cloud)
- [PostgreSQL](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)

# Project description: Housing search aggregator

Description

# Future plans:
