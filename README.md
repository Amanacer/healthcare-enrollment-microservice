# HealthCare Enrollment Microservices (BE)

A Spring Boot microservices backend application with a MySQL backend and CRUD REST API implementation for tracking the status of enrollees in a healthcare program. 

Technologies Used: Spring Cloud, JPA, Spring Boot, MySQL, OpenAPI using Swagger tools for API Documenatation.

## Project Structure

### Microservices Structure
- Config (Configuration) Service
    - external application-config repo for sharing properties (bootstrap.properties) across services
- Eureka Discovery Service
- Enrollee Service
- Gateway Service 

## References used and further reading: 
- [Spring Cloud – Bootstrapping](http://www.baeldung.com/spring-cloud-bootstrapping)

- [12-Factor Methodology in a Spring Boot MicroService](https://www.baeldung.com/spring-boot-12-factor)

- [Documenting a Spring REST API Using OpenAPI 3.0](https://www.baeldung.com/spring-rest-openapi-documentation)

### Running and project set up 
  
  - copy the appliction-config folder to c:\Users\{username}\ on Windows or /home/{username}/ on *nix on your machine. 
  - Then open a git bash terminal in application-config and run:
    - git init
    - git add .
    - git commit -m "First commit"

  1. run the config service
  2. run the discovery service
  3. run all the other servers in any order (gateway, enrollee-service, etc.)


  - The OpenAPI documentation can be accessed on localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/  
