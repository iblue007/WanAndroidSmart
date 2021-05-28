package com.example.baselib.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<V : IBaseView>(view: V) : IBasePresenter<V> {

    protected var view: V? = view
    private var compositeDisposable: CompositeDisposable? = null

    init {//Kotlin中的init代码块就相当于Java中的普通代码块，在创建对象的时候代码块会先执行。注意是每次创建都会执行一遍
        compositeDisposable = CompositeDisposable()
    }

    override fun onCreate() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onDestroy() {
        compositeDisposable?.clear()
    }

    protected fun addSubscribe(disposable: Disposable) {
        compositeDisposable?.add(disposable)
    }
}