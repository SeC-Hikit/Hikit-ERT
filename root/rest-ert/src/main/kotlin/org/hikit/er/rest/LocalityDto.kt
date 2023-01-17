package org.hikit.er.rest

import java.util.*

data class LocalityDto(
    var id: String,
    var name: String,
    var description: String,
//    var coordinates: List<CoordinatesEntity>,
//    var image: ImageEntity,
    var license: String,
//    var relatingCity: CityRefEntity,
//    var recordDetails: RecordDetailsEntity,
    var importedOn: Date
)
