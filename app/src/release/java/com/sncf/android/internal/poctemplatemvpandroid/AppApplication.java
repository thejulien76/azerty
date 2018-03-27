package com.sncf.android.internal.poctemplatemvpandroid;


import com.sncf.android.internal.poctemplatemvpandroid.commons.daggerinjection.DaggerReleaseAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 *
 * Application custom
 *
 * @author PNVE12891
 * @version 1.0
 * @since 20/03/2018
 */

public class AppApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerReleaseAppComponent
                .builder().create(this);
    }
}
