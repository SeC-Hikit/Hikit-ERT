package org.hikit.er.rest

import java.time.LocalDateTime

data class RecordDetailsDto (
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime,
)
