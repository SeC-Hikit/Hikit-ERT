package org.hikit.er.data.processor
/*
import org.hikit.er.client.EventClient
import org.hikit.er.data.mapper.batch.EventMapper
import org.openapitools.model.EventResponseData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventProcessor @Autowired constructor(
        private val eventMapper: EventMapper,
        private val eventClient: EventClient)
{
    fun processBatch(batchPage: Int) : EventBatch {
        val eventsGet = eventClient
                .eventsGet("it", "", "BO", 10, batchPage, "", "")
                ?: return EventBatch(0, 0, emptyList())
        val page = eventsGet.let { it.body?.meta?.currentPage }?.toInt()
        val of = eventsGet.let { it.body?.meta?.lastPage }?.toInt()
        val mappedEntities = eventsGet.let { it.body?.data }
            ?.mapNotNull { loc : EventResponseData -> eventMapper.map(loc) }?.toList()!!
        return EventBatch(page!!, of!!, mappedEntities)
    }
}
*/