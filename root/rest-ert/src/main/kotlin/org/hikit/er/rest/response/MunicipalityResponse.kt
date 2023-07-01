package org.hikit.er.rest.response

import org.hikit.er.rest.MunicipalityDto

data class MunicipalityResponse (
    val status: Status,
    val messages: Set<String>,
    val content: List<MunicipalityDto>,
)