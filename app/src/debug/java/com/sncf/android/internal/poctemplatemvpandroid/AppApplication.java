package com.sncf.android.internal.poctemplatemvpandroid;


import android.os.StrictMode;

import com.facebook.stetho.Stetho;
import com.sncf.android.internal.poctemplatemvpandroid.commons.daggerinjection.DaggerDebugAppComponent;
import com.squareup.leakcanary.LeakCanary;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 *
 *  Application Custom
 *
 * @author PNVE12891
 * @since 20/03/2018
 */

public class AppApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerDebugAppComponent.builder().create(this);
    }

    /**
     *  Install de LeakCanary permettant de voir les fuites mémoires.
     */
   private void initialisationLeakCanary() {
       if (LeakCanary.isInAnalyzerProcess(this)) {
           // This process is dedicated to LeakCanary for heap analysis.
           // You should not init your app in this process.
           return;
       }

       LeakCanary.install(this);
       // Normal app init code...
   }

    /**
     *  Initialisation du StrictMode permettant de mettre en lumiere les erreurs.
     */
    private void initializeStrictMode() {
        // Initialisation StrictMode
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //Initialisation Stetho
        Stetho.initializeWithDefaults(this);
        this.initializeStrictMode();
    }
}
