package com.highhopes.myapplication.data.model

import com.highhopes.myapplication.data.LocalUserDataSource
import com.highhopes.myapplication.di.di.MyDatabase
import timber.log.Timber
import javax.inject.Inject

class LocalUserDataSourceImp @Inject constructor(private val myDatabase: MyDatabase) :
    LocalUserDataSource {
    override suspend fun saveUser(user: User) {
        myDatabase.userDao().save(user)
        Timber.tag("TEST").d("save user")
    }

    override  fun getUser(): User? {
        return myDatabase.userDao().getUser()
    }

    override suspend fun deleteUser() {

    }
}