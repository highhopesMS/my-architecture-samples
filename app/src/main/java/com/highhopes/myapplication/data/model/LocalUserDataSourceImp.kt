package com.highhopes.myapplication.data.model

import androidx.lifecycle.LiveData
import com.highhopes.myapplication.data.LocalUserDataSource
import com.highhopes.myapplication.di.di.MyDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalUserDataSourceImp @Inject constructor(private val myDatabase: MyDatabase) :
    LocalUserDataSource {
    override suspend fun saveUser(user: User) {
        myDatabase.userDao().save(user)
    }

    override suspend fun getUser(): User? {
        return myDatabase.userDao().getUser()
    }

    override suspend fun deleteUser() {

    }
}