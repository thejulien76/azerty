package com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.local;

import android.arch.persistence.room.Transaction;
import android.util.Log;

import com.sncf.android.internal.poctemplatemvpandroid.commons.bdd.AppDatabase;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.dao.GareDao;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.dao.LocalisationDao;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Gare;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Localisation;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 *
 * Stockage des données en local.
 *
 * @author PZII11871
 * @version 1.0
 * @since 20/03/2018
 */

public class ListeGareLocalDataSource {

    private GareDao gareDao;
    private LocalisationDao localisationDao;

    /**
     * Constructor
     * @param appDatabase
     */
    @Inject
    public ListeGareLocalDataSource(AppDatabase appDatabase) {
        this.gareDao = appDatabase.getGareDao();
        this.localisationDao = appDatabase.getLocalisationDao();
    }

    /**
     * Ajouter une liste de gare.
     * @param gareList
     */
    @Transaction
    public void insererGares(List<Gare> gareList) {
        for (Gare gare : gareList) {
            localisationDao.insertLocalisation(gare.getLocalisation());
            gareDao.insertGare(gare);
        }
        Log.i("localisation"," size localisation" +localisationDao.countLocalisation());
    }

    /**
     * Supprime toutes les gares.
     */
    @Transaction
    public void supprimerToutesGares() {
        gareDao.deleteAllGares();
        localisationDao.deleteAllLocalisations();
    }

    /**
     * Recupere toutes les gares.
     * @return
     */
    public Single<List<Gare>> recupererGares() {
        return gareDao.getGaresBdd();
    }

    /**
     * Récupére la localisation en fonction de la gare.
     * @param gare
     * @return
     */
    public Single<Localisation> recupererLocalisation(Gare gare) { return localisationDao.getLocalisationByGareBdd(gare.getId());}
}
