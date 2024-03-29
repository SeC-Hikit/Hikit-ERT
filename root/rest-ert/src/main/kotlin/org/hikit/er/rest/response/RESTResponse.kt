package org.hikit.er.rest.response

abstract class RESTResponse constructor(
    open val currentPage: Long,
    open val totalPages: Long,
    open val size: Long,
    open val totalCount: Long)