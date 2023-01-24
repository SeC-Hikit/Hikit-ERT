package org.hikit.er.data

import java.util.*

data class Locality(
    var id: String,
    var name: String,
    var description: String,
    var coordinates: List<Coordinates>,
    var image: Image,
    var license: String,
    var relatingCity: CityRef,
    var recordDetails: RecordDetails,
    var importedOn: Date
)
