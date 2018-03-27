package com.sncf.android.internal.poctemplatemvpandroid.commons.daggerinjection;


import com.sncf.android.internal.poctemplatemvpandroid.listedegares.di.DebugListeGareModule;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.view.ListeGareActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 *
 * Module Dagger spécifique à l'envirronement de dev/debug
 *
 * @author PZII11871
 * @version 1.0
 * @since 15/03/2018
 */

@Module
abstract class DebugActivityModule {

    @PerActivity
    @ContributesAndroidInjector(modules = { DebugListeGareModule.class })
    abstract ListeGareActivity contributesListeGareActivity();

}