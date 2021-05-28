package com.example.graphqlsample.demo.processor

import org.reactivestreams.Publisher
import org.springframework.stereotype.Component
import reactor.core.scheduler.Schedulers

import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.Many

@Component
class Processor<Olympic> {
    private val sinksOne: Many<Olympic> = Sinks.many().multicast().onBackpressureBuffer()

    fun publish(): Publisher<Olympic> {
        return sinksOne.asFlux().publishOn(Schedulers.single())
    }

    fun emit(book: Olympic) {
        sinksOne.tryEmitNext(book)
    }
}