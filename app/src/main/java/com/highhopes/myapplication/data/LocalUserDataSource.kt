package com.highhopes.myapplication.data

import androidx.lifecycle.LiveData
import com.highhopes.myapplication.data.model.User

/**
 * Class that handles user data with room dbase
 */
interface LocalUserDataSource {
  suspend fun saveUser(user: User)
  suspend fun getUser(): LiveData<User>
  suspend fun deleteUser()
}

