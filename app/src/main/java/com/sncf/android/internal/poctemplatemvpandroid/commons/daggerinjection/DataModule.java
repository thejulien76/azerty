package com.sncf.android.internal.poctemplatemvpandroid.commons.daggerinjection;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.sncf.android.internal.poctemplatemvpandroid.commons.bdd.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Module permettant la gestion de la donnée.
 *
 * @author PZII11871
 * @version 1.0
 * @since 20/03/2018
 */

@Module
public class DataModule {

    /**
     * Fourni un accès a la base de données.
     * @param context
     * @return
     */
    @Provides
    @Singleton
    AppDatabase provideAmortissementDao(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, AppDatabase.NAME).build();
    }
}
