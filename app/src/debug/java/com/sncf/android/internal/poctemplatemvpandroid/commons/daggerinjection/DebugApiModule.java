package com.sncf.android.internal.poctemplatemvpandroid.commons.daggerinjection;

import android.app.Application;

import com.google.gson.Gson;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.api.MockRequestInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.mock.MockRetrofit;

/**
 *
 * Communication avec l'api en envirronement de debug.
 *
 * @author PZII11871
 * @version 1.0
 * @since 15/03/2018
 */

@Module(includes = ApiModule.class)
public class DebugApiModule {

    @Provides
    @Singleton
    MockRetrofit provideMockRetrofit(Retrofit retrofit) {
        return new MockRetrofit.Builder(retrofit).build();
    }

    @Provides
    @Singleton
    MockRequestInterface provideRequestInterface(Application application,
                                                 MockRetrofit mockRetrofit, Gson gson) {
        return new MockRequestInterface(application, mockRetrofit, gson);
    }



}
