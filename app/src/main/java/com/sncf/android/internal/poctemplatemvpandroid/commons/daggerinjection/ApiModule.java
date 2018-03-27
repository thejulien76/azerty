package com.sncf.android.internal.poctemplatemvpandroid.commons.daggerinjection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Module Dagger, communication entre l'api et l'application
 *
 * @author PNVE12891
 * @version 1.0
 * @since 20/03/2018
 */

@Module
public class ApiModule {

    private static final String BASE_URL = "https://ressources.data.sncf.com/"; // url de l'api

    /**
     * Fournir le Retrofit qui permet de faire les appels http
     *
     * @param okHttpClient
     * @return Retrofit
     */
    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    /**
     * Fourni le client http
     * @return OkHttpClient
     */
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }


    /**
     * Fourni Gson : désérialiseur.
     * @return
     */
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }
}
