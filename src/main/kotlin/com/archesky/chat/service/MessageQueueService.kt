package com.archesky.chat.service

import com.archesky.chat.dto.Message
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.observables.ConnectableObservable
import org.reactivestreams.Publisher
import org.springframework.stereotype.Service

@Service
class MessageQueueService {
    private val publisher: Publisher<Message>
    private var emitter: ObservableEmitter<Message>? = null

    init {
        val observable: Observable<Message> = Observable.create {
            emitter = it
        }
        val connectableObservable: ConnectableObservable<Message> = observable.share().publish()
        connectableObservable.connect()
        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER)
    }

    fun push(message: Message) {
        emitter!!.onNext(message)
    }

    fun getPublisher(): Publisher<Message> {
        return publisher
    }
}
