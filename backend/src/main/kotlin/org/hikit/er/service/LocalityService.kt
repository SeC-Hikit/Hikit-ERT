package org.hikit.er.service

import org.hikit.er.client.LocalityClient
import org.hikit.er.data.Coordinates
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocalityService @Autowired constructor(
    private val localityClient: LocalityClient
) {

    fun get(page: Int, skip: Int, coordinates: Coordinates, distance: Double) {
        localityClient.localitiesGet("it", "40036", "BO", 10, page, "", "");
        throw NotImplementedError()
    }

    fun getByText(page: Int, skip: Int, coordinates: Coordinates, distance: Double) {
        throw NotImplementedError()
    }


}