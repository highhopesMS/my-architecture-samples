package com.highhopes.myapplication.di.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.highhopes.myapplication.di.di.utils.MyViewModelFactory
import com.highhopes.myapplication.di.di.utils.ViewModelKey
import com.highhopes.myapplication.ui.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Module used to define the connection between the framework's [ViewModelProvider.Factory] and
 * our own implementation: [MyViewModelFactory].
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindHomeViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory
}
