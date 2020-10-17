package com.archesky.chat.resolvers

import com.archesky.chat.dto.Message
import com.archesky.chat.service.MutationService
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class Mutation(private val mutationService: MutationService): GraphQLMutationResolver {
    fun createMessage(chat: String, environment: DataFetchingEnvironment): Message {
        return mutationService.createMessage(chat)
    }

    fun updateMessage(id: String, chat: String, environment: DataFetchingEnvironment): Message? {
        return mutationService.updateMessage(id, chat)
    }

    fun deleteMessage(id: String, environment: DataFetchingEnvironment): Boolean {
        return mutationService.deleteMessage(id)
    }
}
