package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.er.data.Coordinates
import org.hikit.er.data.Image
import org.hikit.er.data.RecordDetails
import org.springframework.stereotype.Component

@Component
class RecordDetailsMapper constructor(): EntityMapper<RecordDetails> {
    override fun mapToObject(p0: Document): RecordDetails {
        TODO("Not yet implemented")
    }

    override fun mapToDocument(p0: RecordDetails): Document {
        TODO("Not yet implemented")
    }

}