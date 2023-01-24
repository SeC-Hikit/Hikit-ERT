package org.hikit.er.data

data class Image(
    val url: String,
    val thumb: String,
    val title: String,
    val name: String,
    val width: Int,
    val height: Int,
    val license: String,
    val license_url: String
)