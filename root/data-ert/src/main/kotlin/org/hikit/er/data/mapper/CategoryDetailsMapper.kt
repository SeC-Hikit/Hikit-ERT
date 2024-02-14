package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.er.data.Category
import org.springframework.stereotype.Component

@Component
class CategoryDetailsMapper : EntityMapper<Category> {
    override fun mapToObject(doc: Document?): Category =
        Category(
            _id = if (doc != null) doc.getInteger(Category.ID) else 0,
            name = if (doc != null) doc.getString(Category.NAME) else "",
            parent = if (doc != null) doc.getInteger(Category.PARENT) else 0
        )

    override fun mapToDocument(obj: Category): Document =
        Document()
            .append(Category.ID, obj._id)
            .append(Category.NAME, obj.name)
            .append(Category.PARENT, obj.parent)
}