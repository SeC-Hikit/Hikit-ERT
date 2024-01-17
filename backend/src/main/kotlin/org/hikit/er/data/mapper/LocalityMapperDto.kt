package org.hikit.er.data.mapper

import org.hikit.er.data.Locality
import org.hikit.er.rest.CoordinatesDto
import org.hikit.er.rest.ImageDto
import org.hikit.er.rest.LocalityDto
import org.hikit.er.rest.RecordDetailsDto
import org.springframework.stereotype.Component
import java.time.ZoneId
import java.util.*

@Component
class LocalityMapperDto constructor(private val cityRefMapperDto: CityRefMapperDto){

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
            cityRefMapperDto.map(lc.relatingCity),
            recordDetails = RecordDetailsDto(
                createdAt = Date.from(lc.recordDetails.createdAt.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                updatedAt = Date.from(lc.recordDetails.updatedAt.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()) //lc.recordDetails.updatedAt.toLocalDate()
            ),
            importedOn = lc.importedOn
        )
}
