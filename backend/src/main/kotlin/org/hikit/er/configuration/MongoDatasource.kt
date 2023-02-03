package org.hikit.er.configuration

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import org.apache.logging.log4j.LogManager
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.pojo.PojoCodecProvider
import org.hikit.common.datasource.Datasource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.net.URI
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Configuration
open class MongoDataSource @Autowired constructor(
    @Value("\${db.name}") private val dbName : String,
    @Value("\${db.uri}") private val uri : String) : Datasource {

    private val client: MongoClient
    private val logger = LogManager.getLogger(MongoDataSource::class.java)

    init {
        logger.info("Setting connection to DB '${dbName}'. Connection String: '%s'", dbName, uri)
        val pojoCodecRegistry = CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
        )
        val mongoSettings = MongoClientSettings.builder()
            .codecRegistry(pojoCodecRegistry)
            .applyConnectionString(
                ConnectionString(uri)
            )

            .build()
        client = MongoClients.create(mongoSettings)
    }

    override fun getClient() = client

    override fun getDB() = client.getDatabase(dbName)

    override fun getDBName() = dbName


}
