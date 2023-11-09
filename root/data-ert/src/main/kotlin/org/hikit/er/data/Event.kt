package org.hikit.er.data

import java.util.*
import org.hikit.common.data.mapper.MultiPointCoords2D

data class Event(
        var _id: String,
        var remoteId: String,
        var permalink: String,
        var title: String,
        var subtitle: String,
        var description: String,
        var coordinates: MultiPointCoords2D,
        var dateFrom: Date,
        var dateTo: Date
)