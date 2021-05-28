package com.example.baselib

import android.app.Application
import android.content.Context

abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        baseApplication = this;
    }

    companion object {
        //单例
        private var baseApplication: BaseApplication? = null
        fun getContext(): Context {
            return baseApplication!!
        }
    }
}