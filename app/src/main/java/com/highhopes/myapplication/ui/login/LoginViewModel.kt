package com.highhopes.myapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.highhopes.myapplication.data.LoginRepository
import com.highhopes.myapplication.data.Result
import com.highhopes.myapplication.data.model.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _loginResult = MutableLiveData<Result<User>>()
    val loginResult: LiveData<Result<User>> = _loginResult
    private var getUsersJob: Job? = null

    fun login() {
        viewModelScope.launch {
            getUsersJob?.let {
                if (it.isActive) {
                    it.cancel()
                }
            }
            getUsersJob = viewModelScope.launch {
                loginRepository.login().collect { data ->
                    _loginResult.value = data
                }
            }
        }
    }

}
