package com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Localisation;

import java.util.List;

import io.reactivex.Single;

/**
 *
 * Object d'accés à la donnée : Localisation
 *
 * @author PBFW01891
 * @version 1.0
 * @since 13/03/2018
 */
@Dao
public interface LocalisationDao {

    /**
     * Retourne toutes les localisations.
     * @return
     */
    @Transaction
    @Query("SELECT * FROM LOCALISATIONS")
    Single<List<Localisation>> getLocalisationsBdd();


    /**
     * Récupére les localisations en fonction de la gare.
     * @param idGare
     * @return
     */
    @Transaction
    @Query("SELECT * FROM LOCALISATIONS where id= :idGare")
    Single<Localisation> getLocalisationByGareBdd(long idGare);

    /**
     * Ajoute une localisation
     * @param localisation
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLocalisation(Localisation localisation);

    /**
     *  Compte le nombre de localisation dans la table.
     * @return
     */
    @Query("SELECT COUNT(*) from LOCALISATIONS")
    int countLocalisation();

    /**
     * Supprime tous les localisations.
     */
    
    // FIXME: 20/03/2018 Ce ne serait pas plutôt "DELETE FROM LOCALISATION"
    @Query("DELETE FROM gares")
    void deleteAllLocalisations();
}
