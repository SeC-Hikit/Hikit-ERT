package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.common.data.mapper.MultiPointCoordsMapper
import org.hikit.er.data.Event
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventEntityMapper @Autowired constructor(
        private val imageEntityMapper: ImageEntityMapper,
        private val refCityMapper: RefCityMapper,
        private val multiPointCoordinatesMapper: MultiPointCoordsMapper,
        private val recordDetailsMapper: RecordDetailsMapper
) : EntityMapper<Event> {

    override fun mapToObject(document: Document): Event {
        return Event(
                _id = document.getObjectId(Event.ID).toHexString(),
                remoteId = document.getString(Event.REMOTE_ID),
                title = document.getString(Event.TITLE),
                description = document.getString(Event.DESCRIPTION)
        )
    }

    override fun mapToDocument(entity: Event): Document =
        Document()
                .append(Event.REMOTE_ID, entity.remoteId)
                .append(Event.TITLE, entity.title)
                .append(Event.DESCRIPTION, entity.description)
}