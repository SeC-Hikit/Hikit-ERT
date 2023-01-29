package org.hikit.er.manager

import org.hikit.er.data.Locality
import org.hikit.er.data.dao.LocalityDao
import org.hikit.er.data.mapper.LocalityEntityMapper
import org.hikit.er.rest.LocalityDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LocalityManager @Autowired
constructor(
    private val localityDao: LocalityDao,
) {
    fun upsertOnRemoteId(localities: List<Locality>): List<Locality> {
        localityDao.upsertOnRemoteId(localities);
        return emptyList()
    }
}