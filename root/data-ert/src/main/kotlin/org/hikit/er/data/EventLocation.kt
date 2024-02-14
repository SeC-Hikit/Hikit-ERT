package org.hikit.er.data

data class EventLocation(
    var title: String,
    var city: String,
    var province: String,
    var address: String,
    var coordinates: Coordinates
) {
    companion object {
        val TITLE: String = "title"
        val CITY: String = "city"
        val PROVINCE: String = "province"
        val ADDRESS: String = "address"
        val POINTS: String = "points"
    }
}