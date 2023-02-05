package org.hikit.er.data

import org.hikit.common.data.mapper.MultiPointCoords2D
import java.util.*

data class Locality(
    var _id: String,
    var remoteId: String,
    var name: String,
    var description: String,
    var coordinates: MultiPointCoords2D,
    var images: List<Image>,
    var relatingCity: CityRef,
    var recordDetails: RecordDetails,
    var importedOn: Date
) {
    companion object {
        val COLLECTION_NAME: String  = "ert.Locality"

        val ID: String = "_id"
        val REMOTE_ID: String = "remoteId"
        val NAME: String = "name"
        val DESCRIPTION: String = "description"
        val COORDINATES: String = "coordinates"
        val IMAGES: String = "images"
        val RELATING_CITY: String = "cityRefs"
        val RECORD_DETAILS: String = "recordDetails"
        val IMPORTED_ON: String = "importedOn"
    }
}
