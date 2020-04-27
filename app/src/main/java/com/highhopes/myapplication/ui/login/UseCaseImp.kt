package com.highhopes.myapplication.ui.login

import com.highhopes.myapplication.data.repository.LoginRepository
import com.highhopes.myapplication.data.model.User
import kotlinx.coroutines.flow.Flow
import com.highhopes.myapplication.data.model.Result
import javax.inject.Inject

class UseCaseImp @Inject constructor(private val loginRepository: LoginRepository): UseCase {
    override suspend fun getUsers(): Flow<Result<User>> {
        return loginRepository.login()
    }

}