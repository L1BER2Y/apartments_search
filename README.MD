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
- Работа с применением MVC что подразумевает разделение на слои:
    - **Controller** - слой доступа в котором была реализована работа по протоколу **HTTP** через использование библиотеки **Spring Web MVC** внутри которого работает **DispatcherServlet**
    - **Service** - слой сервиса в котором была реализована бизнес логика и конвертации из **DTO** в **Entity**
    - **Dao** - слой доступа к данным в котором была реализована работа с **PostgreSQL** через использование библиотеки **Spring Data JPA**
- Работа с **Maven**
    - **pom.xml** - как файл с метаинформацией о проекте
    - Использование тэга **property** для создания переменных которые можно использовать в pom.xml
    - Автоматизированный запуск **JUnit** тестов через **maven-surefire-plugin**
    - Автоматический деплой **WAR** файла с использованием плагина tomcat7-maven-plugin и команды mvn clean package tomcat7:deploy
    - Использование **parent pom** для сборки нескольких Maven проектов подряд
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
