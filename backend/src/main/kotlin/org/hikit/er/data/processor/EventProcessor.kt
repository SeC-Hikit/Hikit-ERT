package org.hikit.er.data.processor

import org.hikit.er.client.EventClient
import org.hikit.er.data.Coordinates
import org.hikit.er.data.Event
import org.hikit.er.data.mapper.batch.EventMapper
import org.hikit.er.manager.MunicipalityManager
import org.openapitools.model.EventResponseData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventProcessor @Autowired constructor(
    private val eventMapper: EventMapper,
    private val eventClient: EventClient,
    private val municipalityController: MunicipalityManager
) {
    fun processBatch(batchPage: Int): EventBatch {
        val eventsGet = eventClient
            .eventsGet("it", "", "", "BO", "", "", batchPage, 10, "")
            ?: return EventBatch(0, 0, emptyList())
        val page = eventsGet.let { it.body?.meta?.currentPage }?.toInt()
        val of = eventsGet.let { it.body?.meta?.lastPage }?.toInt()
        val mappedEntities = eventsGet.let { it.body?.data }
            ?.mapNotNull { loc: EventResponseData -> mapEvent(loc) }?.toList()!!
        return EventBatch(page!!, of!!, mappedEntities)
    }

    private fun mapEvent(loc: EventResponseData): Event? {
        val locationData = loc.locations.first()
        val municipalityByPoint = municipalityController.getByPoint(
            Coordinates(locationData.lat.toDouble(), locationData.lng.toDouble())
        )
        val municipality = municipalityByPoint.first()
        return eventMapper.map(loc, municipality.relatingCity)
    }
}
