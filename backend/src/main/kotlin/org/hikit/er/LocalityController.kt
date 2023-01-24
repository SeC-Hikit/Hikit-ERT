package org.hikit.er

import io.swagger.v3.oas.annotations.Operation
import org.hikit.common.ControllerConstants.*
import org.hikit.er.data.Coordinates
import org.hikit.er.service.LocalityService
import org.openapitools.model.LocalityResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(LocalityController.PREFIX)
class LocalityController @Autowired constructor(private val localityService: LocalityService) {
    companion object {
        const val PREFIX = "/localities"
    }

    @Operation(summary = "Retrieve Localities")
    @GetMapping
    operator fun get(
        @RequestParam(required = false, defaultValue = MIN_DOCS_ON_READ) skip: Int,
        @RequestParam(required = false, defaultValue = MAX_DOCS_ON_READ) limit: Int,
        @RequestParam(required = false, defaultValue = NO_FILTERING_TOKEN) query: String
    ): LocalityResponse? {
        localityService.get(0, 0, Coordinates(0.0, 0.0, 0.0), 0.0)
        return null
    }


}