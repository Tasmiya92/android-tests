package com.tasmiya.peopleinteractivedemoapp

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
        remoteDataSource: RemoteDataSource,
localDataSource: LocalDataSource

) {
   // Data from remote
    val remote = remoteDataSource
    val local = localDataSource

}