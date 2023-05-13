package com.example.norway.repository
import com.example.norway.network.ApiResult
import com.example.norway.network.NotesApi
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: NotesApi) {

    suspend fun getDataFromREST(): ApiResult<Any> {
        val response = api.getUsers()
        delay(2000L)

        return if (response.isSuccessful && response.body()!=null) {
            ApiResult.Success(response.body()!!.data)
        } else {
            ApiResult.Error(response.errorBody()?.string() ?: "")
        }
    }
}
