package com.sncf.android.internal.poctemplatemvpandroid.commons.daggerinjection;

import com.sncf.android.internal.poctemplatemvpandroid.AppApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AndroidInjectionModule.class,
        AppModule.class,
        DataModule.class,
        DebugApiModule.class,
        DebugActivityModule.class})
public interface DebugAppComponent extends AndroidInjector<AppApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<AppApplication> {

    }

}
