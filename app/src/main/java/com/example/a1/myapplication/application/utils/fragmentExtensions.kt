package com.example.a1.myapplication.application.utils

import android.app.Activity
import android.app.Fragment
import com.example.a1.myapplication.R

/**
 * Author 1
 * Since 21.03.2018.
 */

/**
 * Расширение для перехода на новый фрагмент
 *
 * Принимает:
 * [fragment] - новый фрагмент
 * [placeHolder] - ID framelayout'а в который будет загружен фрагмент
 * [animate] - использовать анимацию да/нет
 * [backStack] - добавить фрагмент в стек да/нет
 *
 * Возвращает: ничего
 *
 * */

fun Fragment.navigateToFragment(
        fragment: Fragment,
        placeHolder: Int = R.id.fragmentPlaceholder,
        animate: Boolean = true,
        backStack: Boolean = true) {
    val fragmentTransaction = this.fragmentManager.beginTransaction()
    val tag = fragment::class.java.simpleName
    if (animate) {
/*        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right,
                R.anim.slide_out_left, R.anim.slide_out_right)*/
    }
    if (backStack) {
        fragmentTransaction.addToBackStack(tag)
    }
    fragmentTransaction.add(placeHolder, fragment, tag)
    fragmentTransaction.commit()
}

fun Activity.navigateToFragment(
        fragment: Fragment,
        placeHolder: Int = R.id.fragmentPlaceholder,
        animate: Boolean = true,
        backStack: Boolean = true) {
    val fragmentTransaction = this.fragmentManager.beginTransaction()
    val tag = fragment::class.java.simpleName
    if (animate) {
/*        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_in_right,
                R.anim.slide_out_left, R.anim.slide_out_right)*/
    }
    if (backStack) {
        fragmentTransaction.addToBackStack(tag)
    }
    fragmentTransaction.add(placeHolder, fragment, tag)
    fragmentTransaction.commit()
}