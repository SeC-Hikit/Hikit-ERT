package org.hikit.er.job

import okhttp3.internal.wait
import org.apache.logging.log4j.LogManager
import org.hikit.er.data.dao.LocalityDao
import org.hikit.er.data.processor.LocalityProcessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class LocalitiesFetchJob @Autowired constructor(
    private val localityProcessor: LocalityProcessor,
    private val localityDao: LocalityDao
) {
    private val logger = LogManager.getLogger(LocalitiesFetchJob::class.java)

    //    @Scheduled(fixedRateString = "PT12H", initialDelay = 1000)
    @Scheduled(fixedRate = 10000, timeUnit = TimeUnit.MILLISECONDS)
    fun fetch() {
        logger.info("Going to fetch LOCALITY data from ERT API")
        var pageToProcess = 0

        do {
            val processBatch = localityProcessor.processBatch(pageToProcess)

            val toContinue = processBatch.page < processBatch.of
            pageToProcess += 1;
            Thread.sleep(1000)
        } while (toContinue)

        logger.info("Done fetching LOCALITY data from ERT API")
    }

}