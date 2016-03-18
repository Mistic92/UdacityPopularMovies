package com.lukaszbyjos.popularmovies;


import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by lbyjos on 2015-12-07 on 14:16.
 */
public class OkHttpSingleton {

    private static OkHttpClient okHttpClient;


    private OkHttpSingleton() {

    }

    private static Cache getCache() {
        final int cacheSize = 10 * 1024 * 1024; // 10 MiB
        File cacheDir = new File(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString());
        return new Cache(cacheDir, cacheSize);
    }

    public static OkHttpClient getOkHttpClient() {

        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .cache(getCache());
            if (BuildConfig.DEBUG)
                builder.addInterceptor(new StethoInterceptor());
            okHttpClient = builder.build();
        }

        return okHttpClient;
    }
}
