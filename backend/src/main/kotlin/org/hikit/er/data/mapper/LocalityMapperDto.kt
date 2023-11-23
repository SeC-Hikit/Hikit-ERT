package org.hikit.er.data.mapper

import org.hikit.er.data.Locality
import org.hikit.er.rest.*
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*

@Component
class LocalityMapperDto {
    fun map(localityDto: LocalityDto): Locality {
        throw NotImplementedError()
    }

    fun map(lc: Locality): LocalityDto =
        LocalityDto(
            lc.remoteId,
            lc.name,
            lc.description,
            lc.coordinates.coordinates2D.map { CoordinatesDto(it[1], it[0]) },
            lc.images.map {
                ImageDto(
                    it.url, it.title, it.name, it.width,
                    it.height, it.license, it.licenseUrl
                )
            },
            CityRefDto(
                istat = lc.relatingCity.istat,
                city = lc.relatingCity.city,
                province = lc.relatingCity.province,
                province_short = lc.relatingCity.province_short,
                iat = lc.relatingCity.iat.map {
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
            ),
            recordDetails = RecordDetailsDto(
                createdAt = Date.from(lc.recordDetails.createdAt.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                updatedAt = Date.from(lc.recordDetails.updatedAt.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()) //lc.recordDetails.updatedAt.toLocalDate()
            ),
            importedOn = lc.importedOn
        )
}
