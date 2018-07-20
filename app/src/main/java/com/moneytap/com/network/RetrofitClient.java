package com.moneytap.com.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.facebook.FacebookSdk.getCacheDir;

public class RetrofitClient {

    static int cacheSize = 10 * 1024 * 1024;
    static Cache cache = new Cache(getCacheDir(), cacheSize);
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor)
                .build();
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl).client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}