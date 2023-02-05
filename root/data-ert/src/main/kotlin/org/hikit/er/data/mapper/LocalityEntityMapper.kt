package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.common.data.mapper.MultiPointCoordsMapper
import org.hikit.er.data.Locality
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LocalityEntityMapper @Autowired constructor(
    private val imageEntityMapper: ImageEntityMapper,
    private val refCityMapper: RefCityMapper,
    private val multiPointCoordsMapper: MultiPointCoordsMapper,
    private val recordDetailsMapper: RecordDetailsMapper
) : EntityMapper<Locality> {
    override fun mapToObject(document: Document): Locality {
        return Locality(
            _id = document.getObjectId(Locality.ID).toHexString(),
            remoteId = document.getString(Locality.REMOTE_ID),
            name = document.getString(Locality.NAME),
            description = document.getString(Locality.DESCRIPTION),
            coordinates = multiPointCoordsMapper.mapToObject(
                document.get(Locality.COORDINATES, Document::class.java)
            ),
            images = document.getList(Locality.IMAGES, Document::class.java)
                .map { imageEntityMapper.mapToObject(it) },
            relatingCity = refCityMapper.mapToObject(document.get(Locality.RELATING_CITY, Document::class.java)),
            recordDetails = recordDetailsMapper.mapToObject(
                document.get(
                    Locality.RECORD_DETAILS,
                    Document::class.java
                )
            ),
            importedOn = document.getDate(Locality.IMPORTED_ON)
        )
    }

    override fun mapToDocument(entity: Locality): Document =
        Document()
            .append(Locality.REMOTE_ID, entity.remoteId)
            .append(Locality.NAME, entity.name)
            .append(Locality.DESCRIPTION, entity.description)
            .append(Locality.COORDINATES, multiPointCoordsMapper.mapToDocument(entity.coordinates))
            .append(Locality.IMAGES, entity.images.map { imageEntityMapper.mapToDocument(it) })
            .append(Locality.RELATING_CITY, refCityMapper.mapToDocument(entity.relatingCity))
            .append(Locality.RECORD_DETAILS, recordDetailsMapper.mapToDocument(entity.recordDetails))
            .append(Locality.IMPORTED_ON, entity.importedOn)
}