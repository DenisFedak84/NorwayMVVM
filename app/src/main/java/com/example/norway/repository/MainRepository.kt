package com.example.norway.repository
import com.example.norway.data.DataModel
import com.example.norway.data.UserResponse
import com.example.norway.network.NotesApi
import javax.inject.Inject


class MainRepository @Inject constructor(private val api: NotesApi) {

    suspend fun getDataFromREST(): List<DataModel> {

        var rest: UserResponse? = null

        try {
            rest = api.getUsers()
        } catch (e: Exception) {
            println("Error")
        }

        return if (rest?.data != null) {
            rest.data
        } else {
            emptyList()
        }
    }
}