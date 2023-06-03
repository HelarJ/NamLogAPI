package com.jaadla.namlogapi

import com.jaadla.namlogapi.log.MessageEntity
import org.apache.commons.logging.LogFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/log"], produces = [MediaType.APPLICATION_JSON_VALUE])
class LogController(private val logService: LogService) {

    companion object {
        private const val MAX_PAGE_SIZE = 20000
        private val log = LogFactory.getLog(LogController::class.java.name)
    }

    @GetMapping(value = ["/{username}"])
    @ResponseBody
    fun getLog(
            @PathVariable username: String,
            @RequestParam(defaultValue = "0") startIndex: Int,
            @RequestParam(defaultValue = "100") size: Int
    ): Page {
        var amount = size

        if (amount > MAX_PAGE_SIZE) {
            amount = MAX_PAGE_SIZE
        }
        if (amount < 0) {
            amount = 0
        }

        val messages = logService.getMessages(username, startIndex, amount)
        log.info("Logs for $username, startID $startIndex")
        return messages
    }

    data class Page(
            var messages: List<MessageEntity>,
            var lastId: Int,
            var finalPage: Boolean,
            var totalMessages: Int,
    )
}
