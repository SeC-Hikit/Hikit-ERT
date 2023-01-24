package org.hikit.er.data

data class Address(
    val name: String,
    val address: String,
    val number: String,
    val coordinates: Coordinates,
    val telephone: List<Contact>,
    val email: List<Contact>
)
