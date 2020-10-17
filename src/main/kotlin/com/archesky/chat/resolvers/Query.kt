package com.archesky.chat.resolvers

import com.archesky.chat.dto.Message
import com.archesky.chat.repository.MessageRepository
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class Query(private val repository: MessageRepository): GraphQLQueryResolver {
    fun listMessages(): MutableIterable<Message> {
        return repository.findAll()
    }
}
