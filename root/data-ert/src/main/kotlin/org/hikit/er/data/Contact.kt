package org.hikit.er.data

data class Contact(
    val label: String,
    val type: String,
    val value: String
) {
    companion object {
        val LABEL = "label"
        val TYPE = "type"
        val VALUE = "value"
    }
}
