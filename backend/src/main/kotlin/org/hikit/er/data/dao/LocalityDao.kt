package org.hikit.er.data.dao

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.FindOneAndReplaceOptions
import com.mongodb.client.model.ReturnDocument
import org.bson.Document
import org.hikit.common.datasource.Datasource
import org.hikit.common.datasource.DocumentListMapperHelper
import org.hikit.er.data.Coordinates
import org.hikit.er.data.Locality
import org.hikit.er.data.mapper.LocalityEntityMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LocalityDao @Autowired constructor(
    private val localityEntityMapper: LocalityEntityMapper,
    private val documentListMapperHelper: DocumentListMapperHelper<Locality>,
    dataSource: Datasource
) {

    private val collection: MongoCollection<Document> = dataSource.db.getCollection(Locality.COLLECTION_NAME)

    fun upsertOnRemoteId(localities: List<Locality>): List<Locality> = localities.map {
        val localityEntity = localityEntityMapper.mapToDocument(it)
        collection.findOneAndReplace(
            Document(Locality.REMOTE_ID, it.remoteId),
            localityEntity,
            FindOneAndReplaceOptions()
                .upsert(true).returnDocument(ReturnDocument.AFTER)
        )
    }.map {
        localityEntityMapper.mapToObject(it!!)
    }

    fun get(skip: Int, limit: Int, coordinates: Coordinates, metersDistance: Double): List<Locality> {
        val documents = collection.find(
            getLocalityByCoordinatesAndDistanceQuery(coordinates, metersDistance)
        ).skip(skip).limit(limit)
        return documentListMapperHelper.toEntries(documents, localityEntityMapper)
    }

    private fun getLocalityByCoordinatesAndDistanceQuery(coordinates: Coordinates, metersDistance: Double) = Document(
        Locality.POINTS, Document(
            "\$near", Document(
                "\$geometry",
                Document("type", "Point").append(
                    "coordinates",
                    listOf(coordinates.longitude, coordinates.latitude)
                )
            ).append("\$maxDistance", metersDistance)
        )
    )

    fun count(coordinates: Coordinates, distance: Double) =
        collection.find(
            getLocalityByCoordinatesAndDistanceQuery(
                coordinates,
                distance
            ),
        ).count()


}
