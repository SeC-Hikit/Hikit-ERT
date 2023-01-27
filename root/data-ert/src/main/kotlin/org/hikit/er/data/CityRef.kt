package org.hikit.er.data

data class CityRef(
    var istat : String,
    var city: String,
    var province: String,
    var province_short: String,
    var iat: List<Iat>
) {
    companion object {
        val ISTAT = "istat"
        val CITY = "city"
        val PROVINCE = "province"
        val PROVINCE_SHORT = "provinceShort"
        val IAT = "iat"
    }
}