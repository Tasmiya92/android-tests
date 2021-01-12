package com.tasmiya.peopleinteractivedemoapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity)
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readUser() : Flow<List<UserEntity>>

    @Update
    suspend fun updateUser(users: UserEntity) : Int
}
