package com.example.wanandroidsmart.http;

import com.example.baselib.BaseApplication;
import com.example.baselib.http.HttpLoggingInterceptor;
import com.example.wanandroidkotlin.contants.ApiConstants;
import com.example.wanandroidkotlin.contants.Constants;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static OkHttpClient.Builder getOkHttpClientBuilder() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkHttp");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);
        File cacheFile = new File(BaseApplication.Companion.getContext().getCacheDir(), "cache");
        //100Mb
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);

        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(),
                        new SharedPrefsCookiePersistor(BaseApplication.Companion.getContext()));
        return new OkHttpClient.Builder()
                .readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
//                .addInterceptor(new AddCookiesInterceptor())
//                .addInterceptor(new SaveCookiesInterceptor())
                .cookieJar(cookieJar)
                .cache(cache);
    }

    static Retrofit factory() {
        OkHttpClient okHttpClient = getOkHttpClientBuilder().build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//?????????rxjava?????????
                .baseUrl(ApiConstants.BASE_URL)
                .build();
    }
}
