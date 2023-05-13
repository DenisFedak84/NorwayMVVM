package com.example.norway.network

import com.example.norway.data.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface NotesApi {
    @GET("/public/v1/users")
    suspend fun getUsers(): Response<UserResponse>
}
