package com.tasmiya.peopleinteractivedemoapp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
@Database(entities = [UserEntity::class],
version = 2,
exportSchema = false)
@TypeConverters(UserTypeConverter::class)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}
