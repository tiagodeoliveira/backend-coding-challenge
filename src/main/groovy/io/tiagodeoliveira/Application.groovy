package io.tiagodeoliveira

import groovy.util.logging.Log
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.web.SpringBootServletInitializer

/**
 * Created by tiago on 29/06/15.
 */

@Log
@SpringBootApplication
class Application extends SpringBootServletInitializer {

    @Override
    def SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class)
    }

    static main(args) {
        SpringApplication.run Application, args
    }
}
