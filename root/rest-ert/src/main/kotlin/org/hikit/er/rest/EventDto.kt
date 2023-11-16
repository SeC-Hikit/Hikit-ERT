package org.hikit.er.rest

import java.time.LocalDateTime
import java.util.*

data class EventDto(
    var id: String,
    var title: String,
    var description: String
)