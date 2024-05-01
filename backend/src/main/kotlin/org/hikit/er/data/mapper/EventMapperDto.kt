package org.hikit.er.data.mapper

import org.hikit.er.data.Event
import org.hikit.er.rest.*
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat

@Component
class EventMapperDto constructor(private val cityRefMapperDto: CityRefMapperDto){
    fun map(ev: Event): EventDto {
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        return EventDto(
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
            simpleDateFormat.format(ev.date_from),
            simpleDateFormat.format(ev.date_to),
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
            cityRefMapperDto.map(ev.municipality),
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
}