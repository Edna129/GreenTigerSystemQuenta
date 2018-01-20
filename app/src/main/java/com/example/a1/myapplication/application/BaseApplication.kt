package com.example.a1.myapplication.application

import android.app.Application
import android.content.Context
import com.example.a1.myapplication.application.modules.ApplicationModule

/**
 * Author 1
 * Since 20.01.2018.
 */
class BaseApplication: Application() {
    companion object {
        lateinit var appContext: Context

        @JvmStatic lateinit var appComponents: AppComponents
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        appComponents = DaggerAppComponents.builder()
                .applicationModule(ApplicationModule(this))
                .build()

    }
}