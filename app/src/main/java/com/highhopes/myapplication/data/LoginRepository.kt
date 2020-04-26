package com.highhopes.myapplication.data

import com.highhopes.myapplication.data.model.User
import com.highhopes.myapplication.di.di.UserApi
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository @Inject constructor(val dataSource: LocalUserDataSource, val userApi: UserApi) {

  suspend fun loginTemp() : User {
         return userApi.loadUser()
  }
}
