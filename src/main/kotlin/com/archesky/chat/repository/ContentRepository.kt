package com.archesky.chat.repository

import com.arangodb.springframework.annotation.Query
import com.arangodb.springframework.repository.ArangoRepository;
import com.archesky.chat.dto.MESSAGE
import com.archesky.chat.dto.Message
import org.springframework.data.repository.query.Param

interface ChatRepository : ArangoRepository<Message, String> {
    @Query(value = "INSERT { chat: @chat } INTO $MESSAGE RETURN NEW")
    fun createChat(@Param("chat") chat: String): Message

    @Query(value =
            "FOR c IN $MESSAGE FILTER c._key == @key " +
            "UPDATE { _key: c._key, chat: @chat } IN $MESSAGE RETURN NEW"
    )
    fun updateChatById(@Param("key") id: String, @Param("chat") chat: String): Message

    fun deleteChatById(id: String)
}
