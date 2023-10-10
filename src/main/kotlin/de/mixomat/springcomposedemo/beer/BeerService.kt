package de.mixomat.springcomposedemo.beer

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class BeerService {
    @Cacheable("beer")
    fun getBeer(): String = "🍺"
}
