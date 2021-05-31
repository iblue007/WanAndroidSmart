package com.example.wanandroidsmart.http

import com.zs.wanandroid.entity.ArticleEntity
import com.zs.wanandroid.entity.BannerEntity
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    /**
     * 获取首页文章数据
     */
    @GET("/article/list/{page}/json")
    fun getHomeList(@Path("page") pageNo: Int): Observable<BaseResponse<ArticleEntity>>

    /**
     * banner
     */
    @GET("/banner/json")
    fun getBanner(): Observable<BaseResponse<MutableList<BannerEntity>>>
}