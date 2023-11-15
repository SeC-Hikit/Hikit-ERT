package org.hikit.er.data

data class Event(
        var _id: String,
        var remoteId: String,
        var title: String,
        var description: String,
) {
    companion object {
        val COLLECTION_NAME: String = "ert.Event"

        val ID: String = "_id"
        val REMOTE_ID: String = "remoteId"
        val TITLE: String = "title"
        val DESCRIPTION: String = "description"
    }
}