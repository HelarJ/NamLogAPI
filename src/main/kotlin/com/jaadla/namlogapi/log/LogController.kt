package com.jaadla.namlogapi.log

import org.apache.commons.logging.LogFactory
import org.springframework.http.CacheControl
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping(path = ["/api/log"], produces = [MediaType.APPLICATION_JSON_VALUE])
class LogController(private val logService: LogService) {

    companion object {
        private const val MAX_PAGE_SIZE = 100000
        private val log = LogFactory.getLog(LogController.javaClass)
    }

    @GetMapping(value = ["/{username}"])
    @ResponseBody
    fun getLog(
            @PathVariable username: String,
            @RequestParam(defaultValue = "0") startIndex: Int,
            @RequestParam(defaultValue = "100") size: Int
    ): ResponseEntity<Page> {
        var amount = size

        if (amount > MAX_PAGE_SIZE) {
            amount = MAX_PAGE_SIZE
        }
        if (amount < 0) {
            amount = 0
        }

        val start = System.nanoTime();

        val messages = logService.getMessages(username, startIndex, amount)
        log.info("Logs for $username, startID $startIndex, count ${messages.messages.size}, DB took ${TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start)} ms.")

        return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES).cachePublic())
            .body(messages)
    }

    data class Page(
            var messages: List<MessageEntity>,
            var lastId: Int,
            var finalPage: Boolean,
            var totalMessages: Int,
    )
}
