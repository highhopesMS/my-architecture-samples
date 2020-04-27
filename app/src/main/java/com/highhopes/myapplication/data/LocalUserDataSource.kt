package com.highhopes.myapplication.data

import androidx.lifecycle.LiveData
import com.highhopes.myapplication.data.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Class that handles user data with room dbase
 */
interface LocalUserDataSource {
  suspend fun saveUser(user: User)
  suspend fun getUser(): Flow<User>
  suspend fun deleteUser()
}

