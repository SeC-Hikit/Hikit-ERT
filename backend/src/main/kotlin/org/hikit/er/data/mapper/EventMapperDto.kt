package org.hikit.er.data.mapper

import org.hikit.er.data.Event
import org.hikit.er.rest.EventDto
import org.springframework.stereotype.Component

@Component
class EventMapperDto {
    fun map(eventDto: EventDto): Event {
        throw NotImplementedError()
    }

    fun map(ev: Event): EventDto =
            EventDto(
                    ev.remoteId,
                    ev.title,
                    ev.description
            )
}