package org.hikit.er.data.processor

import org.hikit.er.client.LocalityClient
import org.hikit.er.data.mapper.batch.LocalityMapper
import org.openapitools.model.LocalityResponseData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LocalityProcessor @Autowired constructor(
    private val localityMapper: LocalityMapper,
    private val localityClient: LocalityClient)
{
    fun processBatch(batchPage: Int) : LocalityBatch {
        val localitiesGet = localityClient
            .localitiesGet("it", "", "BO", 10, batchPage, "", "")
            ?: return LocalityBatch(0, 0, emptyList())
        val page = localitiesGet.let { it.body?.meta?.currentPage }?.toInt()
        val of = localitiesGet.let { it.body?.meta?.lastPage }?.toInt()
        val mappedEntities = localitiesGet.let { it.body?.data }
            ?.mapNotNull { loc : LocalityResponseData -> localityMapper.map(loc) }?.toList()!!
        return LocalityBatch(page!!, of!!, mappedEntities)
    }
}