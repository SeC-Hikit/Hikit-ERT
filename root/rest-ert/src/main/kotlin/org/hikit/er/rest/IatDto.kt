package org.hikit.er.rest

import org.hikit.er.data.Coordinates

data class IatDto (
    var name: String,
    var address: String,
    var number: String,
    var coordinates: Coordinates,
    var contacts: List<ContactDto>
)
