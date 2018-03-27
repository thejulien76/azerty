package com.sncf.android.internal.poctemplatemvpandroid.listedegares.di;

import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.api.RequestInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Copyright (c) 2016 - Société Nationale des Chemins de fer Français
 * <p/>
 * Toute reproduction ou diffusion intégrale ou partielle par quelque procédé que ce soit ne peut
 * être faite sans l'autorisation préalable de la SNCF.
 *
 * @author PNVE12891
 * @version 1.0.0-1
 * @since 15/03/2018
 */

@Module
public class ReleaseListeGareModule extends ListeGareModule {

    @Provides
    RequestInterface provideRequestInterface(Retrofit retrofit) {
        return retrofit.create(RequestInterface.class);
    }

}
