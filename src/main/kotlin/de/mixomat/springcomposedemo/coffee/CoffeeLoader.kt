package de.mixomat.springcomposedemo.coffee

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.ReactiveStringRedisTemplate
import org.springframework.data.redis.core.getAndAwait
import org.springframework.data.redis.core.setAndAwait
import org.springframework.stereotype.Component

@Component
class CoffeeLoader(private val template: ReactiveStringRedisTemplate) {

    suspend fun loadCoffee(id: String): Coffee? =
        template.opsForValue().getAndAwait(id)?.let { Coffee(id, it) }


    suspend fun save(coffee: Coffee): Coffee {
        template.opsForValue().setAndAwait(coffee.id, coffee.flavor)
        return coffee
    }
}

data class Coffee(@Id val id: String, val flavor: String)
