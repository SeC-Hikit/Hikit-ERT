package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.common.data.mapper.MultiPointCoordsMapper
import org.hikit.er.data.Locality
import org.hikit.er.data.Municipality
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MunicipalityEntityMapper @Autowired constructor(
    private val imageEntityMapper: ImageEntityMapper,
    private val refCityMapper: RefCityMapper,
    private val multiPointCoordsMapper: MultiPointCoordsMapper,
    private val coordinatesMapper: CoordinatesMapper,
    private val recordDetailsMapper: RecordDetailsMapper
) : EntityMapper<Municipality> {
    override fun mapToObject(document: Document): Municipality {
        val coordinates = document.get(Municipality.GEOMETRY, Document::class.java)
            .getList("coordinates", List::class.java).first()
        return Municipality(
            _id = document.getObjectId(Municipality.ID).toHexString(),
            remoteId = document.getString(Municipality.REMOTE_ID),
            name = document.getString(Municipality.NAME),
            description = document.getString(Municipality.DESCRIPTION),
            coordinates = multiPointCoordsMapper.mapToObject(
                document.get(Municipality.POINTS, Document::class.java)
            ),
            images = document.getList(Municipality.IMAGES, Document::class.java)
                .map { imageEntityMapper.mapToObject(it) },
            relatingCity = refCityMapper.mapToObject(document.get(Municipality.RELATING_CITY, Document::class.java)),
            recordDetails = recordDetailsMapper.mapToObject(
                document.get(
                    Municipality.RECORD_DETAILS,
                    Document::class.java
                )
            ),
            importedOn = document.getDate(Municipality.IMPORTED_ON),
            geometry = coordinates
                .map { coordinatesMapper.map(it as List<Double>) }
        )
    }

    override fun mapToDocument(entity: Municipality): Document = throw NotImplementedError()
}