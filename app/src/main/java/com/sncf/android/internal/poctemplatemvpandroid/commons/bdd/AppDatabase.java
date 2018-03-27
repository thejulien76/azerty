package com.sncf.android.internal.poctemplatemvpandroid.commons.bdd;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.dao.GareDao;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.dao.LocalisationDao;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Gare;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Localisation;

import javax.inject.Singleton;

/**
 *
 * Contient le database holder et sert de point d'accès principal aux données relationnelles.
 *
 * @author PNVE12891
 * @version 1.0
 * @since 20/03/2018
 */

@Singleton
@Database(entities = {Gare.class, Localisation.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String NAME = "app-database";

    /**
     *  Récupére le Gare DAO
     * @return
     */
    public abstract GareDao getGareDao();

    /**
     *  Récupére le Localisation DAO
     * @return
     */
    public abstract LocalisationDao getLocalisationDao();

}
