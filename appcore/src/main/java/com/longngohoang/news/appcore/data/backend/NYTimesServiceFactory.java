package com.longngohoang.news.appcore.data.backend;

import android.util.Log;

import com.google.gson.Gson;
import com.longngohoang.news.appcore.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Provide "make" methods to create instances of {@link NYTimesServiceApi}
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
public class NYTimesServiceFactory {

    public static NYTimesServiceApi makeService() {
        OkHttpClient okHttpClient = makeOkHttpClient(makeLoggingInterceptor());
        return makeService(okHttpClient);
    }

    public static NYTimesServiceApi makeService(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.NYTIMES_API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        return retrofit.create(NYTimesServiceApi.class);
    }

    public static OkHttpClient makeOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        HttpUrl originalHttpUrl = original.url();
                        HttpUrl url = originalHttpUrl.newBuilder()
                                .addQueryParameter("api_key", BuildConfig.NYTIMES_API_KEY)
                                .build();

                        // Request customization: add request headers
                        Request.Builder requestBuilder = original.newBuilder()
                                .url(url);

                        Request request = requestBuilder.build();

                        Log.d("OKHTTP", "intercept: "+request.url().toString());
                        return chain.proceed(request);
                    }
                })
                .build();
    }


    public static HttpLoggingInterceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }


}
