package com.highhopes.myapplication.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.google.common.truth.Truth
import com.highhopes.myapplication.data.api.UserApi
import com.highhopes.myapplication.data.model.Result
import com.highhopes.myapplication.data.model.User
import com.highhopes.myapplication.utils.MainCoroutineScopeRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combineLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    lateinit var loginRepository: LoginRepository

    @Mock
    lateinit var dataSource: LocalUserDataSource
    @Mock
    lateinit var user: User
    @Mock
    lateinit var userResult: Result<User>
    @Mock
    lateinit var userApi: UserApi

    @Before
    fun setUp() {
        loginRepository = LoginRepository(dataSource, userApi)
        userResult = Result.Success(user)
    }

    @Test
    fun loginSuccess() = coroutineScope.runBlockingTest {
        Mockito.`when`(userApi.loadUser()).thenReturn(user)
        Mockito.`when`(dataSource.getUser()).thenReturn(user)
        loginRepository.login().collect()

        launch(Dispatchers.Default) { loginRepository.login() }

        Truth.assertThat(loginRepository.result).isEqualTo(userResult)
    }

    @Test
    fun loginFail() = coroutineScope.runBlockingTest {
        Mockito.`when`(userApi.loadUser()).thenReturn(null)
        Mockito.`when`(dataSource.getUser()).thenReturn(null)
        loginRepository.login().collect()

        launch(Dispatchers.Default) { loginRepository.login() }

        Truth.assertThat(loginRepository.result).isEqualTo(Result.Loading)
    }
}