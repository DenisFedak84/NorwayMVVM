package com.example.norway.repository

import android.content.Context
import com.example.norway.data.LoginRequest
import com.example.norway.data.LoginResponse
import com.example.norway.network.NotesApi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class UserRepository @Inject constructor(private val api: NotesApi, @ApplicationContext applicationContext: Context) : IUserRepository {

    override fun getUsers() = flow {
        emit(api.getUsers())
    }

    override fun loginByObject(loginRequest: LoginRequest): Flow<LoginResponse>  = flow {
        emit(LoginResponse("1234", "4321"))
    }
}