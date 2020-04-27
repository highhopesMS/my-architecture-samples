package com.highhopes.myapplication.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.highhopes.myapplication.data.model.Result
import com.highhopes.myapplication.data.model.User
import com.highhopes.myapplication.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    lateinit var viewmodel: LoginViewModel

    lateinit var result: Flow<Result<User>>
    lateinit var error: Flow<Result<User>>

    lateinit var userResult: Result<User>
    lateinit var errorResult: Result<User>

    @Mock
    lateinit var user: User

    @Mock
    lateinit var useCase: UseCase

    @Before
    fun setup() {
        viewmodel = LoginViewModel(useCase)
        userResult = Result.Success(user)
        result = flow { emit(userResult) }

        errorResult = Result.Error(Exception(Throwable()))
        error = flow { emit(errorResult) }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loginOnSuccess() = coroutineScope.runBlockingTest {
        Mockito.`when`(useCase.getUsers()).then { result }
        viewmodel.login()

        Truth.assertThat(viewmodel.loginResult.value).isEqualTo(userResult)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loginOnFail() = coroutineScope.runBlockingTest {
        Mockito.`when`(useCase.getUsers()).then { error }
        viewmodel.login()
        Truth.assertThat(viewmodel.loginResult.value).isEqualTo(errorResult)
    }

}
