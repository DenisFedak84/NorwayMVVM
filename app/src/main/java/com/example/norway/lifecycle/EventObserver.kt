package com.example.norway.lifecycle

import androidx.lifecycle.Observer

class EventObserver<T>(
    private val onEventUnconsumedContent: (T) -> Unit
) :
    Observer<ConsumableEvent<T>> {
    override fun onChanged(event: ConsumableEvent<T>) {
        event.consume(onEventUnconsumedContent)
    }
}
