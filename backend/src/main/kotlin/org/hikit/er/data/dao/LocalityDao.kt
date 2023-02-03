package org.hikit.er.data.dao

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.FindOneAndReplaceOptions
import com.mongodb.client.model.ReturnDocument
import org.bson.Document
import org.hikit.common.datasource.Datasource
import org.hikit.er.data.Coordinates
import org.hikit.er.data.Locality
import org.hikit.er.data.mapper.LocalityEntityMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class LocalityDao @Autowired constructor(
    private val localityEntityMapper: LocalityEntityMapper,
    dataSource: Datasource
) {

    private val collection: MongoCollection<Document> = dataSource.db.getCollection(Locality.COLLECTION_NAME)

    fun upsertOnRemoteId(localities: List<Locality>): List<Locality> =
        localities.map {
            val localityEntity = localityEntityMapper.mapToDocument(it)
            collection.findOneAndReplace(
                Document(Locality.REMOTE_ID, it.remoteId),
                localityEntity, FindOneAndReplaceOptions().upsert(true)
                    .returnDocument(ReturnDocument.AFTER)
            )
        }.map {
            localityEntityMapper.mapToObject(it!!)
        }

    fun get(skip: Int, limit: Int, coordinates: Coordinates, distance: Double): List<Locality> {
        collection.find(
                Document(
                    POINTS,
                    getPointNearSearchQuery(coordinates.longitude,
                        coordinates.latitude, distance)
                )
            )
            .skip(skip)
            .limit(limit)
    }

    fun count(latitude: Double, longitude: Double, distance: Double) {
        collection.countDocuments(Document(
            Document(
                POINTS,
                getPointNearSearchQuery(
                    longitude,
                    latitude, distance)
            )
        ))
    }
}