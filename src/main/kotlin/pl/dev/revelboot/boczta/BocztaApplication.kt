package pl.dev.revelboot.boczta

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BocztaApplication

fun main(args: Array<String>) {
    runApplication<BocztaApplication>(*args)
}
