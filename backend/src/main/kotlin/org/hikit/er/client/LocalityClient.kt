package org.hikit.er.client

import org.apache.logging.log4j.LogManager
import org.ert.api.LocalitiesApi
import org.openapitools.model.LocalityResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientException
import java.net.SocketTimeoutException

@Component
class LocalityClient @Autowired constructor(
    private val restTemplateBuilder: RestTemplateBuilder) : LocalitiesApi {
    private val logger = LogManager.getLogger(LocalityClient::class.java)

    @Value("\${endpoint.locality:https://emiliaromagnaturismo.it/opendata/v1/localities}") lateinit var endpointUrl: String

    override fun localitiesGet(
        lang: String?,
        istat: String?,
        prov: String?,
        limit: Int?,
        page: Int?,
        updated: String?,
        query: String?
    ): ResponseEntity<LocalityResponse>? {
         try {
            logger.info("Fetching PROV=${prov} LIMIT=${limit} PAGE=${page}")
            return restTemplateBuilder.build()
                .getForEntity(
                    endpointUrl.plus("?prov=${prov}&limit=${limit}&page=${page}"),
                    org.openapitools.model.LocalityResponse::class.java
                )
        } catch (socketConnectionTimeout: SocketTimeoutException) {
            logger.warn("The remote API timed-out, will retry on the next run")

        } catch (restClientException : RestClientException) {
            logger.error("The remote locality API responded with an error", restClientException.cause)
        }
        return null
    }
}