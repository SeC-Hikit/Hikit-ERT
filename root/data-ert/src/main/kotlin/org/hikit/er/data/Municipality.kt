package org.hikit.er.data

import org.hikit.common.data.mapper.MultiPointCoords2D
import java.util.*

class Municipality(
    var _id: String,
    var remoteId: String,
    var name: String,
    var description: String,
    var coordinates: MultiPointCoords2D,
    var images: List<Image>,
    var relatingCity: CityRef,
    var recordDetails: RecordDetails,
    var importedOn: Date,
    var geometry: List<Coordinates>,
) {
}
