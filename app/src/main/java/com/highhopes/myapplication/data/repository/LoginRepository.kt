package com.highhopes.myapplication.data.repository

import com.highhopes.myapplication.data.model.Result
import com.highhopes.myapplication.data.api.UserApi
import com.highhopes.myapplication.data.model.User
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository @Inject constructor(
    private val dataSource: LocalUserDataSource,
    private val userApi: UserApi
) {

    suspend fun login() = flow<Result<User>> {
        //delay(3_000)
        dataSource.getUser()?.let {
            emit(Result.Success(it))
            Timber.tag("TEST").d("from db $it")
        }

        userApi.loadUser().let {
            dataSource.saveUser(it)
            emit(Result.Success(it))
            Timber.tag("TEST").d("from network")
        }

    }
}