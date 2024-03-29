package org.hikit.er.data

data class Event(
        var _id: String,
        var remoteId: String,
        var title: String,
        var description: String,
        var locations: List<EventLocation>,
        var date_from: String,
        var date_to: String,
        var ticketing: Ticket,
        var category: List<Category>,
        var attachments: List<Image>,
        var municipality: CityRef
) {
    companion object {
        val COLLECTION_NAME: String = "ert.Event"

        val ID: String = "_id"
        val REMOTE_ID: String = "remoteId"
        val TITLE: String = "title"
        val DESCRIPTION: String = "description"
        val LOCATIONS: String = "locations"
        val POINTS: String = "points"
        val DATE_FROM: String = "date_from"
        val DATE_TO: String = "date_to"
        val TICKETING: String = "ticketing"
        val CATEGORY: String = "category"
        val ATTACHMENTS: String = "attachments"
        val MUNICIPALITY: String = "municipality"
    }
}