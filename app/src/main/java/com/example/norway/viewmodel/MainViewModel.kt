package com.example.norway.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.norway.data.DataModel
import com.example.norway.data.UserAdapterModel
import com.example.norway.lifecycle.ConsumableEvent
import com.example.norway.mapper.UserMapper
import com.example.norway.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository,
    private val userMapper: UserMapper
) : ViewModel() {

    val eventsLiveData = MutableLiveData<ConsumableEvent<Event>>()

    fun getNotes() {
        eventsLiveData.value = ConsumableEvent(Event.LoadingState(true))

        viewModelScope.launch {
            repository.getUsers().flowOn(Dispatchers.IO)
                .catch { error ->
                    eventsLiveData.value = ConsumableEvent(Event.ShowError(error.message ?: ""))
                    eventsLiveData.value = ConsumableEvent(Event.LoadingState(false))
                }.map {
                    userMapper.transformToAdapterData(it)
                }.collect {
                    eventsLiveData.value = ConsumableEvent(Event.ShowNotes(it))
                    eventsLiveData.value = ConsumableEvent(Event.LoadingState(false))
                }
        }
    }

    sealed class Event {
        class ShowNotes(val notes: List<UserAdapterModel>) : Event()
        class ShowError(val error: String) : Event()
        class LoadingState(val isLoading: Boolean) : Event()
    }
}