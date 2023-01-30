package org.hikit.er.data.mapper.batch

import org.hikit.er.data.*
import org.hikit.er.data.mapper.DateTimeMapper
import org.openapitools.model.LocalityResponseData
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class LocalityMapper @Autowired constructor(private val dateTimeMapper: DateTimeMapper) {

    private val logger: Logger = LoggerFactory.getLogger(LocalityMapper::class.java)

    fun map(lrp: LocalityResponseData): Locality? {
        return try {
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
                        title = att.title ?: "",
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
        } catch (e: Exception) {
            logger.warn("Could not map Locality. Error: ${e.message}")
            null
        }
    }

}