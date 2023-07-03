package org.hikit.er.service

import org.hikit.er.controller.request.LineRequest
import org.hikit.er.controller.response.internal.InternalResponse
import org.hikit.er.data.mapper.MunicipalityMapperDto
import org.hikit.er.manager.MunicipalityManager
import org.hikit.er.rest.MunicipalityDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MunicipalityService @Autowired constructor(private val municipalityManager: MunicipalityManager,
                                                 private val municipalityMapper: MunicipalityMapperDto
) {
    fun getMunicipalitiesByLineString(request: LineRequest): InternalResponse<MunicipalityDto> {
        val data = municipalityManager.getByLine(request).map { municipalityMapper.map(it) }
        return InternalResponse(data = data, totalCount = if (data.isEmpty()) 0 else data.count())
    }

    fun getById(id: String): InternalResponse<MunicipalityDto> {
        val data = municipalityManager.getById(id).map { municipalityMapper.map(it) }
        return InternalResponse(data = data, totalCount = if (data.isEmpty()) 0 else 1)
    }
}