package com.example.norway.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.norway.data.LoginRequest
import com.example.norway.data.LoginResponse
import com.example.norway.db.AuthUser
import com.example.norway.lifecycle.ConsumableEvent
import com.example.norway.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    val eventsLiveData = MutableLiveData<ConsumableEvent<Event>>()


    fun login(email: String, password: String) {

        viewModelScope.launch {
            repository.loginByObject(LoginRequest(email, password)).flowOn(Dispatchers.IO)
                .catch { error -> }
                .map {
                    it.accessToken
                }
                .collect {
                    eventsLiveData.value = ConsumableEvent(Event.ShowToken(it))
                }
        }
    }

    fun saveAuth(authUser: AuthUser) {
        viewModelScope.launch {
            repository.saveUserAuthToDB(authUser).flowOn(Dispatchers.IO).catch { e -> println(e) }
                .collect {
                    println("Saved: $it")
                }
        }
    }

    sealed class Event {
        class ShowToken(val token: String) : Event()
    }
}

