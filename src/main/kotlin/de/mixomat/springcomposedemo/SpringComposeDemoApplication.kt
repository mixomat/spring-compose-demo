package de.mixomat.springcomposedemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class SpringComposeDemoApplication

fun main(args: Array<String>) {
	runApplication<SpringComposeDemoApplication>(*args)
}
