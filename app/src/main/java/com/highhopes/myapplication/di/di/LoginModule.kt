package com.highhopes.myapplication.di.di

import com.highhopes.myapplication.data.LoginDataSource
import com.highhopes.myapplication.data.LoginRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class LoginModule {
    @Provides
    open fun providesLoginDataSource(): LoginDataSource {
        return LoginDataSource()
    }

    @Provides
    open fun providesLoginRepository(loginDataSource: LoginDataSource,userApi: UserApi): LoginRepository {
        return LoginRepository(loginDataSource, userApi)
    }

    @Provides
    fun provideAccountApi(@Named("default retrofit") retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}
