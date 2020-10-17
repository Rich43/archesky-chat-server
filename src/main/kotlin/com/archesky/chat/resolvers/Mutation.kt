package com.archesky.chat.resolvers

import com.archesky.chat.dto.Message
import com.archesky.chat.service.MutationService
import graphql.kickstart.tools.GraphQLMutationResolver
import graphql.schema.DataFetchingEnvironment
import org.springframework.stereotype.Component

@Component
class Mutation(private val mutationService: MutationService): GraphQLMutationResolver {
    fun createMessage(message: String, environment: DataFetchingEnvironment): Message {
        return mutationService.createMessage(message)
    }
}
