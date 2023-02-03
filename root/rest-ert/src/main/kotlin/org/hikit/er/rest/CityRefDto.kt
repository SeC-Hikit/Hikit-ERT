package org.hikit.er.rest

data class CityRefDto(
    var istat: String,
    var city: String,
    var province: String,
    var province_short: String,
    var iat: List<IatDto>
)
