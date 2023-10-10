package de.mixomat.springcomposedemo.coffee

import kotlinx.coroutines.runBlocking
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class CoffeeHandler(private val coffeeLoader: CoffeeLoader) {
    suspend fun getCoffee(serverRequest: ServerRequest): ServerResponse =
        coffeeLoader.loadCoffee(serverRequest.pathVariable("id")).toResponse()

    suspend fun createCoffee(serverRequest: ServerRequest): ServerResponse =
        ServerResponse.ok().bodyValueAndAwait(coffeeLoader.save(serverRequest.awaitBody<Coffee>()))
}

suspend fun Coffee?.toResponse() = this
    ?.let { ServerResponse.ok().bodyValueAndAwait(it) }
    ?: ServerResponse.notFound().buildAndAwait()
