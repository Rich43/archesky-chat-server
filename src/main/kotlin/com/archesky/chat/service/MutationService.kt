package com.archesky.chat.service

import com.archesky.chat.dto.Message
import com.archesky.chat.repository.ChatRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class MutationService(private val repository: ChatRepository,
                      private val messageQueueService: MessageQueueService) {
    @PreAuthorize("hasAuthority('archesky.create_chat')")
    fun createMessage(chat: String): Message {
        return repository.createChat(chat)
    }

    @PreAuthorize("hasAuthority('archesky.update_chat')")
    fun updateMessage(id: String, chat: String): Message? {
        val result = repository.updateChatById(id, chat)
        messageQueueService.push(result)
        return result
    }

    @PreAuthorize("hasAuthority('archesky.delete_chat')")
    fun deleteMessage(id: String): Boolean {
        repository.deleteChatById(id)
        return true
    }
}
