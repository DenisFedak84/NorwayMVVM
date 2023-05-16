package com.example.norway.network

import android.graphics.ColorSpace.Model
import com.example.norway.data.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface NotesApi {
    @GET("/public/v1/users")
    suspend fun getUsers(): UserResponse

    @FormUrlEncoded
    @POST("/api/login")
    fun login(@Field("email") name: String?, @Field("password") job: String?): Call<Model?>?
}
