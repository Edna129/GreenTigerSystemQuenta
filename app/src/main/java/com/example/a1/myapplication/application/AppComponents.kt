package com.example.a1.myapplication.application

import com.example.a1.myapplication.application.modules.ApplicationModule
import com.example.a1.myapplication.presentation.LoginActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Author 1
 * Since 20.01.2018.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface AppComponents {
    fun inject(loginActivity: LoginActivity)

}