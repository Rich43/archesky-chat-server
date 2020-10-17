package com.archesky.chat.repository

import com.arangodb.springframework.annotation.Query
import com.arangodb.springframework.repository.ArangoRepository;
import com.archesky.chat.dto.MESSAGE
import com.archesky.chat.dto.Message
import org.springframework.data.repository.query.Param

interface MessageRepository : ArangoRepository<Message, String> {
    @Query(value = "INSERT { message: @message, created: @created } INTO $MESSAGE RETURN NEW")
    fun createMessage(
            @Param("message") message: String,
            @Param("created") created: String
    ): Message
}
