package com.example.norway.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.norway.data.DataModel
import com.example.norway.lifecycle.ConsumableEvent
import com.example.norway.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(private val repository: MainRepository) : ViewModel() {

    val eventsLiveData = MutableLiveData<ConsumableEvent<Event>>()

    fun getNotes( ){
        viewModelScope.launch {
            val response = repository.getDataFromREST()
            eventsLiveData.value = ConsumableEvent(Event.ShowNotes(response))
        }
    }

    sealed class Event {
        class ShowNotes(val notes: List<DataModel>) : Event()
    }
}