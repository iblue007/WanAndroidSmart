package com.example.baselib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<P : IBasePresenter<*>> : Fragment() {

    protected var presenter: P? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
        presenter?.let {
            lifecycle.addObserver(it)
        }
    }

    abstract fun createPresenter(): P?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(getLayoutId(), null)
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init(savedInstanceState)
    }

    abstract fun getLayoutId(): Int

    protected abstract fun init(savedInstanceState: Bundle?)


}