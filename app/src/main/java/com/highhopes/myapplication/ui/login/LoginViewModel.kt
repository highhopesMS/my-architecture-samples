package com.highhopes.myapplication.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.highhopes.myapplication.R
import com.highhopes.myapplication.data.LoginRepository
import com.highhopes.myapplication.data.Result
import com.highhopes.myapplication.di.di.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _loginResult = MutableLiveData<Result<User>>()
    val loginResult: LiveData<Result<User>> = _loginResult

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    fun loginTemp() {
        viewModelScope.launch {
            try {
                _loginResult.postValue(Result.Loading)
                val result= loginRepository.loginTemp()
                Log.d("User", Gson().toJson(result))
                _loginResult.postValue(Result.Success(result))
            }catch (throwable : Throwable) {
                _loginResult.postValue(Result.Error(Exception(throwable)))
            }finally {

            }

        }
    }

}
