package org.hikit.er.controller

import io.swagger.v3.oas.annotations.Operation
import org.hikit.common.ControllerConstants.*
import org.hikit.er.controller.response.EventsResponseHelper
import org.hikit.er.rest.response.EventsResponse
import org.hikit.er.service.EventService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(EventController.PREFIX)
class EventController @Autowired constructor(
    private val eventService: EventService,
    private val eventResponseHelper: EventsResponseHelper
) {
    companion object {
        const val PREFIX = "/event"
    }

    @Operation(summary = "Retrieve events by ISTAT code")
    @GetMapping("/{istatCode}")
    fun getByIstat(
        @PathVariable istatCode: String,
        @RequestParam(required = false, defaultValue = MIN_DOCS_ON_READ) skip: Int,
        @RequestParam(required = false, defaultValue = MAX_DOCS_ON_READ) limit: Int
    ):
            EventsResponse {
        val internalResponse = eventService.getByIstat(istatCode, skip, limit)
        return eventResponseHelper.constructResponse(
            emptySet(),
            internalResponse.data,
            internalResponse.totalCount,
            skip, limit
        )
    }


//    @Operation(summary = "Retrieve localities by distance from a point")
//    @GetMapping
//    operator fun get(
//        @RequestParam(required = false, defaultValue = MIN_DOCS_ON_READ) skip: Int,
//        @RequestParam(required = false, defaultValue = MAX_DOCS_ON_READ) limit: Int,
//        @RequestParam(required = true) latitude: Double,
//        @RequestParam(required = true) longitude: Double,
//        @RequestParam(required = true) distance: Double
//    ): LocalityResponse {
//        val internalResponse = localityService.get(skip, limit, Coordinates(latitude, longitude), distance)
//        return localityResponseHelper
//            .constructResponse(
//                emptySet(),
//                internalResponse.data,
//                internalResponse.totalCount,
//                skip, limit
//            )
//    }


}