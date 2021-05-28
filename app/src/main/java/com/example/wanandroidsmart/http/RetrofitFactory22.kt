package com.example.wanandroidsmart.http

import com.example.baselib.BaseApplication.Companion.getContext
import com.example.baselib.http.HttpLoggingInterceptor
import com.example.wanandroidkotlin.contants.ApiConstants
import com.example.wanandroidkotlin.contants.Constants
import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import java.util.logging.Level

object RetrofitFactory22 {

    private fun getOkHttpClientBuilder(): OkHttpClient.Builder {
        val loggingInterceptor = HttpLoggingInterceptor("OkHttp")
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
        loggingInterceptor.setColorLevel(Level.INFO)
        val cacheFile = File(getContext().cacheDir, "cache")
        //100Mb
        val cache = Cache(cacheFile, 1024 * 1024 * 100)
        val cookieJar: ClearableCookieJar = PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(getContext())
        )
        return OkHttpClient.Builder()
            .readTimeout(
                Constants.DEFAULT_TIMEOUT.toLong(),
                TimeUnit.MILLISECONDS
            )
            .connectTimeout(
                Constants.DEFAULT_TIMEOUT.toLong(),
                TimeUnit.MILLISECONDS
            )
            .addInterceptor(loggingInterceptor) //                .addInterceptor(new AddCookiesInterceptor())
            //                .addInterceptor(new SaveCookiesInterceptor())
            .cookieJar(cookieJar)
            .cache(cache)
    }

    @JvmStatic
    fun factory(): Retrofit? {
        val okHttpClient: OkHttpClient = getOkHttpClientBuilder().build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //添加对rxjava的支持
            .baseUrl(ApiConstants.BASE_URL)
            .build()
    }
}