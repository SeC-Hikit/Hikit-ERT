package org.hikit.er.data.mapper

import org.hikit.er.data.Locality
import org.hikit.er.rest.LocalityDto
import org.springframework.stereotype.Component

@Component
class LocalityMapperDto {
    fun map(localityDto: LocalityDto) : Locality {
        throw NotImplementedError()
    }
    fun map(locality: Locality) : LocalityDto {
        throw NotImplementedError()
    }
}