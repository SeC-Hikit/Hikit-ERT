package org.hikit.er.configuration

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "Hikit ERT Microservice",
        version = "1.0",
        description = "Hikit ERT is a microservice to fetch data in relation to Emilia-Romagna tourism data"
    )
)
@EnableScheduling
open class OpenAPI