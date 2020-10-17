package com.archesky.chat.service

import com.archesky.chat.dto.Chat
import com.archesky.chat.repository.ChatRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
class MutationService(private val repository: ChatRepository,
                      private val chatQueueService: ChatQueueService) {
    @PreAuthorize("hasAuthority('archesky.create_chat')")
    fun createChat(chat: String): Chat {
        return repository.createChat(chat)
    }

    @PreAuthorize("hasAuthority('archesky.update_chat')")
    fun updateChat(id: String, chat: String): Chat? {
        val result = repository.updateChatById(id, chat)
        chatQueueService.push(result)
        return result
    }

    @PreAuthorize("hasAuthority('archesky.delete_chat')")
    fun deleteChat(id: String): Boolean {
        repository.deleteChatById(id)
        return true
    }
}
