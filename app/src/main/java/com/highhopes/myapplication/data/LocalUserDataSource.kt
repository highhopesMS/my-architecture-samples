package com.highhopes.myapplication.data

import com.highhopes.myapplication.data.model.User

/**
 * Class that handles user data with room dbase
 */
interface LocalUserDataSource {
  suspend fun saveUser(user: User)
  suspend fun getUser()
  suspend fun deleteUser()
}

