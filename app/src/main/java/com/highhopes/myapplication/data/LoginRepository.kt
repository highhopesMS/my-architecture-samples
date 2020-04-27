package com.highhopes.myapplication.data

import com.highhopes.myapplication.data.model.User
import com.highhopes.myapplication.di.di.UserApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository @Inject constructor(
    val dataSource: LocalUserDataSource,
    val userApi: UserApi
) {

    suspend fun login() = flow<Result<User>> {
//        dataSource.getUser().collect {
//            emit(Result.Success(it))
//            Timber.tag("TEST").d("from db")
//        }

        try {
            delay(5_000)
            userApi.loadUser().let {
             dataSource.saveUser(it)
             emit(Result.Success(it))
             Timber.tag("TEST").d("from network")
            }
        } catch (throwable: Throwable) {
            emit(Result.Error(Exception(throwable)))
        } catch (exception: Exception) {
            emit(Result.Error(exception))
        }

    }


}
