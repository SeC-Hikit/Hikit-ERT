package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.er.data.Locality
import org.springframework.stereotype.Component

@Component
class LocalityEntityMapper : EntityMapper<Locality> {
    override fun mapToObject(p0: Document): Locality {
        TODO("Not yet implemented")
    }

    override fun mapToDocument(p0: Locality): Document {
        TODO("Not yet implemented")
    }
}