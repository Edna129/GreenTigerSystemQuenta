package com.example.a1.myapplication.domain

import com.example.a1.myapplication.application.utils.FunctionalSubscriber
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

/**
 * Author 1
 * Since 20.01.2018.
 */
abstract class UseCase<in C, T> {

    private var mSubscription: Disposable = Disposables.empty()

    protected abstract fun buildObservable(criteria: C): Observable<T>

    fun execute(useCaseSubscriber: FunctionalSubscriber<T>, criteria: C) {
        mSubscription = buildObservable(criteria)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(useCaseSubscriber)
    }

    fun unsubscribe() {
        if (!mSubscription.isDisposed) {
            mSubscription.dispose()
        }
    }
}