package org.hikit.er.service

import org.hikit.er.controller.response.internal.InternalResponse
import org.hikit.er.data.Coordinates
import org.hikit.er.manager.LocalityManager
import org.hikit.er.rest.LocalityDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocalityService @Autowired constructor(private val localityManager: LocalityManager) {

    fun getByIstat(istatCode: String): InternalResponse<LocalityDto> {
        val response = localityManager.getByIstat(istatCode)
        return InternalResponse(
            response, response.size
        )
    }

    fun get(skip: Int, limit: Int, coordinates: Coordinates, distance: Double): InternalResponse<LocalityDto> =
        InternalResponse(
            localityManager.get(skip, limit, coordinates, distance),
            localityManager.countByDistance(coordinates, distance)
        )

}