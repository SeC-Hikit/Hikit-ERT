package org.hikit.er.service

import org.hikit.er.controller.response.internal.InternalResponse
import org.hikit.er.data.Coordinates
import org.hikit.er.manager.EventManager
import org.hikit.er.rest.EventDto
import org.springframework.beans.factory.annotation.Autowired

class EventService @Autowired constructor(private val eventManager: EventManager) {

    fun get(skip: Int, limit: Int, coordinates: Coordinates, distance: Double): InternalResponse<EventDto> =
/*        InternalResponse(
            eventManager.get(skip, limit, coordinates, distance),
            eventManager.countByDistance(coordinates, distance)
        )*/
        throw NotImplementedError()

}
