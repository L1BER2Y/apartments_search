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
- Working with **Git**
    - **.gitignore** - is a file in the root directory of a Git repository that specifies which files and directories should be ignored by version control. This is useful for excluding temporary files, build artifacts, development environment settings, and other files from the repository that should not be tracked by version control.
    - **Branch** - Branches allow you to isolate work on new features, bug fixes, or experiments without affecting the main code base. The main branch is usually called main or master, but additional branches can be created for other tasks.
    - **Pull Request** - is a request to push changes from one branch to another within a version control system, usually in a repository host such as GitHub, GitLab or Bitbucket. Pull Request allows project members to discuss changes, conduct code reviews, and test code before merging it into the main branch.
    - **Merge Conflict** - Occurs when Git is unable to automatically merge changes from different branches due to conflicting changes on the same lines in the same file. In such cases, you must manually resolve the conflict by choosing which changes to keep.
- Working with **Docker**
    - **Dockerfile** - As a file containing a set of instructions for creating a Docker image. It determines what steps need to be followed to install the desired software and its configuration. Basic instructions:
        - **FROM** - Specifies the base image on which the new image will be built. This is a mandatory instruction that starts any Dockerfile.
        - **RUN** - Executes commands during the image build process. Typically used to install packages and perform other configuration operations.
        - **CMD** - Specifies the command that will be executed when the container starts. Unlike RUN, it is not executed during image build, but only when the container is started.
        - **COPY** - Copies files and directories from the computer where the image is assembled into the container file system.
        - **ADD** - Similar to COPY but has additional features such as unzipping local files and loading files from a URL.
        - **WORKDIR** - Specifies the working directory for subsequent RUN, CMD, ENTRYPOINT, COPY and ADD instructions.
        - **ENV** - Sets environment variables that will be available in the container.
    - **Image** is an immutable template that contains everything you need to run the application: the assembled application and settings. The image is created based on the instructions from the Dockerfile. Images can be stored in an image registry (e.g. Docker Hub) and reused to build containers.
        - To create an image from a Dockerfile, while in the folder with the file, run the command in the console:
        `docker build -t %specify the desired name of the image%`
    - **Container** is an image instance running in an isolated environment. Containers use host resources (CPU, memory, file system) and provide isolation of applications from each other. Containers make it easy to deploy, scale, and move applications between different environments.
        - To create a container from a previously created image, run the command in the console
        `docker run %name of the image created at the previous stage%`
        - The following options are often specified:
            - `--name` - specify the name of the image. For example: `app_name:version`
            - `-p`- indicate the access port to the container. For example: `80:8080`
            - `-e` - indicate values for environment variables. For example: `VARIABLE_NAME=VARIABLE VALUE`
    - **Compose** is a tool for defining and running multi-container Docker applications. Using the docker-compose.yml file, you can describe the configuration of all the services that will work together (for example, web server, database, cache). Docker Compose makes it easy to manage and run complex applications.
- Working with **OpenAPI**
    - **paths** - Describes the paths (endpoints) and operations (HTTP methods) for interacting with the API. This is a key part of the specification that defines how clients can interact with the server.
    - **components** - Contains reusable parts of the specification, such as data schemas, parameters, responses, etc.
    - **security** - Describes the security schemes used to protect the API. May include mechanisms such as OAuth2, API keys and basic authentication.
    - **tags** - A list of tags to simplify navigation and grouping of operations in the API documentation.

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
Unit-testing, NoSQL database, Message service(RabbitMQ)
