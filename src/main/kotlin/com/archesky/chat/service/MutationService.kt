package com.archesky.chat.service

import com.archesky.chat.dto.Message
import com.archesky.chat.repository.MessageRepository
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class MutationService(private val repository: MessageRepository,
                      private val messageQueueService: MessageQueueService) {
    fun createMessage(message: String): Message {
        val msg = repository.createMessage(
                message,
                SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date())
        )
        messageQueueService.push(msg)
        return msg
    }
}
