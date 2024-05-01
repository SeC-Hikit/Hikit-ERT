package org.hikit.er.data.mapper.batch

import org.hikit.er.data.*
import org.hikit.er.data.imported.EventImport
import org.openapitools.model.EventResponseData
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventMapper @Autowired constructor() {
    private val logger: Logger = LoggerFactory.getLogger(EventMapper::class.java)

    fun map(erp: EventResponseData, cityRef: CityRef): EventImport? {
        return try {
            EventImport(
                _id = "",
                remoteId = erp.id.toString(),
                title = erp.title,
                description = erp.description,
                locations = erp.locations.map { loc ->
                    EventLocation(
                        title = loc.title,
                        city = loc.city,
                        province = loc.province,
                        address = loc.address,
                        coordinates = Coordinates(
                            latitude = loc.lat.toDouble(),
                            longitude = loc.lng.toDouble()
                        )
                    )
                },
                date_from = erp.dates.from,
                date_to = erp.dates.to,
                ticketing = Ticket(
                    website = erp.ticketing.website,
                    subscriptions = erp.ticketing.subscriptions,
                    full_rate = erp.ticketing.fullRate,
                    gratuity = erp.ticketing.gratuity,
                    type = erp.ticketing.type,
                    entrance = erp.ticketing.entrance
                ),
                category = erp.category.map { att ->
                    Category(
                        _id = att.id,
                        name = att.name,
                        parent = att.parent
                    )
                },
                attachments = erp.attachments.map { att->
                    Image(
                        url = att.url,
                        title = att.title,
                        name = att.name,
                        width = att.width,
                        height = att.height,
                        license = att.license,
                        licenseUrl = att.licenseUrl
                    )
                },
                municipality = cityRef
            )
        } catch (e: Exception) {
            logger.warn("Could not map Event. Error: ${e.message}")
            null
        }
    }
}