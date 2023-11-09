package org.hikit.er.rest

import java.time.LocalDateTime
import java.util.*

data class EventDto(
    var id: String,
    var permalink: String,
    var title: String,
    var subtitle: String,
    var description: String,
    var coordinates: List<CoordinatesDto>,
    var dateFrom: Date,
    var dateTo: Date
)