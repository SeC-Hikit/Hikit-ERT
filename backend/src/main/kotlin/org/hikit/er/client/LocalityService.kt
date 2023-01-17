package org.hikit.er.client

import Coordinates
import org.hikit.er.data.mapper.LocalityMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocalityService @Autowired constructor(
    private val localityMapper: LocalityMapper
) {

    fun get(page: Int, skip: Int, coordinates: Coordinates, distance: Double) {
        throw NotImplementedError()
    }

    fun getByText(page: Int, skip: Int, coordinates: Coordinates, distance: Double) {
        throw NotImplementedError()
    }


}