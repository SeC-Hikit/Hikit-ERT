package org.hikit.er.data.mapper.imported

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.er.data.Event
import org.hikit.er.data.mapper.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventEntityMapper @Autowired constructor(
    private val eventLocationMapper: EventLocationMapper,
    private val ticketDetailsMapper: TicketDetailsMapper,
    private val categoryDetailsMapper: CategoryDetailsMapper,
    private val refCityMapper: RefCityMapper,
    private val imageEntityMapper: ImageEntityMapper
) : EntityMapper<Event> {

    override fun mapToObject(document: Document): Event {
        return Event(
            _id = document.getObjectId(Event.ID).toHexString(),
            remoteId = document.getString(Event.REMOTE_ID),
            title = document.getString(Event.TITLE),
            description = document.getString(Event.DESCRIPTION),
            locations = document.getList(Event.LOCATIONS, Document::class.java)
                .map { eventLocationMapper.mapToObject(it) },
            date_from = document.getDate(Event.DATE_FROM),
            date_to = document.getDate(Event.DATE_TO),
            ticketing = ticketDetailsMapper.mapToObject(
                document.get(
                    Event.TICKETING,
                    Document::class.java
                )
            ),
            category = document.getList(Event.CATEGORY, Document::class.java).map {
                categoryDetailsMapper.mapToObject(it)
            },
            attachments = document.getList(Event.ATTACHMENTS, Document::class.java).map {
                imageEntityMapper.mapToObject(it)
            },
            municipality = refCityMapper.mapToObject(
                document.get(Event.MUNICIPALITY, Document::class.java)
            )
        )
    }

    override fun mapToDocument(entity: Event): Document {
        throw IllegalStateException()
    }
}