package org.hikit.er.data

data class Coordinates(
    var latitude: Double,
    var longitude: Double,
) {
    companion object {
        val LATITUDE = "latitude"
        val LONGITUDE = "longitude"
    }
}
