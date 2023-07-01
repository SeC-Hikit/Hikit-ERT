package org.hikit.er.service

import org.hikit.er.controller.request.LineRequest
import org.hikit.er.controller.response.internal.InternalResponse
import org.hikit.er.manager.MunicipalityManager
import org.hikit.er.rest.MunicipalityDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MunicipalityService @Autowired constructor(private val municipalityManager: MunicipalityManager) {
    fun getMunicipalitiesByLineString(request: LineRequest): InternalResponse<MunicipalityDto> {
        throw NotImplementedError()
    }
}