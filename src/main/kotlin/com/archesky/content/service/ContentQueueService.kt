package com.archesky.chat.service

import com.archesky.chat.dto.Chat
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.observables.ConnectableObservable
import org.reactivestreams.Publisher
import org.springframework.stereotype.Service

@Service
class ChatQueueService {
    private val publisher: Publisher<Chat>
    private var emitter: ObservableEmitter<Chat>? = null

    init {
        val observable: Observable<Chat> = Observable.create {
            emitter = it
        }
        val connectableObservable: ConnectableObservable<Chat> = observable.share().publish()
        connectableObservable.connect()
        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun push(chat: Chat) {
        emitter!!.onNext(chat)
    }

    fun getPublisher(): Publisher<Chat> {
        return publisher
    }
}
