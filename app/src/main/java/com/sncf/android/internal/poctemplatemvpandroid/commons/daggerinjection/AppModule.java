package com.sncf.android.internal.poctemplatemvpandroid.commons.daggerinjection;

import android.app.Application;
import android.content.Context;

import com.sncf.android.internal.poctemplatemvpandroid.AppApplication;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 *
 * Module Dagger pour fournir le context et l'application
 *
 * @author PNVE12891
 * @version 1.0
 * @since 20/03/2018
 */

@Module
public abstract class AppModule {

    /**
     * Fourni le contexte
     * @param app
     * @return
     */
    @Binds
    @Singleton
    abstract Context provideContext(AppApplication app);


    /**
     * Fournir l'application
     * @param app
     * @return
     */
    @Binds
    @Singleton
    abstract Application provideApplication(AppApplication app);
}
