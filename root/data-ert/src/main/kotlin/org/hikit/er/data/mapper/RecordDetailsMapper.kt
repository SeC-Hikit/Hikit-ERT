package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.er.data.Coordinates
import org.hikit.er.data.Image
import org.hikit.er.data.RecordDetails
import org.springframework.stereotype.Component

@Component
class RecordDetailsMapper constructor(private val dateTimeMapper: DateTimeMapper) : EntityMapper<RecordDetails> {
    override fun mapToObject(document: Document): RecordDetails =
        RecordDetails(
            dateTimeMapper.map(document.getString(RecordDetails.CREATED_AT)),
            dateTimeMapper.map(document.getString(RecordDetails.UPDATED_AT))
        )

    override fun mapToDocument(entity: RecordDetails): Document =
        Document(RecordDetails.CREATED_AT, dateTimeMapper.format(entity.createdAt))
            .append(RecordDetails.UPDATED_AT,dateTimeMapper.format(entity.updatedAt))


}