package org.hikit.er.service

import org.hikit.er.controller.response.internal.LocalityInternalResponse
import org.hikit.er.data.Coordinates
import org.hikit.er.manager.LocalityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocalityService @Autowired constructor(private val localityManager: LocalityManager) {

    fun getByIstat(istatCode: String): LocalityInternalResponse {
        val response = localityManager.getByIstat(istatCode)
        return LocalityInternalResponse(
            response, response.size
        )
    }

    fun get(skip: Int, limit: Int, coordinates: Coordinates, distance: Double): LocalityInternalResponse =
        LocalityInternalResponse(
            localityManager.get(skip, limit, coordinates, distance),
            localityManager.countByDistance(coordinates, distance)
        )

}