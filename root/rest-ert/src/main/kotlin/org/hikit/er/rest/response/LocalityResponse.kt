package org.hikit.er.rest.response

import org.hikit.er.rest.LocalityDto

data class LocalityResponse (
    val status: Status,
    val messages: Set<String>,
    val content: List<LocalityDto>,
    override val currentPage: Long,
    override val totalPages: Long,
    override val size: Long,
    override val totalCount: Long
) : RESTResponse(currentPage, totalPages, size, totalCount)