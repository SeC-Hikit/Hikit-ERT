package org.hikit.er.manager

import org.hikit.er.data.Coordinates
import org.hikit.er.data.Locality
import org.hikit.er.data.dao.LocalityDao
import org.hikit.er.data.dao.MunicipalityDao
import org.hikit.er.data.mapper.LocalityMapperDto
import org.hikit.er.data.mapper.MunicipalityMapperDto
import org.hikit.er.rest.LocalityDto
import org.hikit.er.rest.MunicipalityDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MunicipalityManager @Autowired
constructor(
    private val municipalityDao: MunicipalityDao,
    private val municipalityMapper: MunicipalityMapperDto
) {


}