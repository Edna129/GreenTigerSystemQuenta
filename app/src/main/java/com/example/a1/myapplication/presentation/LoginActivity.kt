package com.example.a1.myapplication.presentation

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.a1.myapplication.R
import com.example.a1.myapplication.application.BaseApplication
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by 1 on 11.04.2017.
 */


class LoginActivity: AppCompatActivity(){
    lateinit var meowlog: String
    lateinit var meowpass: String
    val func = {
        Log.i("h", "ello world")
        Unit
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        BaseApplication.appComponents.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etLogin.requestFocus()
        showKeyboard(etLogin, func)
        button.setOnClickListener {
            meowlog  = etLogin.text.toString()
            meowpass = etPass.text.toString()
        }
    }

    fun showKeyboard(view: View, p1: () -> Unit = {}){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        p1.invoke()
    }
}