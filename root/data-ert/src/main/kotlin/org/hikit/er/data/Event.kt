package org.hikit.er.data

import java.util.*

data class Event(
    var _id: String,
    var remoteId: String,
    var title: String,
    var description: String,
    var locations: List<EventLocation>,
    var date_from: Date,
    var date_to: Date,
    var ticketing: Ticket,
    var category: List<Category>,
    var attachments: List<Image>,
    var municipality: CityRef
) {
    companion object {
        val COLLECTION_NAME: String = "ert.Event"

        const val ID: String = "_id"
        const val REMOTE_ID: String = "remoteId"
        const val TITLE: String = "title"
        const val DESCRIPTION: String = "description"
        const val LOCATIONS: String = "locations"
        const val POINTS: String = "points"
        const val DATE_FROM: String = "date_from"
        const val DATE_TO: String = "date_to"
        const val TICKETING: String = "ticketing"
        const val CATEGORY: String = "category"
        const val ATTACHMENTS: String = "attachments"
        const val MUNICIPALITY: String = "municipality"
    }
}