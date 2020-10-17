package com.archesky.chat.resolvers

import com.archesky.chat.dto.Message
import com.archesky.chat.service.MessageQueueService
import graphql.kickstart.tools.GraphQLSubscriptionResolver
import org.reactivestreams.Publisher
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux.from

@Component
class Subscription(private val messageQueueService: MessageQueueService) : GraphQLSubscriptionResolver {
    fun updateMessage(): Publisher<Message> {
        return from(messageQueueService.getPublisher())
    }
}
