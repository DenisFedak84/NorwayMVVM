package com.example.norway.repository

import com.example.norway.data.LoginRequest
import com.example.norway.data.LoginResponse
import com.example.norway.data.UserResponse
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun getUsers(): Flow<UserResponse>

    fun loginByObject(loginRequest: LoginRequest): Flow<LoginResponse>
}