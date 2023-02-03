package org.hikit.er.data.processor

import org.hikit.er.data.Locality

data class LocalityBatch(
    val page: Int,
    val of: Int,
    val data: List<Locality>
)
