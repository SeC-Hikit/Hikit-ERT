package org.hikit.er.data

data class Iat(
    val name: String,
    val address: String,
    val number: String,
    val coordinates: Coordinates,
    val contacts: List<Contact>
)
