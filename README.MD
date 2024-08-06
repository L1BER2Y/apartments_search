# General explanation

Web application based on microservice architecture style. It grants information about relevant rent and sale options.

## Application microservices:

**[User-service](https://github.com/L1BER2Y/apartments_search/tree/main/user-service)**
- This microservice is responsible for admin (creating/editing users, user information, and user (registering, verification, logging, user information) functions.

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
- [Maven 3.9.6](https://maven.apache.org/)
- [Spring Boot 3.2.0](https://spring.io/projects/spring-boot)
- [Spring Data JPA 3.2.0](https://spring.io/projects/spring-data-jpa)
- [Spring Security 3.2.0](https://spring.io/projects/spring-security)
- [Spring Cloud OpenFeign 4.1.0](https://spring.io/projects/spring-cloud-openfeign)
- [JUnit5](https://junit.org/junit5/)
- [H2 Database](https://www.h2database.com/html/main.html)
- [Nginx](https://nginx.org/en/)
- [PostgreSQL](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)

# Future plans:
Unit-testing, NoSQL database, Message broker(RabbitMQ)
