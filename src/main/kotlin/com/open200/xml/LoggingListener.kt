package com.open200.xml

import com.open200.xesar.connect.Topics
import com.open200.xesar.connect.XesarConnect
import com.open200.xesar.connect.filters.AllTopicsFilter
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class LoggingListener(
    private val xesar: XesarConnect,
) {
    private val log = LoggerFactory.getLogger(LoggingListener::class.java)

    @EventListener(ApplicationReadyEvent::class)
    suspend fun logTopics() {
        // only subscribe to all topics for testing purposes
        xesar.subscribeAsync(Topics(Topics.ALL_TOPICS)).await()

        xesar.on(AllTopicsFilter()) { log.info("Received message on {}", it.topic) }
    }
}
