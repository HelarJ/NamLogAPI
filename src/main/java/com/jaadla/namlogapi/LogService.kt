package com.jaadla.namlogapi

import com.jaadla.namlogapi.log.MessageRepository
import org.springframework.stereotype.Service

@Service
class LogService(
        private val messageRepository: MessageRepository
) {
    fun getMessages(username: String, startIndex: Int, size: Int): LogController.Page {
        var index = startIndex
        var totalMessages = -1
        if (index <= 0) {
            //Since the order is descending it would be impossible to know the last id when the first request is made.
            index = messageRepository.maxId() + 10
            //message count is only included in the first request as it's expensive.
            //for the "all" request it is sufficient to just use the max id for an approximate value.
            totalMessages = if (username.equals("all", ignoreCase = true)) index else messageRepository.getMessageCountUser(username)
        }
        val messages = if (username.equals("all", ignoreCase = true)) messageRepository.findAllMessages(index, size) else messageRepository.findByUsername(username, index, size)
        val finalPage = messages.size < size
        val lastId = if (messages.isNotEmpty()) messages[messages.size - 1].id!! else 0
        return LogController.Page(messages, lastId, finalPage, totalMessages)
    }
}
