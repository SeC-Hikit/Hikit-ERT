package org.hikit.er.data

data class Iat(
    val name: String,
    val address: String,
    val number: String,
    val coordinates: List<Double>,
    val contacts: List<Contact>
) {
    companion object {
        val NAME = "name"
        val ADDRESS = "address"
        val NUMBER = "number"
        val COORDINATES = "coordinates"
        val CONTACTS = "contacts"
    }
}
