package com.tasmiya.peopleinteractivedemoapp.network

import com.tasmiya.peopleinteractivedemoapp.models.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface UsersApi {
    @GET("/api/?results=10")
    suspend fun getUsers():Response<UserModel>
}