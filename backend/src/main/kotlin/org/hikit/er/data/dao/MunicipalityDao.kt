package org.hikit.er.data.dao

import com.mongodb.client.MongoCollection
import org.bson.Document
import org.hikit.common.datasource.Datasource
import org.hikit.common.datasource.DocumentListMapperHelper
import org.hikit.er.controller.request.LineRequest
import org.hikit.er.data.Coordinates
import org.hikit.er.data.Municipality
import org.hikit.er.data.mapper.MunicipalityEntityMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MunicipalityDao @Autowired constructor(
    private val municipalityEntityMapper: MunicipalityEntityMapper,
    private val documentListMapperHelper: DocumentListMapperHelper<Municipality>,
    dataSource: Datasource
) {

    private val collection: MongoCollection<Document> = dataSource.db.getCollection(Municipality.COLLECTION_NAME)

    fun get(id: String): List<Municipality> {
        val documents = collection.find(
            Document("_id", id)
        )
        return documentListMapperHelper.toEntries(documents, municipalityEntityMapper)
    }

    fun getByLine(line: LineRequest): List<Municipality> {
        val coordinates =
            line.coordinates.map {
                listOf(it.longitude, it.latitude)
            }

        val query = Document(
            Municipality.GEOMETRY,
            Document(
                "\$geoIntersects",
                Document(
                    "\$geometry",
                    Document(
                        "type", "LineString"
                    ).append("coordinates", coordinates)
                )
            )
        )
        val documents = collection.find(
            query
        )
        return documentListMapperHelper.toEntries(documents, municipalityEntityMapper)
    }

    fun getByName(name: String) : List<Municipality> {
        val documents = collection.find(
            Document("name", name)
        )
        return documentListMapperHelper.toEntries(documents, municipalityEntityMapper)
    }

    fun getByPoint(point: Coordinates): List<Municipality> {
        val query = Document(
            Municipality.GEOMETRY,
            Document(
                "\$geoIntersects",
                Document(
                    "\$geometry",
                    Document(
                        "type", "Point"
                    ).append("coordinates", listOf(point.longitude, point.latitude))
                )
            )
        )
        val documents = collection.find(
            query
        )
        return documentListMapperHelper.toEntries(documents, municipalityEntityMapper)
    }
}
