package com.tasmiya.peopleinteractivedemoapp

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasmiya.peopleinteractivedemoapp.models.UserModel

class UserTypeConverter {

    var gson = Gson()
    @TypeConverter
    fun userToString(user : UserModel):String{
        return  gson.toJson(user)

    }

    @TypeConverter
    fun StringToUser(data : String):UserModel{
        val listType = object : TypeToken<UserModel>(){}.type
        return gson.fromJson(data, listType)
    }
}