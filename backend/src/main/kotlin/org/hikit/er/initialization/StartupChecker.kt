package org.hikit.er.initialization

import com.mongodb.client.model.Indexes
import org.hikit.common.datasource.Datasource
import org.hikit.er.data.Locality
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class StartupChecker @Autowired constructor(private val dataSource: Datasource) {

    @PostConstruct
    fun initIndexes() {
        val db = dataSource.db
        val pointGeoIndex = db.getCollection(Locality.COLLECTION_NAME)
            .createIndex(Indexes.geo2dsphere(Locality.POINTS))

    }
}