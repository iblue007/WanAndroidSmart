package com.example.baselib.base

import android.os.Bundle

abstract class LazyFragment<P : IBasePresenter<*>> : BaseFragment<P>() {

    private var isLoad = false
    override fun init(savedInstanceState: Bundle?) {

    }

    override fun onResume() {
        super.onResume()
        if (!isLoad && !isHidden) {
            lazyInt()
            isLoad = true;
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isLoad = false
    }

    abstract fun lazyInt()
}