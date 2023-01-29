package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.er.data.*
import org.springframework.stereotype.Component

@Component
class ContactMapper : EntityMapper<Contact> {
    override fun mapToObject(doc: Document): Contact =
        Contact(
            label = doc.getString(Contact.LABEL),
            type = doc.getString(Contact.TYPE),
            value = doc.getString(Contact.VALUE),
        )

    override fun mapToDocument(obj: Contact): Document =
        Document(Contact.LABEL, obj.label)
            .append(Contact.TYPE, obj.type)
            .append(Contact.VALUE, obj.value)

}