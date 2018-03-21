package com.example.a1.myapplication.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.a1.myapplication.R
import com.example.a1.myapplication.application.utils.navigateToFragment
import com.example.a1.myapplication.presentation.login.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToFragment(fragment = LoginFragment(), backStack = false)
    }
}
