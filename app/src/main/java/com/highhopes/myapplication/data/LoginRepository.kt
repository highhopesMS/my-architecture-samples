package com.highhopes.myapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import com.highhopes.myapplication.data.model.User
import com.highhopes.myapplication.di.di.UserApi
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository @Inject constructor(
    val dataSource: LocalUserDataSource,
    val userApi: UserApi
) {

    suspend fun loginTemp(): User {
        //delay request
        //return dataSource.getUser()
//        delay(3_000)
        val result = userApi.loadUser()
//        if (result is User) {
//            dataSource.saveUser(result)
//        }
        return result
    }
}
