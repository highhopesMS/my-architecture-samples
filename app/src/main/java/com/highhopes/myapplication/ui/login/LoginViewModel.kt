package com.highhopes.myapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.highhopes.myapplication.data.model.Result
import com.highhopes.myapplication.data.model.User
import com.highhopes.myapplication.extensions.cancelIfActive
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val useCase: UseCase) :
    ViewModel() {

    private val _loginResult = MutableLiveData<Result<User>>()
    val loginResult: LiveData<Result<User>> = _loginResult
    private var getUsersJob: Job? = null

    @ExperimentalCoroutinesApi
    fun login() {
        viewModelScope.launch {
            getUsersJob?.cancelIfActive()

            getUsersJob = viewModelScope.launch {
                useCase.getUsers()
                    .onStart {
                        _loginResult.postValue(Result.Loading)
                        Timber.tag("TEST").d("loading")
                    }
                    .catch {
                        _loginResult.postValue(Result.Error(Exception(it)))
                        Timber.tag("TEST").d("error")
                    }.collect { data ->
                        _loginResult.postValue(data)
                    }
            }
        }
    }

}
