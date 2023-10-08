package de.mixomat.springcomposedemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringComposeDemoApplication

fun main(args: Array<String>) {
	runApplication<SpringComposeDemoApplication>(*args)
}
