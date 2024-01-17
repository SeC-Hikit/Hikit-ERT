package org.hikit.er.data.dao

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.FindOneAndReplaceOptions
import com.mongodb.client.model.ReturnDocument
import org.bson.Document
import org.hikit.common.datasource.Datasource
import org.hikit.common.datasource.DocumentListMapperHelper
import org.hikit.er.data.CityRef
import org.hikit.er.data.Event
import org.hikit.er.data.mapper.EventEntityMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventDao @Autowired constructor(
    private val eventEntityMapper: EventEntityMapper,
    private val documentListMapperHelper: DocumentListMapperHelper<Event>,
    dataSource: Datasource
) {
    private val collection: MongoCollection<Document> = dataSource.db.getCollection(Event.COLLECTION_NAME)

    fun upsertOnRemoteId(events: List<Event>): List<Event> = events.map {
        val eventDocument = eventEntityMapper.mapToDocument(it)
        collection.findOneAndReplace(
            Document(Event.REMOTE_ID, it.remoteId),
            eventDocument,
            FindOneAndReplaceOptions()
                .upsert(true)
                .returnDocument(ReturnDocument.AFTER)
        )
    }.map {
        eventEntityMapper.mapToObject(it!!)
    }

    fun getByIstatCode(istat: String, skip: Int, limit: Int): List<Event> {
        val documents = collection.find(Document(Event.MUNICIPALITY + "." + CityRef.ISTAT, istat))
            .limit(limit).skip(skip)
        return documentListMapperHelper.toEntries(documents, eventEntityMapper)
    }

    fun countByIstat(istat: String): Int =
        collection.countDocuments(
            Document(Event.MUNICIPALITY + "." + CityRef.ISTAT, istat))
            .toInt()

}