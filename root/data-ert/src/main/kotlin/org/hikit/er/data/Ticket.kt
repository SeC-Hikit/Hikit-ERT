package org.hikit.er.data

data class Ticket(
        val website: String,
        val subscriptions: String,
        val full_rate: String,
        val gratuity: String,
        val type: Int,
        val entrance: String
) {
    companion object {
        val WEBSITE: String = "website"
        val SUBSCRIPTIONS: String = "subscriptions"
        val FULL_RATE: String = "full_rate"
        val GRATUITY: String = "gratuity"
        val TYPE: String = "type"
        val ENTRANCE: String = "entrance"
    }
}