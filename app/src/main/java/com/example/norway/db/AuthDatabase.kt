package com.example.norway.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AuthUser :: class],
    version = 1
)
abstract class AuthDatabase : RoomDatabase() {
    abstract val dao: AuthDao
}