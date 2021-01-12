package com.tasmiya.peopleinteractivedemoapp
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val userDao : UserDao
){
    fun readUsers() : Flow<List<UserEntity>>{
        return userDao.readUser()
    }

    suspend fun inserUser(userEntity: UserEntity){
        userDao.insertUser(userEntity)
    }

    suspend fun update(user : UserEntity){
        userDao.updateUser(user)
    }
}
