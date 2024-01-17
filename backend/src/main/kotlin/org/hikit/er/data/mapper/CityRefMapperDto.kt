package org.hikit.er.data.mapper

import org.hikit.er.data.CityRef
import org.hikit.er.rest.CityRefDto
import org.hikit.er.rest.ContactDto
import org.hikit.er.rest.CoordinatesDto
import org.hikit.er.rest.IatDto
import org.springframework.stereotype.Component

@Component
class CityRefMapperDto {
    fun map(cityRef: CityRef) =
        CityRefDto(
            istat = cityRef.istat,
            city = cityRef.city,
            province = cityRef.province,
            province_short = cityRef.province_short,
            iat = cityRef.iat.map {
                IatDto(
                    name = it.name,
                    address = it.address,
                    number = it.number,
                    CoordinatesDto(
                        it.coordinates.longitude,
                        it.coordinates.latitude
                    ),
                    it.contacts.map { c -> ContactDto(c.label, c.type, c.value) }
                )
            }
        )
}