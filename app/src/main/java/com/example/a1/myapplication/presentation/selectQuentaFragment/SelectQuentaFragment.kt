package com.example.a1.myapplication.presentation.selectQuentaFragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter

/**
 * Author 1
 * Since 21.03.2018.
 */
interface SelectQuentaView: MvpView

class SelectQuentaFragment: MvpFragment(), SelectQuentaView {
    @InjectPresenter
    lateinit var presenter: SelectQuentaPresenter
}

@InjectViewState
class SelectQuentaPresenter: MvpPresenter<SelectQuentaView>(){

}