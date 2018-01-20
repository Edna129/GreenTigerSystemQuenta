package com.example.a1.myapplication.application.utils

import android.util.Log
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.observers.DisposableObserver
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author Ilyas Shafigin
 * @since 03.02.17
 */
/**
 * @author Ilyas Shafigin
 * @since 16.01.17
 */

class FunctionalSubscriber<T> : DisposableObserver<T>() {
    private val onCompletedFunctions = ArrayList<() -> Unit>()
    private val onErrorFunctions = ArrayList<(throwable: Throwable) -> Unit>()
    private val onNextFunctions = ArrayList<(value: T) -> Unit>()
    private val onStartFunctions = ArrayList<() -> Unit>()

    override fun onStart() {
        onStartFunctions.forEach { it() }
    }

    override fun onError(e: Throwable) {
        return (e).let { exception ->
            if (onErrorFunctions.isEmpty()) {
                throw OnErrorNotImplementedException(e)
            } else {
                onErrorFunctions.forEach { it(exception) }
            }
        }
    }


    override fun onComplete() {
        onCompletedFunctions.forEach { it() }
    }

    override fun onNext(value: T) {
        onNextFunctions.forEach { it(value) }
    }

    /*override fun onSubscribe(d: Disposable) {
        onStartFunctions.forEach { it() }
    }*/

    fun onCompleted(onCompletedFunction: () -> Unit): FunctionalSubscriber<T> = apply {
        onCompletedFunctions.add(onCompletedFunction)
    }

    fun onError(onErrorFunction: (throwable: Throwable) -> Unit): FunctionalSubscriber<T> = apply {
        onErrorFunctions.add(onErrorFunction)
    }

    fun onNext(onNextFunction: (value: T) -> Unit): FunctionalSubscriber<T> = apply {
        onNextFunctions.add(onNextFunction)
    }

    fun onStart(onStartFunction: () -> Unit): FunctionalSubscriber<T> = apply {
        onStartFunctions.add(onStartFunction)
    }

}

class FunctionalSingleSubscriber<T> : SingleObserver<T> {

    private val onSuccessFunctions = ArrayList<(value: T) -> Unit>()
    private val onErrorFunctions = ArrayList<(throwable: Throwable) -> Unit>()
    private val onStartFunctions = ArrayList<() -> Unit>()

    override fun onSuccess(value: T) {
        onSuccessFunctions.forEach { it(value) }
    }

    override fun onError(e: Throwable) {
        return (e).let { exception ->
            if (onErrorFunctions.isEmpty()) {
                throw OnErrorNotImplementedException(exception)
            } else {
                onErrorFunctions.forEach { it(exception) }
            }
        }
    }

    fun onSuccess(onSuccessFunction: (value: T) -> Unit): FunctionalSingleSubscriber<T> = apply {
        onSuccessFunctions.add(onSuccessFunction)
    }

    fun onError(onErrorFunction: (throwable: Throwable) -> Unit): FunctionalSingleSubscriber<T> = apply {
        onErrorFunctions.add(onErrorFunction)
    }

    override fun onSubscribe(d: Disposable) {
        onStartFunctions.forEach { it() }
    }

}

fun <T> subscriber(): FunctionalSubscriber<T> = FunctionalSubscriber()
fun <T> subscriber(init: FunctionalSubscriber<T>.() -> Unit):
        FunctionalSubscriber<T> = FunctionalSubscriber<T>().apply(init)

fun <T> singleSubscriber(): FunctionalSingleSubscriber<T> = FunctionalSingleSubscriber()
fun <T> singleSubscriber(init: FunctionalSingleSubscriber<T>.() -> Unit):
        FunctionalSingleSubscriber<T> = FunctionalSingleSubscriber<T>().apply(init)


fun <T> preferLastObservable(vararg sources: Observable<T>) = preferLastObservable(sources.toList())

fun <T> preferLastObservable(sources: List<Observable<T>>): Observable<T> {
    return Observable.create { subscriber ->
        var lastIndex = -1
        val errorsAndCompletesCount = AtomicInteger(0)

        sources.forEachIndexed { i, observable ->
            observable.subscribe(subscriber<T> {
                onNext { value ->
                    synchronized(lastIndex) {
                        if (lastIndex < i) {
                            subscriber.onNext(value)
                            lastIndex = i
                        }
                    }
                }
                onCompleted {
                    errorsAndCompletesCount.incrementAndGet()

                    if (i == sources.lastIndex) {
                        subscriber.onComplete()
                    }

                }
                onError { exception ->
                    if (errorsAndCompletesCount.incrementAndGet() == sources.size) {
                        subscriber.onError(exception)
                    } else {
                        Log.e("rx", exception.message)
                    }
                }
            })
        }
    }
}