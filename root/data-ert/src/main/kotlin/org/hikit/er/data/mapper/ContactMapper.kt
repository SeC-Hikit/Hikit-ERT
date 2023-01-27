package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.er.data.*
import org.springframework.stereotype.Component

@Component
class ContactMapper: EntityMapper<Contact> {
    override fun mapToObject(doc: Document): Contact {
        TODO("Not yet implemented")
    }

    override fun mapToDocument(obj: Contact): Document {
        TODO("Not yet implemented")
    }

}