package com.example.norway.mapper

import com.example.norway.data.UserAdapterModel
import com.example.norway.data.UserResponse

class UserMapper {

    fun transformToAdapterData( response : UserResponse) : List<UserAdapterModel> {
        var list = mutableListOf<UserAdapterModel>()
        if (response.data.isNotEmpty()){
            list = response.data.map { UserAdapterModel(it.id, it.name)} as MutableList<UserAdapterModel>
        }
        return list
    }
}