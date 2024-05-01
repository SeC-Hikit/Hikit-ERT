package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.common.data.mapper.MultiPointCoords2D
import org.hikit.common.data.mapper.MultiPointCoordsMapper
import org.hikit.er.data.Event
import org.hikit.er.data.imported.EventImport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.IllegalStateException
import java.time.LocalDate

@Component
class ImportedEventEntityMapper @Autowired constructor(
    private val eventLocationMapper: EventLocationMapper,
    private val multiPointCoordinatesMapper: MultiPointCoordsMapper,
    private val ticketDetailsMapper: TicketDetailsMapper,
    private val categoryDetailsMapper: CategoryDetailsMapper,
    private val refCityMapper: RefCityMapper,
    private val imageEntityMapper: ImageEntityMapper
) : EntityMapper<EventImport> {

    override fun mapToDocument(entity: EventImport): Document {
        val coords: List<List<Double>> = entity.locations.map {
            listOf(it.coordinates.longitude, it.coordinates.latitude)
        }
        val locationsAsPoints = MultiPointCoords2D(coords)
        return Document()
            .append(Event.REMOTE_ID, entity.remoteId)
            .append(Event.TITLE, entity.title)
            .append(Event.DESCRIPTION, entity.description)
            .append(Event.DATE_FROM, LocalDate.parse(entity.date_from))
            .append(Event.DATE_TO, LocalDate.parse(entity.date_to))
            .append(Event.LOCATIONS, entity.locations.map { eventLocationMapper.mapToDocument(it) })
            .append(
                Event.POINTS,
                multiPointCoordinatesMapper.mapToDocument(locationsAsPoints)
            )
            .append(Event.TICKETING, ticketDetailsMapper.mapToDocument(entity.ticketing))
            .append(Event.CATEGORY, entity.category.map { categoryDetailsMapper.mapToDocument(it) })
            .append(Event.ATTACHMENTS, entity.attachments.map { imageEntityMapper.mapToDocument(it) })
            .append(Event.MUNICIPALITY, refCityMapper.mapToDocument(entity.municipality))
    }

    override fun mapToObject(document: Document?): EventImport {
        throw IllegalStateException()
    }
}