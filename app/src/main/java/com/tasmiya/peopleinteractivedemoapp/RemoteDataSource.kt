package com.tasmiya.peopleinteractivedemoapp

import com.tasmiya.peopleinteractivedemoapp.models.UserModel
import com.tasmiya.peopleinteractivedemoapp.network.UsersApi
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val usersApi : UsersApi
) {
    suspend fun getUsers(): Response<UserModel>{
        return usersApi.getUsers()
    }
}