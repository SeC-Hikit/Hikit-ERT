package org.hikit.er.data

import java.time.LocalDateTime

data class RecordDetails (
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        val CREATED_AT = "createAt"
        val UPDATED_AT = "updatedAt"
    }
}
