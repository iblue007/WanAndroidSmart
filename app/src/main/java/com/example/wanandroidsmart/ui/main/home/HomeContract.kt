package com.example.wanandroidsmart.ui.main.home

import com.example.baselib.base.IBasePresenter
import com.example.baselib.base.IBaseView
import com.zs.wanandroid.entity.ArticleEntity
import com.zs.wanandroid.entity.BannerEntity

interface HomeContract {

    interface View : IBaseView {
        fun showList(list: MutableList<ArticleEntity.DatasBean>)
        fun showBanner(bannerList:MutableList<BannerEntity>)
        fun collectSuccess()
        fun unCollectSuccess()
    }

    interface Present<T> : IBasePresenter<View> {
        fun loadData(pageNum: Int)
        fun loadBanner()
        fun collect(id: Int)
        fun unCollect(id: Int)
    }
}