package com.example.wanandroidsmart.http

import com.zs.wanandroid.entity.BannerEntity
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.http.GET

interface ApiService {

    /**
     * banner
     */
    @GET("/banner/json")
    fun getBanner(): Observable<BaseResponse<MutableList<BannerEntity>>>
}