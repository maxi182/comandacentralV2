package com.mcba.comandacentralv2.api;

import android.content.Context;
import android.util.Log;

import com.mcba.comandacentralv2.BuildConfig;
import com.mcba.comandacentralv2.Utils.Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by maximiliano.ferraiuolo on 08/11/2016.
 */

public class RestClient {

    private static ApiEndpoints sEndPoints;
    private static Context mContext;
    private final static Lock locks = new ReentrantLock();

    public static void init(Context context) {
        sEndPoints = getRetrofit(context).create(ApiEndpoints.class);
        mContext = context;
    }

    public static ApiEndpoints getApiService() {
        return sEndPoints;
    }

    public static Interceptor getApiInterceptor() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                //Build new request
                Request.Builder builder = request.newBuilder();
                builder.header("Accept", "application/json"); //if necessary, say to consume JSON
                String token = "";//TiiprApplication.getInstance().getLoginManager().getAuthToken();
                Log.d("TOKEN:", token != null ? token : "");
                if (token != null) {
                    builder.header("Authorization", String.format("Bearer %s", token));
                }
                request = builder.build(); //overwrite old request
                Response response = chain.proceed(request); //perform request, here original request will be executed

                return response;
            }
        };
    }

    private static String getServerUrl() {
        return "https://www.thecocktaildb.com/api/json/v1/1/";
    }

    public static Retrofit getRetrofit(Context context) {
        return new Retrofit.Builder()
                .baseUrl(getServerUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClient(context))
                .build();
    }


    private static OkHttpClient getHttpClient(Context context) {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder()
                .addInterceptor(getHttpDebugInterceptor())
                .addInterceptor(getApiInterceptor())
                .addInterceptor(getCacheInterceptor())
                .addInterceptor(getOfflineCacheInterceptor(context))
                .writeTimeout(60, TimeUnit.SECONDS)
                .cache(getCache(context));

        return httpBuilder.build();
    }


    public static Interceptor getCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());

                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(1, TimeUnit.HOURS)
                        .build();

                return response.newBuilder()
                        .header("Cache-Control", cacheControl.toString())
                        .build();
            }
        };
    }

    public static Interceptor getOfflineCacheInterceptor(final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                if (!Utils.isNetworkConnectionAvailable(context)) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(1, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }

    private static Interceptor getHttpDebugInterceptor() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level body =
                BuildConfig.DEBUG ?
                        HttpLoggingInterceptor.Level.BODY :
                        HttpLoggingInterceptor.Level.NONE;
        loggingInterceptor.setLevel(body);
        return loggingInterceptor;
    }

    private static Cache getCache(Context context) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(context.getCacheDir(), cacheSize);
    }


    private static void logout() {

    }

}
