package com.example.norway.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.norway.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(private val repository: MainRepository) : ViewModel() {

    fun getNotes( ){
        viewModelScope.launch {
            val response = repository.getDataFromREST()
        }
    }
}