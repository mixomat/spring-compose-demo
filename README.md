# spring-compose-demo
Spring Boot Demo Application for docker-compose support. 


## Features
Uses [Reactive Redis](https://docs.spring.io/spring-data/redis/docs/current/reference/html/#redis:reactive) and the [WebFlux.fn](https://docs.spring.io/spring-framework/reference/languages/kotlin/coroutines.html#webflux-fn) support in Spring-Boot 3, backed by a Redis container.

The Redis container is bootstrapped automatically upon the application start on the local developer machine.

For integration testing, the [Spring-Boot Testcontainers](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.testing.testcontainers) support is used to start a Redis container for the test scope.


## Getting Started

### Requirements
- JDK 17
- Docker

### Usage
```shell
./gradlew bootRun
```


### Test Endpoints using httpie
```shell
# Create a coffee
http :8080/coffees id=42 flavor=Americano

# Get a coffee
http :8080/coffees/42
```
