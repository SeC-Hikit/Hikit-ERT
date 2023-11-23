package org.hikit.er.rest

data class TicketDto(
    var website: String,
    var subscriptions: String,
    var full_rate: String,
    var gratuity: String,
    var type: String,
    var entrance: String
)