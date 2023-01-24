package org.hikit.er.configuration

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = Info(
        title = "ERT",
        version = "1.0",
        description = "Hikit ERT is an open API that manages high level geo and meta data related to trails. " +
                "It supports accessibility-notifications, maintenance planning, generic/specific POIs " +
                "and other features all linked with trails."
    )
)
open class OpenAPI