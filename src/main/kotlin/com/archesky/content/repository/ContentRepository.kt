package com.archesky.chat.repository

import com.arangodb.springframework.annotation.Query
import com.arangodb.springframework.repository.ArangoRepository;
import com.archesky.chat.dto.CONTENT
import com.archesky.chat.dto.Chat
import org.springframework.data.repository.query.Param

interface ChatRepository : ArangoRepository<Chat, String> {
    @Query(value = "INSERT { chat: @chat } INTO $CONTENT RETURN NEW")
    fun createChat(@Param("chat") chat: String): Chat

    @Query(value =
            "FOR c IN $CONTENT FILTER c._key == @key " +
            "UPDATE { _key: c._key, chat: @chat } IN $CONTENT RETURN NEW"
    )
    fun updateChatById(@Param("key") id: String, @Param("chat") chat: String): Chat

    fun deleteChatById(id: String)
}
