package com.example.a1.myapplication.presentation.login

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.a1.myapplication.R
import com.example.a1.myapplication.application.utils.navigateToFragment
import com.example.a1.myapplication.presentation.selectQuentaFragment.SelectQuentaFragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.layout_buttons_quenta.*

/**
 * Author 1
 * Since 21.03.2018.
 */
//Список состояний view
@StateStrategyType(AddToEndSingleStrategy::class)
interface LoginView : MvpView {
    fun showError(text: String)
    fun navigateToSeletcQuenta()
}

// фрагмент расширяется интерфейсом LoginView. Нам необходимо написать реализацию всех состояний view
class LoginFragment : MvpFragment(), LoginView {

    // инджектим презентер во фрагмент
    @InjectPresenter
    lateinit var presenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_login, container, false) // преедаем фрагменту view из нашего xml
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonLogin.setOnClickListener {    // вызывем метод презентера при клике на кнопку
            presenter.onLoginClick(etLogin.text.toString(), etPass.text.toString())
        }
    }

    // реализация поведения в случае ошибки. принимает текст ошибки т.к. её возвращает сервер или менеджер запросов или что угодно
    override fun showError(text: String) {
        Snackbar.make(rootView, text, Snackbar.LENGTH_SHORT).show()
    }

    // реализация перехода на другой экран
    override fun navigateToSeletcQuenta() {
        navigateToFragment(SelectQuentaFragment())
    }
}

@InjectViewState // инджектим viewState
class LoginPresenter : MvpPresenter<LoginView>() { // presenter отвечает за бизнес логику (работа с сетью, БД, принимает решения о том что делать с view)
    fun onLoginClick(login: String, pass: String) { // вызываетсся из врагмента
        viewState.navigateToSeletcQuenta()  // "говорим" view перейти в определенное состояние
    }
}
