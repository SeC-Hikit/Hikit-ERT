package org.hikit.er.data.dao

import com.mongodb.client.MongoCollection
import org.bson.Document
import org.hikit.common.datasource.Datasource
import org.hikit.er.data.Locality
import org.hikit.er.data.mapper.LocalityEntityMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LocalityDao @Autowired constructor(
    private val localityEntityMapper: LocalityEntityMapper,
    dataSource: Datasource) {

    private val collection: MongoCollection<Document> = dataSource.db.getCollection(Locality.COLLECTION_NAME)

    fun upsertOnRemoteId(localities: List<Locality>): List<Locality> {
        val localityEntities = localities.map { localityEntityMapper.mapToDocument(it) }
        return emptyList()
    }
}