package com.example.a1.myapplication.presentation.selectQuentaFragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.a1.myapplication.R
import com.example.a1.myapplication.domain.models.view.ViewModelQuenta
import com.example.a1.myapplication.presentation.OnItemClick
import com.example.a1.myapplication.presentation.QuentaListAdapter
import com.pawegio.kandroid.runDelayedOnUiThread
import kotlinx.android.synthetic.main.fragment_select_quenta.*

/**
 * Author 1
 * Since 21.03.2018.
 */
interface SelectQuentaView: MvpView{
    fun showLoading()
    fun hideLoading()
    fun setQuentaList(quentaList: ArrayList<ViewModelQuenta>)
    fun navigateToQuentaCard(quenta: ViewModelQuenta)

}

class SelectQuentaFragment: MvpFragment(), SelectQuentaView {

    @InjectPresenter
    lateinit var presenter: SelectQuentaPresenter

    val adapter: QuentaListAdapter = QuentaListAdapter(object: OnItemClick{
        override fun onClick(item: ViewModelQuenta) {
            presenter.onQuentaSelected(item)
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View { //создани view (указывается xml)
        return inflater.inflate(R.layout.fragment_select_quenta, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) { //вызывается после того, как view будет создано
        super.onViewCreated(view, savedInstanceState)
        fbAdd.setOnClickListener{
            presenter.onAddClick()
        }
        rvQuenta.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        rvQuenta.adapter = adapter
        presenter.loadQuentaList()
    }

    override fun showLoading() {
        pbQuentaListLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbQuentaListLoading.visibility = View.INVISIBLE
    }

    override fun setQuentaList(quentaList: ArrayList<ViewModelQuenta>) {
        adapter.setData(quentaList)
    }

    override fun navigateToQuentaCard(quenta: ViewModelQuenta) {
        Snackbar.make(QuentaListRootView, "${quenta.name} Quenta selected!", Snackbar.LENGTH_SHORT).show()
    }

}

@InjectViewState
class SelectQuentaPresenter: MvpPresenter<SelectQuentaView>(){
    fun onQuentaSelected(item: ViewModelQuenta) {
        viewState.navigateToQuentaCard(item)
    }

    fun onAddClick() {

    }

    fun loadQuentaList() {
        viewState.showLoading()
        runDelayedOnUiThread(500L, {
            viewState.setQuentaList(arrayListOf(
                    ViewModelQuenta("Джон","Земля","https://vignette.wikia.nocookie.net/mspaintadventures/images/c/c8/John_Egbert_Wise_Guy.png/revision/latest?cb=20111213135927&path-prefix=ru"),
                    ViewModelQuenta("КАРКОТ","Альтерния","https://vignette.wikia.nocookie.net/mspaintadventures/images/1/17/Karkat_Vantas.png/revision/latest?cb=20120208100409&path-prefix=ru"),
                    ViewModelQuenta("8риска","Альтерния",""),
                    ViewModelQuenta("Дейв","Земля","")
            ))
            viewState.hideLoading()
        })

    }

}