package de.mixomat.springcomposedemo.coffee

import com.redis.testcontainers.RedisContainer
import de.mixomat.springcomposedemo.coffee.Coffee
import de.mixomat.springcomposedemo.coffee.CoffeeLoader
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.data.redis.core.deleteAndAwait
import org.springframework.data.redis.core.hasKeyAndAwait
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@SpringBootTest
@Testcontainers
class CoffeeLoaderTest(
    @Autowired private val redisTemplate: ReactiveStringRedisTemplate,
    @Autowired private val coffeeLoader: CoffeeLoader
) {

    val key = "1"

    @BeforeEach
    fun setUp() {
        runBlocking {
            redisTemplate.deleteAndAwait(key)
        }
    }

    @Test
    fun `should save coffee entities`() {
        runBlocking {
            coffeeLoader.save(Coffee(key, "Espresso"))
            redisTemplate.hasKeyAndAwait(key).shouldBeTrue()
        }
    }

    @Test
    fun `should load coffees by id`() {
        runBlocking {
            coffeeLoader.loadCoffee(key).shouldBeNull()
            coffeeLoader.save(Coffee(key, "Espresso"))
            coffeeLoader.loadCoffee(key).shouldNotBeNull()
        }
    }

    companion object {

        @Container
        @ServiceConnection
        val redis = RedisContainer(DockerImageName.parse("redis:alpine"))

    }
}
