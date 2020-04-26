package com.highhopes.myapplication.data.model

import com.highhopes.myapplication.data.LocalUserDataSource
import com.highhopes.myapplication.di.di.MyDatabase
import javax.inject.Inject

class LocalUserDataSourceImp @Inject constructor(private val myDatabase: MyDatabase) :
    LocalUserDataSource {
    override suspend fun saveUser(user: User) {
        myDatabase.userDao().save(user)
    }

    override suspend fun getUser() {

    }

    override suspend fun deleteUser() {

    }
}