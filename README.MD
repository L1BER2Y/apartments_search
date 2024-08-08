# General explanation

Web application based on microservice architecture style. It grants information about relevant rent and sale options.

# Architecture diagram:
<img src="https://raw.githubusercontent.com/L1BER2Y/apartments_search/main/Application diagram.png" alt="architecture-diagram" width="1000"/>

# Application microservices:

**[User-service](https://github.com/L1BER2Y/apartments_search/tree/main/user-service)**
- This microservice is responsible for admin (creating/editing users, user information, and user (registering, verification, logging in, user information) functions.
- Also it has user action tracking functionality, that was implemented with the help of Aspect-oriented programming.

**[Flats-service](https://github.com/L1BER2Y/apartments_search/tree/main/flats-service)**
- Flats-service is used to display information about flats.
- You can also specify you request with filters (price, bedrooms, area, floors, photos).

**[Parser-service](https://github.com/L1BER2Y/apartments_search/tree/main/parser-service)**
- This microservice parses info about flats from https://re.kufar.by/ website in real time. 
- Parsed information is stored into application database.

**[Audit-service](https://github.com/L1BER2Y/apartments_search/tree/main/audit-service)**
- Audit-service accepts audited actions from user-service and stores them into database. 
- It has admin feature to check audited actions for chosen user by id.

**[Report-service](https://github.com/L1BER2Y/apartments_search/tree/main/report-service)**
- Service create reports based on user's audited actions.
- It accepts report requests, can display report status, give page of reports, provides created report download link.

**[Mail-service](https://github.com/L1BER2Y/apartments_search/tree/main/mail-service)**
- Responsible for sending verification codes to email addresses during user registration.

# Used technologies and frameworks:
- [Java 17](https://docs.oracle.com/en/java/javase/17/docs/api/)
- [Nginx](https://nginx.org/en/)
- [JUnit5](https://junit.org/junit5/)
- [Docker](https://www.docker.com/)
- [Maven 3.9.6](https://maven.apache.org/)
- [H2 Database](https://www.h2database.com/html/main.html)
- [PostgreSQL 16](https://www.postgresql.org/)
- [Spring Boot 3.2.0](https://spring.io/projects/spring-boot)
- [Spring Security 3.2.0](https://spring.io/projects/spring-security)
- [Spring Data JPA 3.2.0](https://spring.io/projects/spring-data-jpa)
- [Spring Cloud OpenFeign 4.1.0](https://spring.io/projects/spring-cloud-openfeign)

# Future plans:
- ~~Unit-testing~~
- Expand audit functionality 
- NoSQL database
- Message broker (RabbitMQ)
