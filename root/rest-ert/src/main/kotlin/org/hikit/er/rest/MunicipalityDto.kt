package org.hikit.er.rest

import java.util.*

data class MunicipalityDto(
    var id: String,
    var name: String,
    var description: String,
    var coordinates: List<CoordinatesDto>,
    var image: List<ImageDto>,
    var relatingCity: CityRefDto,
    var recordDetails: RecordDetailsDto,
    var importedOn: Date,
    var geometry: List<CoordinatesDto>
)
