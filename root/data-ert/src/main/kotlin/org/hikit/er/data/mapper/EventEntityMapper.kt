package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.common.data.mapper.MultiPointCoords2D
import org.hikit.common.data.mapper.MultiPointCoordsMapper
import org.hikit.er.data.Event
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventEntityMapper @Autowired constructor(
    private val eventLocationMapper: EventLocationMapper,
    private val multiPointCoordinatesMapper: MultiPointCoordsMapper,
    private val ticketDetailsMapper: TicketDetailsMapper,
    private val categoryDetailsMapper: CategoryDetailsMapper,
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
            date_from = document.getString(Event.DATE_FROM),
            date_to = document.getString(Event.DATE_TO),
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
            }
        )
    }

    override fun mapToDocument(entity: Event): Document {
        val coords: List<List<Double>> = entity.locations.map {
            listOf(it.coordinates.longitude, it.coordinates.latitude)
        }
        val locationsAsPoints = MultiPointCoords2D(coords)
        return Document()
            .append(Event.REMOTE_ID, entity.remoteId)
            .append(Event.TITLE, entity.title)
            .append(Event.DESCRIPTION, entity.description)
            .append(Event.DATE_FROM, entity.date_from)
            .append(Event.DATE_TO, entity.date_to)
            .append(
                Event.POINTS,
                multiPointCoordinatesMapper.mapToDocument(locationsAsPoints)
            )
            .append(Event.TICKETING, ticketDetailsMapper.mapToDocument(entity.ticketing))
            .append(Event.CATEGORY, entity.category.map { categoryDetailsMapper.mapToDocument(it) })
            .append(Event.ATTACHMENTS, entity.attachments.map { imageEntityMapper.mapToDocument(it) })
    }
}