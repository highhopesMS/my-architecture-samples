package com.highhopes.myapplication.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.highhopes.myapplication.R
import com.highhopes.myapplication.data.Result
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        login.setOnClickListener {
            viewModel.loginTemp()
        }

        viewModel.loginResult.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    loading.visibility = View.VISIBLE
                    Timber.d("loading")
                }
                is Result.Success -> {
                    loading.visibility = View.GONE
                    username.setText(it.data.accountName)
                    Timber.d("success")
                }
                is Result.Error -> {
                    loading.visibility = View.GONE
                    Timber.d("error")
                }
            }
        })

    }
}
