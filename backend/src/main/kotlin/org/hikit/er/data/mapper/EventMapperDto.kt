package org.hikit.er.data.mapper

import org.hikit.er.data.Event
import org.hikit.er.data.Ticket
import org.hikit.er.rest.*
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
            ev.description,
            ev.locations.map {
                 EventLocationDto(
                     it.title,
                     it.city,
                     it.province,
                     it.address,
                     CoordinatesDto(
                         it.coordinates.longitude,
                         it.coordinates.latitude
                     )
                 )
            },
            ev.date_from,
            ev.date_to,
            TicketDto(
                ev.ticketing.website,
                ev.ticketing.subscriptions,
                ev.ticketing.full_rate,
                ev.ticketing.gratuity,
                ev.ticketing.type,
                ev.ticketing.entrance
            ),
            ev.category.map {
                CategoryDto(
                    it._id,
                    it.name,
                    it.parent
                )
            },
            ev.attachments.map {
                ImageDto(
                    it.url,
                    it.title,
                    it.name,
                    it.width,
                    it.height,
                    it.license,
                    it.licenseUrl
                )
            }
        )
}