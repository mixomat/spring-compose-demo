package de.mixomat.springcomposedemo.beer

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class BeerHandler(private val beerService: BeerService) {

    suspend fun getBeer(request: ServerRequest): ServerResponse =
        ServerResponse.ok().bodyValueAndAwait(beerService.getBeer())
}
