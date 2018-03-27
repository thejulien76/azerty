package com.sncf.android.internal.poctemplatemvpandroid.commons.daggerinjection;


import com.sncf.android.internal.poctemplatemvpandroid.listedegares.di.ReleaseListeGareModule;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.view.ListeGareActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 *
 * Module dagger permettant de lier a la donnée.
 *
 * @author PNVE12891
 * @since 20/03/2018
 */

@Module
abstract class ReleaseActivityModule {

    /**
     *  Distribue la donnée.
     * @return
     */
    @PerActivity
    @ContributesAndroidInjector(modules = { ReleaseListeGareModule.class })
    abstract ListeGareActivity contributesListeGareActivity();

}