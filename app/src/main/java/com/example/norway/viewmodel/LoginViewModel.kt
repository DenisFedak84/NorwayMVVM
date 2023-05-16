package com.example.norway.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.norway.lifecycle.ConsumableEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
    val eventsLiveData = MutableLiveData<ConsumableEvent<Event>>()




    sealed class Event {
        class ShowToken(val token: String) : Event()
    }
}

