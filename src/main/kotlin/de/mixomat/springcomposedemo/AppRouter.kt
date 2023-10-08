package de.mixomat.springcomposedemo

import de.mixomat.springcomposedemo.coffee.CoffeeHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class AppRouter {

    @Bean
    fun router(coffeeHandler: CoffeeHandler) = coRouter {
        accept(APPLICATION_JSON).nest {
            POST("/coffees", coffeeHandler::createCoffee)
            GET("/coffees/{id}", coffeeHandler::getCoffee)
        }
    }
}

