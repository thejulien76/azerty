package com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Gare;

import java.util.List;

import io.reactivex.Single;

/**
 *
 * Object d'accés à la donnée : Gare
 *
 * @author PZII11871
 * @version 1.0
 * @since 20/03/2018
 */

@Dao
public interface GareDao {

    /**
     * Renvoie la liste de Gare.
     * @return
     */
    @Transaction
    @Query("SELECT * FROM gares")
    Single<List<Gare>> getGaresBdd();

    /**
     * Ajouter un objet gare en base.
     * @param gare
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGare(Gare gare);

    /**
     * Supprimer les objet de la table gares.
     */
    @Query("DELETE FROM gares")
    void deleteAllGares();
}
