package com.highhopes.myapplication.di.modules

import com.highhopes.myapplication.data.repository.LocalUserDataSource
import com.highhopes.myapplication.data.repository.LoginRepository
import com.highhopes.myapplication.data.db.LocalUserDataSourceImp
import com.highhopes.myapplication.data.api.UserApi
import com.highhopes.myapplication.data.db.MyDatabase
import com.highhopes.myapplication.ui.login.UseCase
import com.highhopes.myapplication.ui.login.UseCaseImp
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
        return LocalUserDataSourceImp(
            myDatabase
        )
    }

    @Provides
    fun providesLoginRepository(loginDataSource: LocalUserDataSource, userApi: UserApi): LoginRepository {
        return LoginRepository(
            loginDataSource,
            userApi
        )
    }

    @Provides
    fun providesUseCase(loginRepository : LoginRepository): UseCase {
        return UseCaseImp(loginRepository)
    }

    @Provides
    fun provideAccountApi(@Named("default retrofit") retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}
