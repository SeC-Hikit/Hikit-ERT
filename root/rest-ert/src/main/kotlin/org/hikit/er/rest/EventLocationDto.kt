package org.hikit.er.rest


data class EventLocationDto(
    var title: String,
    var city: String,
    var province: String,
    var address: String,
    var coordinates: CoordinatesDto
) {
}