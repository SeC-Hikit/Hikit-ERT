package org.hikit.er.controller.response.internal

data class InternalResponse<T> (val data: List<T>,
                                val totalCount: Int)