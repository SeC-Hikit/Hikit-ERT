package org.hikit.er.data.processor

import org.hikit.er.data.imported.EventImport

data class EventBatch(
        val page: Int,
        val of: Int,
        val data: List<EventImport>
)
