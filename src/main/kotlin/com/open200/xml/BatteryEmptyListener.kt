package com.open200.xml

import com.open200.xesar.connect.Topics
import com.open200.xesar.connect.XesarConnect
import com.open200.xesar.connect.filters.TopicFilter
import com.open200.xesar.connect.messages.query.AccessProtocolEvent
import com.open200.xesar.connect.messages.query.EventType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class BatteryEmptyListener(
    private val xesar: XesarConnect,
) {
    private val log = LoggerFactory.getLogger(LoggingListener::class.java)
    private val scope = CoroutineScope(Job() + Dispatchers.IO)
    private val batteryEmpty =
        Topics.Event.accessProtocolEventTopic(EventType.BATTERY_EMPTY)

    @EventListener(ApplicationReadyEvent::class)
    suspend fun listenOnBatteryEmpty() {
        xesar.subscribeAsync(Topics(batteryEmpty)).await()

        xesar.on(TopicFilter(batteryEmpty)) {
            scope.launch {
                val event = AccessProtocolEvent.decode(it.message)
                log.warn(
                    "Battery warning received from xesar on installation point: {} ({})",
                    event.installationPointIdentifier,
                    event.installationPointName)
            }
        }
    }
}
