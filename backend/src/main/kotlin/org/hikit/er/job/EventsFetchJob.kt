package org.hikit.er.job

import org.apache.logging.log4j.LogManager
import org.hikit.er.data.processor.EventProcessor
import org.hikit.er.manager.EventManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class EventsFetchJob @Autowired constructor(
        private val eventProcessor: EventProcessor,
        private val eventManager: EventManager,
) {
    private val logger = LogManager.getLogger(EventsFetchJob::class.java)

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
    fun fetch() {
        logger.info("Going to fetch EVENT data from ERT API")
        var pageToProcess = 0
        do {
            val processBatch = eventProcessor.processBatch(pageToProcess)
            eventManager.upsertOnRemoteId(processBatch.data)
            val isThereMoreToFetch = processBatch.page < processBatch.of
            pageToProcess += 1
            Thread.sleep(1000)
        } while (isThereMoreToFetch)
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
    fun cleanOldEntries() {
        logger.info("Going to clean outdated EVENTs data from ERT API")
        eventManager.deleteOutdatedEntries()
    }

}
