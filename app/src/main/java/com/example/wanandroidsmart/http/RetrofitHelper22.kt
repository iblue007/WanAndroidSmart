package com.example.wanandroidsmart.http

object RetrofitHelper22 {

    fun getApiService(): ApiService? {
        return RetrofitFactory.factory()!!.create(ApiService::class.java)
    }
}