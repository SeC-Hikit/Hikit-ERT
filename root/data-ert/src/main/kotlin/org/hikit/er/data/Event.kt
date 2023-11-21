package org.hikit.er.data

import org.hikit.common.data.mapper.MultiPointCoords2D

data class Event(
        var _id: String,
        var remoteId: String,
        var title: String,
        var description: String,
        var coordinates: MultiPointCoords2D
) {
    companion object {
        val COLLECTION_NAME: String = "ert.Event"

        val ID: String = "_id"
        val REMOTE_ID: String = "remoteId"
        val TITLE: String = "title"
        val DESCRIPTION: String = "description"
        val POINTS: String = "points"
    }
}