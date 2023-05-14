package com.example.norway.repository

import com.example.norway.network.NotesApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: NotesApi) : IUserRepository {

    override fun getUsers() = flow {
        emit(api.getUsers())
    }
}