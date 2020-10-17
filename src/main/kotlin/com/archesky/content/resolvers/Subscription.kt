package com.archesky.chat.resolvers

import com.archesky.chat.dto.Chat
import com.archesky.chat.service.ChatQueueService
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux.from

@Component
class Subscription(private val chatQueueService: ChatQueueService) : GraphQLSubscriptionResolver {
    fun updateChat(id: String): Publisher<Chat> {
        return from(chatQueueService.getPublisher())
                .filter { chat -> chat.id == id }
    }
}
