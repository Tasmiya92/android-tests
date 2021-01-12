package com.tasmiya.peopleinteractivedemoapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tasmiya.peopleinteractivedemoapp.models.UserModel
import com.tasmiya.peopleinteractivedemoapp.util.Constants.Companion.USER_TABLE

@Entity(tableName = USER_TABLE )
class UserEntity(
    var userModel : UserModel,
    var acceptOrReject : String?
) {
    @PrimaryKey(autoGenerate = true)
   var  id : Int = 0

}