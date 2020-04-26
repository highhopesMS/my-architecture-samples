package com.highhopes.myapplication.di.di

import android.content.Context
import androidx.room.Room
import com.highhopes.myapplication.MyApplication
import com.highhopes.myapplication.data.RetrofitBuilder

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

//import io.reactivex.disposables.CompositeDisposable

@Module
open class AppModule {

    @Provides
    fun provideContext(application: MyApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    @Named("default retrofit")
    fun provideRetrofit(): Retrofit {
        return RetrofitBuilder().retrofit
    }

    @Provides
    @Singleton
    fun provideDataBase(context: Context): MyDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MyDatabase::class.java, "MyDatabase.db"
        ).build()

    }



}