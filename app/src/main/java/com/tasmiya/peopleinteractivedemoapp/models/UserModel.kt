package com.tasmiya.peopleinteractivedemoapp.models


import com.google.gson.annotations.SerializedName
import com.tasmiya.peopleinteractivedemoapp.models.Info
import com.tasmiya.peopleinteractivedemoapp.models.Result

data class UserModel(
    @SerializedName("results")
    val results: List<Result>
)