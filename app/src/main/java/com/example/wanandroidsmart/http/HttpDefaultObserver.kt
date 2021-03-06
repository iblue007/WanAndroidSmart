package com.example.wanandroidsmart.http

import android.util.Log
import com.example.baselib.http.BusinessHttpException
import com.example.wanandroidsmart.util.AppManager
import com.google.gson.JsonParseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import java.lang.reflect.ParameterizedType
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


abstract class HttpDefaultObserver<T> : Observer<BaseResponse<T>> {

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
        disposable(d)
    }

    override fun onNext(t: BaseResponse<T>) {
        if (t.errorCode == 0) {

            if (t.data == null) {
                val tClass =
                    (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
                t.data = tClass.newInstance()
            }
            t.data?.let { onSuccess(it) }
        }
        //code!=0代表业务出错，进行过滤
        else {
            filterCode(t.errorMsg, t.errorCode)
        }
    }

    override fun onError(e: Throwable) {
        var errorMsg = if (e is UnknownHostException) {
            "网络异常"
        } else if (e is JSONException || e is JsonParseException) {
            "数据异常"
        } else if (e is SocketTimeoutException) {
            "连接超时"
        } else if (e is ConnectException) {
            "连接错误"
        } else if (e is BusinessHttpException) {
            e.businessMessage
        } else {
            "未知错误"
        }
        onError(errorMsg)
    }

    private fun filterCode(msg: String, code: Int) {
        when (code) {
            //登录失败
            -1001 -> {
                AppManager.resetUser()
                onError(BusinessHttpException(msg, code))
            }
            405 -> {//需要登录
                Log.e("======", "======登录")
            }
            //未知code,将errorMsg封装成异常,由onError()处理
            else -> onError(BusinessHttpException(msg, code))
        }
    }

    abstract fun disposable(d: Disposable)
    abstract fun onSuccess(t: T)
    abstract fun onError(errorMsg: String)

}