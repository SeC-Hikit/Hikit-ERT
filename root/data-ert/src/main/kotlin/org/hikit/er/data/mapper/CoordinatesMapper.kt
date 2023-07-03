package org.hikit.er.data.mapper

import org.hikit.er.data.Coordinates
import org.springframework.stereotype.Component

@Component
class CoordinatesMapper {
    fun map(doc: List<Double>) = Coordinates(
        latitude = doc[0],
        longitude = doc[1]
    )
}