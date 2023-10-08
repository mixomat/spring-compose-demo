package de.mixomat.springcomposedemo.coffee

import com.ninjasquad.springmockk.MockkBean
import de.mixomat.springcomposedemo.AppRouter
import io.mockk.coEvery
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(CoffeeHandler::class)
@Import(AppRouter::class)
class CoffeeHandlerTest(@Autowired private val webClient: WebTestClient) {

    @MockkBean
    private lateinit var coffeeLoader: CoffeeLoader

    private val latte = Coffee("someId", "Latte")

    @Test
    fun `should handle create coffee requests`() {
        coEvery { coffeeLoader.save(any()) } returns latte

        webClient.post().uri("/coffees")
            .bodyValue(latte)
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun `should handle get coffee requests`() {
        coEvery { coffeeLoader.loadCoffee(latte.id) } returns latte

        webClient.get().uri("/coffees/someId")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.id").isEqualTo(latte.id)
            .jsonPath("$.flavor").isEqualTo(latte.flavor)
    }
}
