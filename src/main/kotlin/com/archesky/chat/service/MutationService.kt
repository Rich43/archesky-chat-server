package com.archesky.chat.service

import com.archesky.chat.dto.Message
import com.archesky.chat.repository.ChatRepository
import org.springframework.stereotype.Service

@Service
class MutationService(private val repository: ChatRepository,
                      private val messageQueueService: MessageQueueService) {
    fun createMessage(chat: String): Message {
        val message = repository.createMessage(chat)
        messageQueueService.push(message)
        return message
    }
}
