package com.sample.taskbookmyshow.mvvm

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

/**
 * Created by AKASH on 29/12/19.
 */
@Module
class AppModule(private val context: Context) {
    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return context
    }
}
