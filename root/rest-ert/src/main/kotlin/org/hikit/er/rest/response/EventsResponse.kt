package org.hikit.er.rest.response

import org.hikit.er.rest.EventDto

data class EventsResponse(
    val status: Status,
    val messages: Set<String>,
    val content: List<EventDto>,
    override val currentPage: Long,
    override val totalPages: Long,
    override val size: Long,
    override val totalCount: Long
) : RESTResponse(currentPage, totalPages, size, totalCount)