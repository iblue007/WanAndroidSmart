package com.example.baselib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baselib.util.ColorUtils
import com.example.baselib.util.StatusUtils

abstract class BaseActivity<P : IBasePresenter<*>> : AppCompatActivity() {

    protected val TAG = javaClass.name
    protected var presenter: P? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = getLayoutId();
        if (layoutId != 0) {
            setContentView(layoutId)
        }
        presenter = createPresenter()
        presenter?.let {
            lifecycle.addObserver(it)
        }
        setStatusColor()
        setSystemInvadeBlack()
        init(savedInstanceState)
    }

    abstract fun init(savedInstanceState: Bundle?)

    protected open fun setSystemInvadeBlack() {
        //第二个参数是是否沉浸,第三个参数是状态栏字体是否为黑色。
        StatusUtils.setSystemStatus(this, true, true)
    }

    protected open fun setStatusColor() {
        StatusUtils.setUseStatusBarColor(this, ColorUtils.parseColor("#00ffffff"))
    }

    abstract fun createPresenter(): P?

    abstract fun getLayoutId(): Int
}