package com.highhopes.myapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.highhopes.myapplication.data.LoginRepository
import com.highhopes.myapplication.data.Result
import com.highhopes.myapplication.data.model.User
import com.highhopes.myapplication.di.di.utils.cancelIfActive
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _loginResult = MutableLiveData<Result<User>>()
    val loginResult: LiveData<Result<User>> = _loginResult
    private var getUsersJob: Job? = null

    fun login() {
        viewModelScope.launch {
            getUsersJob?.cancelIfActive()

            getUsersJob = viewModelScope.launch {
                loginRepository.login()
                    .onStart {
                        _loginResult.postValue(Result.Loading)
                        Timber.tag("TEST").d("loading")
                    }
                    .catch {
                        _loginResult.value = Result.Error(Exception(it))
                        Timber.tag("TEST").d("error")
                    }
                    .collect { data ->
                        _loginResult.value = data
                    }
            }
        }
    }

}
