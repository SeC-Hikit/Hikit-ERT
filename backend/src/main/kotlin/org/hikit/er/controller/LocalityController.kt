package org.hikit.er.controller

import io.swagger.v3.oas.annotations.Operation
import org.hikit.common.ControllerConstants.*
import org.hikit.er.controller.response.LocalityResponseHelper
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
class LocalityController @Autowired constructor(
    private val localityService: LocalityService,
    private val localityResponseHelper: LocalityResponseHelper
) {
    companion object {
        const val PREFIX = "/localities"
    }

    @Operation(summary = "Retrieve localities by distance from a point")
    @GetMapping
    operator fun get(
        @RequestParam(required = false, defaultValue = MIN_DOCS_ON_READ) skip: Int,
        @RequestParam(required = false, defaultValue = MAX_DOCS_ON_READ) limit: Int,
        @RequestParam(required = true) latitude: Double,
        @RequestParam(required = true) longitude: Double,
        @RequestParam(required = true) distance: Double
    ): LocalityResponse {
        val internalResponse = localityService.get(skip, limit, Coordinates(latitude, longitude), distance)
        localityResponseHelper
            .constructResponse(
                emptySet(),
                internalResponse.data,
                internalResponse.totalCount,
                skip, limit
            )
    }


}