package com.highhopes.myapplication.ui.login

import android.os.Bundle
import android.view.View
import android.widget.*
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

        val username = findViewById<TextView>(R.id.username)
        val lastName = findViewById<TextView>(R.id.lastName)
        val getUser = findViewById<Button>(R.id.getUser)
        val loading = findViewById<ProgressBar>(R.id.loading)

        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)

        getUser.setOnClickListener {
            viewModel.login()
        }

        viewModel.loginResult.observe(this, Observer {
            when (it) {
                is Result.Loading -> {
                    loading.visibility = View.VISIBLE
                    Timber.d("loading")
                }
                is Result.Success -> {
                    loading.visibility = View.GONE
                    username.text = it.data.name
                    lastName.text = it.data.lastName
                    Timber.d("success")
                }
                is Result.Error -> {
                    loading.visibility = View.GONE
                    Timber.d("error")
                    Toast.makeText(this, it.exception.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}
