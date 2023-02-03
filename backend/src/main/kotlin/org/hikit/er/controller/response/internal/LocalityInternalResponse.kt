package org.hikit.er.controller.response.internal

import org.hikit.er.rest.LocalityDto

data class LocalityInternalResponse (val data: List<LocalityDto>,
                                     val totalCount: Long)