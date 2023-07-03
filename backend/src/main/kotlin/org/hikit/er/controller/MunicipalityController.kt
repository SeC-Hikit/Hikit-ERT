package org.hikit.er.controller

import io.swagger.v3.oas.annotations.Operation
import org.hikit.common.ControllerConstants.*
import org.hikit.er.controller.request.LineRequest
import org.hikit.er.rest.response.MunicipalityResponse
import org.hikit.er.rest.response.Status
import org.hikit.er.service.MunicipalityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(MunicipalityController.PREFIX)
class MunicipalityController @Autowired constructor(
    private val municipalityService: MunicipalityService,
) {
    companion object {
        const val PREFIX = "/municipality"
    }

    @Operation(summary = "Retrieve a municipality by ID")
    @GetMapping("/{id}")
    operator fun get(@PathVariable id: String): MunicipalityResponse {
        val internalResponse = municipalityService.getById(id)
        return MunicipalityResponse(
            Status.OK,
            emptySet(),
            internalResponse.data,
        )
    }


    @PostMapping
    @Operation(summary = "Find within which municipalities a line is contained")
    fun findMunicipalitiesForLine(@RequestBody request: LineRequest): MunicipalityResponse {
        val internalResponse = municipalityService.getMunicipalitiesByLineString(request)
        return MunicipalityResponse(
            Status.OK,
            emptySet(),
            internalResponse.data,
        )
    }


}