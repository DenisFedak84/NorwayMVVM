package com.example.norway.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.norway.data.DataModel
import com.example.norway.lifecycle.ConsumableEvent
import com.example.norway.network.ApiStatus
import com.example.norway.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(private val repository: MainRepository) : ViewModel() {

    val eventsLiveData = MutableLiveData<ConsumableEvent<Event>>()

    fun getNotes( ){
        eventsLiveData.value = ConsumableEvent(Event.LoadingState(true))
        viewModelScope.launch {
            val response = repository.getDataFromREST()

           when(response.status){
               ApiStatus.SUCCESS -> {
                   val notes = response.data as List<DataModel>
                   eventsLiveData.value = ConsumableEvent(Event.ShowNotes(notes))
                   eventsLiveData.value = ConsumableEvent(Event.LoadingState(false))
               }
               ApiStatus.ERROR -> {
                   val error = response.message?: ""
                   eventsLiveData.value = ConsumableEvent(Event.ShowError(error))
                   eventsLiveData.value = ConsumableEvent(Event.LoadingState(false))
               }
           }
        }
    }

    sealed class Event {
        class ShowNotes(val notes: List<DataModel>) : Event()
        class ShowError (val error: String) : Event()
        class LoadingState (val isLoading: Boolean) : Event()
    }
}