package org.hikit.er.rest

data class ImageDto (
    var url: String,
    var title: String,
    var name: String,
    var width: Int,
    var height: Int,
    var license: String,
    var licenseUrl: String
)