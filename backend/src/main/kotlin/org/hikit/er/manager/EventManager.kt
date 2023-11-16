package org.hikit.er.manager

import org.hikit.er.data.Event
import org.hikit.er.data.dao.EventDao
import org.hikit.er.data.mapper.EventMapperDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EventManager @Autowired constructor(
        private val eventDao: EventDao,
        private val eventMapper: EventMapperDto
) {
    fun upsertOnRemoteId(events: List<Event>): List<Event> =
            eventDao.upsertOnRemoteId(events)
}