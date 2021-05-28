package com.example.wanandroidsmart.ui.main.home

import android.util.Log
import com.example.baselib.base.BasePresenter
import com.example.wanandroidsmart.http.HttpDefaultObserver
import com.example.wanandroidsmart.http.RetrofitHelper
import com.zs.wanandroid.entity.BannerEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(view: HomeContract.View) : BasePresenter<HomeContract.View>(view),
    HomeContract.Present<HomeContract.View> {

    override fun loadData(pageNum: Int) {

    }

    override fun loadBanner() {
        RetrofitHelper.getApiService()
            .getBanner()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<MutableList<BannerEntity>>() {
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: MutableList<BannerEntity>) {
                    Log.e("======", "======onSuccess:" + t.size)
                    view?.showBanner(t)
                }

                override fun onError(errorMsg: String) {
                    Log.e("======", "======errorMsg:" + errorMsg)
                    view?.onError(errorMsg)
                }
            })
    }

    override fun collect(id: Int) {

    }

    override fun unCollect(id: Int) {

    }
}