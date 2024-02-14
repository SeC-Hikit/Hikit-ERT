package org.hikit.er.service

import org.hikit.er.controller.response.internal.InternalResponse
import org.hikit.er.manager.EventManager
import org.hikit.er.rest.EventDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EventService @Autowired constructor(private val eventManager: EventManager) {

    fun getByIstat(istatCode: String,
                   skip: Int,
                   limit: Int): InternalResponse<EventDto> {
        val response = eventManager.getByIstat(istatCode, skip, limit)
        return InternalResponse(response, eventManager.countByIstat(istatCode))
    }
}