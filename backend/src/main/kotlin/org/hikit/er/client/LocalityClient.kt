package org.hikit.er.client

import org.ert.api.LocalitiesApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class LocalityClient @Autowired constructor(
    private val restTemplateBuilder: RestTemplateBuilder) : LocalitiesApi {

    @Value("\${endpoint.locality:abc}") lateinit var endpointUrl: String

    override fun localitiesGet(
        lang: String?,
        istat: String?,
        prov: String?,
        limit: Int?,
        page: Int?,
        updated: String?,
        query: String?
    ): ResponseEntity<org.openapitools.model.LocalityResponse> {
        return restTemplateBuilder.build()
            .getForEntity(endpointUrl, org.openapitools.model.LocalityResponse::class.java)
    }
}