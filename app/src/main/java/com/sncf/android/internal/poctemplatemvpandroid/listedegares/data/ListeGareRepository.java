package com.sncf.android.internal.poctemplatemvpandroid.listedegares.data;

import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.local.ListeGareLocalDataSource;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Gare;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Localisation;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Records;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.remote.ListeGareRemoteDataSource;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.presenter.ListeGareContrat;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 *
 * Dépot contennant la donnée.
 *
 * @author PNVE12891
 * @version 1.0
 * @since 20/03/2018
 */

public class ListeGareRepository implements ListeGareContrat.ListeGareModeleContrat {

    // Source de données distantes.
    private ListeGareRemoteDataSource listeGareRemoteDataSource;
    // Source de données locales.
    private ListeGareLocalDataSource listeGareLocalDataSource;

    /**
     * Constructeur
     * @param listeGareRemoteDataSource
     * @param listeGareLocalDataSource
     */
    @Inject
    public ListeGareRepository(ListeGareRemoteDataSource listeGareRemoteDataSource, ListeGareLocalDataSource listeGareLocalDataSource) {
        this.listeGareRemoteDataSource = listeGareRemoteDataSource;
        this.listeGareLocalDataSource = listeGareLocalDataSource;
    }

    /**
     *  Récupére les gares dans le depot distant.
     * @return
     */
    @Override
    public Single<Records> recupererGaresADistance() {
       return listeGareRemoteDataSource.recupererGaresADistance();
    }

    /**
     * Récupére les gare dans le dépot local.
     * @return
     */
    @Override
    public Single<List<Gare>> recupererGaresLocale() {
        return listeGareLocalDataSource.recupererGares();
    }

    /**
     * récupére les localisation dans le dépot local.
     * @param gare
     * @return
     */
    @Override
    public Single<Localisation> recupererLocalisationLocale(Gare gare) {
        return listeGareLocalDataSource.recupererLocalisation(gare);
    }

    /**
     *  Insert une liste de gare dans le dépot local.
     * @param gareList
     */
    @Override
    public void insererGares(List<Gare> gareList) {
        listeGareLocalDataSource.insererGares(gareList);
    }

    /**
     * Suppression de tous les gares du dépot local.
     */
    @Override
    public void supprimerToutesGares() {
        listeGareLocalDataSource.supprimerToutesGares();
    }
}
