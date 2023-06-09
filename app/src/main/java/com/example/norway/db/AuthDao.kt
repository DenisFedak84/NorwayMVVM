package com.example.norway.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface AuthDao {

    @Upsert
    suspend fun upsertAuth (authUser: AuthUser)

    @Delete
    suspend fun deleteAuth (authUser: AuthUser)

    @Query("SELECT * FROM authUser")
    fun getAll(): List<AuthUser>
}