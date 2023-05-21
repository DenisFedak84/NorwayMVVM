package com.example.norway.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AuthUser(
    val accessToken: String,
    val refreshToken: String,
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0
)
