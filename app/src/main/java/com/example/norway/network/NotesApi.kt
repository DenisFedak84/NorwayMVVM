package com.example.norway.network

import com.example.norway.data.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NotesApi {
    @GET("/public/v1/users")
    suspend fun getUsers(): UserResponse

    @GET("/public/v1/users")
    suspend fun getPaginationUsers(@Query("page") pageNumber: Int): UserResponse
}
