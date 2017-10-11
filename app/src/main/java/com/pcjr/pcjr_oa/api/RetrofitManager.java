package com.pcjr.pcjr_oa.api;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitManager
 * Created by Mario on 2016/7/25.
 */
public class RetrofitManager {

    private static RetrofitManager mInstance;

    private ApiService apiService;
    private OAuthService authService;

    public static RetrofitManager getInstance() {
        if (mInstance == null) mInstance = new RetrofitManager();
        return mInstance;
    }

    private RetrofitManager() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(10, TimeUnit.SECONDS);
        client.readTimeout(10, TimeUnit.SECONDS);
        client.addInterceptor(new ApiInterceptor());
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiConstant.API_URL)
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();

        client.interceptors().clear();
        client.addInterceptor(new OAuthInterceptor());
        Retrofit oauthRetrofit = new Retrofit.Builder().baseUrl(ApiConstant.MAPI_URL)
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();

        this.apiService = retrofit.create(ApiService.class);
        this.authService = oauthRetrofit.create(OAuthService.class);

    }
    public ApiService getApiService() {
        return apiService;
    }
    public OAuthService getAuthService() {
        return authService;
    }

}
