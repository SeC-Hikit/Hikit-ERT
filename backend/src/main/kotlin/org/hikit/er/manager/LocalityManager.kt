package org.hikit.er.manager

import org.hikit.er.data.Coordinates
import org.hikit.er.data.Locality
import org.hikit.er.data.dao.LocalityDao
import org.hikit.er.data.mapper.LocalityMapperDto
import org.hikit.er.rest.LocalityDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LocalityManager @Autowired
constructor(
    private val localityDao: LocalityDao,
    private val localityMapper: LocalityMapperDto
) {

    fun get(skip: Int, limit: Int, coordinates: Coordinates, distance: Double) : List<LocalityDto> =
        localityDao.get(skip, limit, coordinates, distance)
            .map { localityMapper.map(it) }


    fun upsertOnRemoteId(localities: List<Locality>): List<Locality> =
        localityDao.upsertOnRemoteId(localities);

    fun countByDistance(coordinates: Coordinates, distance: Double): Long =
        localityDao.count(coordinates.latitude, coordinates.longitude, distance)


}