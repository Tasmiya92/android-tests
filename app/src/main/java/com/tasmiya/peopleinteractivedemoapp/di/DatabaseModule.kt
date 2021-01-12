package com.tasmiya.peopleinteractivedemoapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.Builder
import com.tasmiya.peopleinteractivedemoapp.UserDatabase
import com.tasmiya.peopleinteractivedemoapp.util.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
             .build()

    @Singleton
    @Provides
    fun provideDao(database: UserDatabase) = database.userDao()
}