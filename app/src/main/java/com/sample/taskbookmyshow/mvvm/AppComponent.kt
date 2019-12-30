package com.sample.taskbookmyshow.mvvm


import android.app.Application
import com.sample.taskbookmyshow.MovieListScreen
import com.sample.taskbookmyshow.base.BaseServiceModule
import dagger.Component

import javax.inject.Singleton

/**
 * Created by AKASH on 29/12/19.
 */
@Component(modules = [AppModule::class, BaseServiceModule::class])
@Singleton
interface AppComponent {
    fun doInjection(app: Application)
    fun doInjection(movieListScreen: MovieListScreen)
}
