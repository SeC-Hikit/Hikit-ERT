package org.hikit.er.data.mapper

import org.hikit.er.data.Locality
import org.hikit.er.rest.LocalityDto
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
interface LocalityMapper {
    fun map(localityDto: LocalityDto) : Locality
    fun map(locality: Locality) : LocalityDto
}