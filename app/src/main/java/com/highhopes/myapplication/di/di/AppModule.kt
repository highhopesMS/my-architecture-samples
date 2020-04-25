package com.highhopes.myapplication.di.di

import android.content.Context
import com.highhopes.myapplication.MyApplication

import dagger.Module
import dagger.Provides
//import io.reactivex.disposables.CompositeDisposable

@Module
open class AppModule {

    @Provides
    fun provideContext(application: MyApplication): Context {
        return application.applicationContext
    }

//    @Provides
//    fun provideCompositeSubscription(): CompositeDisposable {
//        return CompositeDisposable()
//    }


}