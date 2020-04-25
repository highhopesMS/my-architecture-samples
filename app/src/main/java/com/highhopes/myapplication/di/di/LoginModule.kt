package com.highhopes.myapplication.di.di

import com.highhopes.myapplication.data.LoginDataSource
import com.highhopes.myapplication.data.LoginRepository
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    open fun providesLoginDataSource(): LoginDataSource {
        return LoginDataSource()
    }

    @Provides
    open fun providesLoginRepository(loginDataSource: LoginDataSource): LoginRepository {
        return LoginRepository(loginDataSource)
    }
}
