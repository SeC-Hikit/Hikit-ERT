package org.hikit.er.manager

import org.hikit.er.controller.request.LineRequest
import org.hikit.er.data.Municipality
import org.hikit.er.data.dao.MunicipalityDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MunicipalityManager @Autowired
constructor(
    private val municipalityDao: MunicipalityDao,
) {

    fun getById(id: String): List<Municipality> =
        municipalityDao.get(id)

    fun getByLine(line: LineRequest): List<Municipality> =
        municipalityDao.getByLine(line)

    fun getByName(name: String): List<Municipality> =
        municipalityDao.getByName(name)

}