package com.highhopes.myapplication.di.di

import com.highhopes.myapplication.data.LocalUserDataSource
import com.highhopes.myapplication.data.LoginRepository
import com.highhopes.myapplication.data.model.LocalUserDataSourceImp
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class LoginModule {
    @Singleton
    @Provides
     fun providesLoginDataSource(myDatabase : MyDatabase): LocalUserDataSource {
        return LocalUserDataSourceImp(myDatabase)
    }

    @Provides
    fun providesLoginRepository(loginDataSource: LocalUserDataSource, userApi: UserApi): LoginRepository {
        return LoginRepository(loginDataSource, userApi)
    }

    @Provides
    fun provideAccountApi(@Named("default retrofit") retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}
