package org.hikit.er.data.mapper.batch

import org.hikit.er.data.*
import org.hikit.er.data.mapper.DateTimeMapper
import org.openapitools.model.LocalityResponseData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

@Component
class LocalityMapper @Autowired constructor(private val dateTimeMapper: DateTimeMapper){

    fun map(lrp: LocalityResponseData): Locality =
        Locality(
            _id = "",
            remoteId = lrp.id.toString(),
            name = lrp.title,
            description = lrp.content,
            relatingCity = CityRef(
                istat = lrp.location.city.istat.toString(),
                city = lrp.location.city.city,
                province = lrp.location.city.province,
                province_short = lrp.location.city.provinceShort,
                iat = lrp.iat.map { iat ->
                    Iat(
                        name = iat.name,
                        address = iat.location.address,
                        number = iat.location.number,
                        coordinates = Coordinates(
                            latitude = iat.location.lat.toDouble(),
                            longitude = iat.location.lng.toDouble(),
                        ),
                        contacts = iat.contacts.map { contact ->
                            Contact(contact.label, contact.type, contact.value)
                        },
                    )
                }
            ),
            coordinates =
                Coordinates(
                    latitude = lrp.location.lat.toDouble(),
                    longitude = lrp.location.lng.toDouble()
                ),
            images = lrp.attachments.map { att ->
                Image(
                    url = att.url,
                    thumb = att.thumbUrl,
                    title = att.title,
                    name = att.name,
                    width = att.width,
                    height = att.height,
                    license = att.license,
                    licenseUrl = att.licenseUrl
                )
            },
            recordDetails = RecordDetails(
                createdAt = dateTimeMapper.map(lrp.createdAt),
                updatedAt = dateTimeMapper.map(lrp.updatedAt)
            ),
            importedOn = Date()
        )
}