package org.hikit.er.rest

import java.time.LocalDateTime
import java.util.*

data class LocalityDto(
    var id: String,
    var name: String,
    var description: String,
    var coordinates: List<CoordinatesDto>,
    var image: List<ImageDto>,
    var relatingCity: CityRefDto,
    var recordDetails: RecordDetailsDto,
    var importedOn: Date
)
