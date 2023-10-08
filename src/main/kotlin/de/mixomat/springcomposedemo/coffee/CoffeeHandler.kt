package de.mixomat.springcomposedemo.coffee

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class CoffeeHandler(private val coffeeLoader: CoffeeLoader) {
    suspend fun getCoffee(serverRequest: ServerRequest): ServerResponse {
        coffeeLoader.loadCoffee(serverRequest.pathVariable("id"))
            ?.let { return ServerResponse.ok().bodyValueAndAwait(it) }
            ?: return ServerResponse.notFound().buildAndAwait()
    }

    suspend fun createCoffee(serverRequest: ServerRequest): ServerResponse =
        ServerResponse.ok().bodyValueAndAwait(coffeeLoader.save(serverRequest.awaitBody<Coffee>()))
}
