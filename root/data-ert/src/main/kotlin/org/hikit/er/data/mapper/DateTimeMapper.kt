package org.hikit.er.data.mapper

import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class DateTimeMapper {
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd k:m:s")

    fun map(date: String): LocalDateTime =
        LocalDateTime.parse(date, dateTimeFormatter)
}