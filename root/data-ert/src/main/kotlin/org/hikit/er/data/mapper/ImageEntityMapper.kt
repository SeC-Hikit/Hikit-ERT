package org.hikit.er.data.mapper

import org.bson.Document
import org.hikit.common.data.mapper.EntityMapper
import org.hikit.er.data.Image
import org.springframework.stereotype.Component

@Component
class ImageEntityMapper : EntityMapper<Image> {
    override fun mapToObject(doc: Document): Image =
        Image(
            url = doc.getString(Image.URL),
            title = doc.getString(Image.TITLE),
            name = doc.getString(Image.NAME),
            width = doc.getInteger(Image.WIDTH),
            height = doc.getInteger(Image.HEIGHT),
            license = doc.getString(Image.LICENSE),
            licenseUrl = doc.getString(Image.LICENSE_URL),
        )

    override fun mapToDocument(obj: Image): Document =
        Document(Image.URL, obj.url)
            .append(Image.TITLE, obj.title)
            .append(Image.NAME, obj.name)
            .append(Image.WIDTH, obj.width)
            .append(Image.HEIGHT, obj.height)
            .append(Image.LICENSE, obj.license)
            .append(Image.LICENSE_URL, obj.licenseUrl)
}