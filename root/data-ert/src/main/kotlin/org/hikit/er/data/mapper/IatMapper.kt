package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.er.data.Coordinates
import org.hikit.er.data.Iat
import org.hikit.er.data.Image
import org.hikit.er.data.RecordDetails
import org.springframework.stereotype.Component

@Component
class IatMapper constructor(): EntityMapper<Iat> {
    override fun mapToObject(doc: Document): Iat {
        TODO("Not yet implemented")
    }

    override fun mapToDocument(obj: Iat): Document {
        TODO("Not yet implemented")
    }

}