package com.example.wanandroidsmart.http;

/**
 * des 实例化Retrofit,获取ApiService
 *
 * @author zs
 * @date 2020-03-05
 */
public class RetrofitHelper {
    private static ApiService apiService;
    private RetrofitHelper(){}
    public static ApiService getApiService(){
        return apiService;
    }
    static {
        apiService= RetrofitFactory22.factory().create(ApiService.class);
    }
}
