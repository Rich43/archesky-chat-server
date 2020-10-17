package com.archesky.chat.resolvers

import com.archesky.chat.dto.Message
import com.archesky.chat.service.MutationService
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class Mutation(private val mutationService: MutationService): GraphQLMutationResolver {
    fun createChat(chat: String, environment: DataFetchingEnvironment): Message {
        return mutationService.createChat(chat)
    }

    fun updateChat(id: String, chat: String, environment: DataFetchingEnvironment): Message? {
        return mutationService.updateChat(id, chat)
    }

    fun deleteChat(id: String, environment: DataFetchingEnvironment): Boolean {
        return mutationService.deleteChat(id)
    }
}
