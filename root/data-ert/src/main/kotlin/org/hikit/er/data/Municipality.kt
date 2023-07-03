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
    companion object {
        val COLLECTION_NAME: String  = "ert.Municipality"

        val ID: String = "_id"
        val REMOTE_ID: String = "remoteId"
        val NAME: String = "name"

        val DESCRIPTION: String = "description"
        val POINTS: String = "points"
        val IMAGES: String = "images"
        val RELATING_CITY: String = "cityRefs"
        val RECORD_DETAILS: String = "recordDetails"
        val IMPORTED_ON: String = "importedOn"
        val GEOMETRY: String = "geometry"
    }
}
