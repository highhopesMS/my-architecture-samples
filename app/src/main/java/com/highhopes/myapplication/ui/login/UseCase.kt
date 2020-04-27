package com.highhopes.myapplication.ui.login

import com.highhopes.myapplication.data.model.User
import com.highhopes.myapplication.data.model.Result
import kotlinx.coroutines.flow.Flow

interface UseCase {
    suspend fun getUsers(): Flow<Result<User>>
}
