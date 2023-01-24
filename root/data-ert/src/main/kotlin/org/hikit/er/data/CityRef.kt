package org.hikit.er.data

data class CityRef(
    var istat : String,
    var city: String,
    var province: String,
    var province_short: String,
    var iat: Address
)