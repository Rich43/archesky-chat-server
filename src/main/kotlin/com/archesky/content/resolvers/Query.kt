package com.archesky.chat.resolvers

import com.archesky.chat.dto.Chat
import com.archesky.chat.repository.ChatRepository
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class Query(private val repository: ChatRepository): GraphQLQueryResolver {
    fun listChat(): MutableIterable<Chat> {
        return repository.findAll()
    }
}
