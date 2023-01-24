package org.hikit.er.data

import java.util.*

data class Locality(
    var _id: String,
    var remoteId: String,
    var name: String,
    var description: String,
    var coordinates: List<Coordinates>,
    var images: List<Image>,
    var relatingCity: CityRef,
    var recordDetails: RecordDetails,
    var importedOn: Date
)
