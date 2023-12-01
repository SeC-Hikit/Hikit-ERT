package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.common.data.mapper.MultiPointCoordsMapper
import org.hikit.er.data.Coordinates
import org.hikit.er.data.EventLocation
import org.hikit.er.data.Locality
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventLocationMapper @Autowired constructor(
        private val multiPointCoordsMapper: MultiPointCoordsMapper
) : EntityMapper<EventLocation> {

    override fun mapToObject(doc: Document): EventLocation {
        val longLat = doc.get(EventLocation.COORDINATES, List::class.java)
        return EventLocation(
            title = doc.getString(EventLocation.TITLE),
            city = doc.getString(EventLocation.CITY),
            province = doc.getString(EventLocation.PROVINCE),
            address = doc.getString(EventLocation.ADDRESS),
            coordinates = Coordinates(
                longitude = longLat[0] as Double,
                latitude = longLat[1] as Double
            )
        )
    }

    override fun mapToDocument(obj: EventLocation): Document =
        Document()
            .append(EventLocation.TITLE, obj.title)
            .append(EventLocation.CITY, obj.city)
            .append(EventLocation.PROVINCE, obj.province)
            .append(EventLocation.ADDRESS, obj.address)
            .append(Locality.POINTS,
                listOf(
                    obj.coordinates.longitude.toFloat(),
                    obj.coordinates.latitude.toFloat()
                )
            )
}