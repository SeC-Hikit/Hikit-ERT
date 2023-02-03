package org.hikit.er.controller.response

import org.hikit.common.response.ControllerPagination
import org.hikit.er.rest.LocalityDto
import org.hikit.er.rest.response.LocalityResponse
import org.hikit.er.rest.response.Status
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LocalityResponseHelper @Autowired constructor(private val controllerPagination: ControllerPagination){

    fun constructResponse(
        errors: Set<String>,
        dtos: List<LocalityDto>,
        totalCount: Long,
        skip: Int,
        limit: Int
    ): LocalityResponse {
        return if (errors.isNotEmpty()) {
            LocalityResponse(
                Status.ERROR, errors, dtos, 1L,
                1, limit.toLong(), totalCount
            )
        } else LocalityResponse(
            Status.OK, errors, dtos,
            controllerPagination.getCurrentPage(skip, limit),
            controllerPagination.getTotalPages(totalCount, limit),
            limit.toLong(),
            totalCount
        )
    }
}