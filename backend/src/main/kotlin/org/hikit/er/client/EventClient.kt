package org.hikit.er.client

import io.swagger.annotations.ApiParam
import org.apache.logging.log4j.LogManager
import org.ert.api.EventsApi
import org.openapitools.model.EventResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.client.RestClientException
import java.net.SocketTimeoutException
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.Size

@Component
class EventClient @Autowired constructor(
    private val restTemplateBuilder: RestTemplateBuilder) : EventsApi {
    private val logger = LogManager.getLogger(EventClient::class.java)

    @Value("\${endpoint.event:https://emiliaromagnaturismo.it/opendata/v1/events}") lateinit var endPointUrl: String
    override fun eventsGet(@Size(max = 2) @ApiParam(value = "Lingua") @Valid @RequestParam(required = false, value = "lang") lang: @Size(max = 2) @Valid String?,
                           @Size(max = 6) @ApiParam(value = "Codice Istat identificativo del comune") @Valid @RequestParam(required = false, value = "istat") istat: @Size(max = 6) @Valid String?,
                           @Size(max = 255) @ApiParam(value = "Filtro per nome della città, consulta l'endpoint dei comuni per l'elenco di province e comuni supportati") @Valid @RequestParam(required = false, value = "city") query: @Size(max = 255) @Valid String?,
                           @Size(max = 2) @ApiParam(value = "Filtro per nome della provincia, consulta l'endpoint dei comuni per l'elenco di province e comuni supportati") @Valid @RequestParam(required = false, value = "prov") prov: @Size(max = 2) @Valid String?,
                           @Size(max = 6) @ApiParam(value = "Codice del tematismo per il quale filtrare") @Valid @RequestParam(required = false, value = "theme") theme: @Size(max = 6) @Valid String?,
                           @Size(max = 6) @ApiParam(value = "Codice della categoria per la quale filtrare") @Valid @RequestParam(required = false, value = "category") category: @Size(max = 6) @Valid String?,
                           @Min(value = 1) @Max(value = 99999999) @ApiParam(value = "Numero di pagina da interrogare") @Valid @RequestParam(required = false, value = "page") page: @Min(value = 1) @Max(value = 99999999) @Valid Int?,
                           @Min(value = -1) @Max(value = 1000) @ApiParam(value = "Numero di risultati da restituire per ogni pagina") @Valid @RequestParam(required = false, value = "limit") limit: @Min(value = -1) @Max(value = 1000) @Valid Int?,
                           @Size(max = 23) @ApiParam(value = "Data di aggiornamento. Vengono restituiti tutti i contenuti che siano stati modificati dopo la data inserita. La data va inserita in formato [rfc3339](https://www.ietf.org/rfc/rfc3339.txt)") @Valid @RequestParam(required = false, value = "updated") updated: @Size(max = 23) @Valid String?): ResponseEntity<EventResponse>? {
        try {
            logger.info("Fetching PROV=${prov} LIMIT=${limit} PAGE=${page}")
            return restTemplateBuilder.build()
                    .getForEntity(
                            endPointUrl.plus("?prov=${prov}&limit=${limit}&page=${page}"),
                            org.openapitools.model.EventResponse::class.java
                    )
        } catch (socketConnectionTimeout: SocketTimeoutException) {
            logger.warn("The remote API timed-out, will retry on the next run")
        } catch (restClientException : RestClientException) {
            logger.warn("The remote locality API responded with an error", restClientException.cause)
        }
        return null
    }
}
