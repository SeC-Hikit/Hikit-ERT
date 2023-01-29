package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.er.data.*
import org.springframework.stereotype.Component

@Component
class IatMapper constructor(private val contactMapper: ContactMapper) : EntityMapper<Iat> {
    override fun mapToObject(doc: Document): Iat {
        val longLat = doc.getList(Iat.COORDINATES, Double::class.java)
        return Iat(
            name = doc.getString(Iat.NAME),
            address = doc.getString(Iat.ADDRESS),
            number = doc.getString(Iat.NUMBER),
            coordinates = Coordinates(
                longitude = longLat[0],
                latitude = longLat[1]
            ),
            contacts = doc.getList(Iat.CONTACTS, Document::class.java)
                .map { contactMapper.mapToObject(it) }
        )
    }

    override fun mapToDocument(entity: Iat): Document =
        Document(Iat.NAME, entity.name)
            .append(Iat.ADDRESS, entity.address)
            .append(Iat.NUMBER, entity.address)
            .append(
                Locality.COORDINATES,
                arrayOf(
                    entity.coordinates.longitude,
                    entity.coordinates.latitude
                )
            )
            .append(Iat.CONTACTS,
                entity.contacts.map { contactMapper.mapToDocument(it) })

}