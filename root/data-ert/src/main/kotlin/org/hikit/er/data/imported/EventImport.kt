package org.hikit.er.data.imported

import org.hikit.er.data.*

data class EventImport(
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
)