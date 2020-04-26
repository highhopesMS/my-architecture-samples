package com.highhopes.myapplication.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.highhopes.myapplication.data.LoginRepository
import com.highhopes.myapplication.data.Result
import com.highhopes.myapplication.data.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _loginResult = MutableLiveData<Result<User>>()
    val loginResult: LiveData<Result<User>> = _loginResult


    fun loginTemp() {
        viewModelScope.launch {
            try {
                _loginResult.postValue(Result.Loading)
                val result= loginRepository.loginTemp()
                Log.d("User", Gson().toJson(result))
                _loginResult.postValue(Result.Success(result))
            }catch (throwable : Throwable) {
                _loginResult.postValue(Result.Error(Exception(throwable)))
            }catch (exception: Exception){
                _loginResult.postValue(Result.Error(exception))
            }

        }
    }

}
