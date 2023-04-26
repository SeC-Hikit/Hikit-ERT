package org.hikit.er.job

import org.apache.logging.log4j.LogManager
import org.hikit.er.data.processor.LocalityProcessor
import org.hikit.er.manager.LocalityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class LocalitiesFetchJob @Autowired constructor(
    private val localityProcessor: LocalityProcessor,
    private val localityManager: LocalityManager
) {
    private val logger = LogManager.getLogger(LocalitiesFetchJob::class.java)

    @Scheduled(initialDelay = 3000, fixedRate = 600000, timeUnit = TimeUnit.MILLISECONDS)
    fun fetch() {
        logger.info("Going to fetch LOCALITY data from ERT API")
        var pageToProcess = 0
        do {
            val processBatch = localityProcessor.processBatch(pageToProcess)
            localityManager.upsertOnRemoteId(processBatch.data)
            val isThereMoreToFetch = processBatch.page < processBatch.of
            pageToProcess += 1;
            Thread.sleep(1000)
        } while (isThereMoreToFetch)

        logger.info("Done fetching LOCALITY data from ERT API")
    }

}