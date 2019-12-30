package com.sample.taskbookmyshow

import android.app.Application
import android.content.Context
import com.sample.taskbookmyshow.mvvm.AppComponent
import com.sample.taskbookmyshow.mvvm.AppModule
import com.sample.taskbookmyshow.mvvm.DaggerAppComponent

/**
 * Created by AKASH on 29/12/19.
 */
class MyApplication : Application() {
//    val appComponent: AppComponent

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        component.doInjection(this);
        instance = this
    }

    companion object {
        var appContext: Context? = null
            private set
        @get:Synchronized
        var instance: MyApplication? = null
            private set
    }

}
