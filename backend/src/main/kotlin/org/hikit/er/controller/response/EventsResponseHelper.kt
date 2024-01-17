package org.hikit.er.controller.response

import org.hikit.common.response.ControllerPagination
import org.hikit.er.rest.EventDto
import org.hikit.er.rest.response.EventsResponse
import org.hikit.er.rest.response.Status
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventsResponseHelper @Autowired constructor(private val controllerPagination: ControllerPagination) {
    fun constructResponse(
        errors: Set<String>,
        dtos: List<EventDto>,
        totalCount: Int,
        skip: Int,
        limit: Int
    ): EventsResponse {
        val totalCountLong = totalCount.toLong()
        return if (errors.isNotEmpty()) {
            EventsResponse(
                Status.ERROR, errors, dtos, 1L,
                1, limit.toLong(), totalCountLong
            )
        } else EventsResponse(
            Status.OK, errors, dtos,
            controllerPagination.getCurrentPage(skip, limit),
            controllerPagination.getTotalPages(totalCountLong, limit),
            limit.toLong(),
            totalCountLong
        )
    }
}