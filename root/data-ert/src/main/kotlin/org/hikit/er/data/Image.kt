package org.hikit.er.data

data class Image(
    val url: String,
    val title: String,
    val name: String,
    val width: Int,
    val height: Int,
    val license: String,
    val licenseUrl: String
) {
    companion object {
        val URL: String = "url"
        val TITLE: String = "title"
        val NAME: String = "name"
        val WIDTH: String = "width"
        val HEIGHT: String = "height"
        val LICENSE: String = "license"
        val LICENSE_URL: String = "licenseUrl"
    }
}