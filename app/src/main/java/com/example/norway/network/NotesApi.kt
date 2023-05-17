package com.example.norway.network

import android.graphics.ColorSpace.Model
import com.example.norway.data.LoginRequest
import com.example.norway.data.LoginResponse
import com.example.norway.data.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface NotesApi {
    @GET("/public/v1/users")
    suspend fun getUsers(): UserResponse

    @FormUrlEncoded
    @POST("/api/login")
    fun loginByFields(@Field("email") email: String?, @Field("password") password: String?): Call<Model?>?

    @FormUrlEncoded
    @POST("/api/login")
    fun loginByObject(@Body loginRequest: LoginRequest): LoginResponse
}
