package com.sncf.android.internal.poctemplatemvpandroid.listedegares.di;

import com.sncf.android.internal.poctemplatemvpandroid.commons.bdd.AppDatabase;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.ListeGareRepository;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.local.ListeGareLocalDataSource;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.remote.ListeGareRemoteDataSource;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.presenter.ListeGareContrat;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.presenter.ListeGarePresenter;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Accés à la donnée externe.
 *
 * @author PZII11871
 * @version 1.0
 * @since 20/03/2018
 */

@Module
public class ListeGareModule {

    /**
     *  Fourni un acces au depot.
     * @param listeGareRemoteDataSource
     * @param listeGareLocalDataSource
     * @return
     */
    @Provides
    ListeGareContrat.ListeGareModeleContrat provideListeGareModele
            (ListeGareRemoteDataSource listeGareRemoteDataSource, ListeGareLocalDataSource listeGareLocalDataSource) {
        return new ListeGareRepository(listeGareRemoteDataSource, listeGareLocalDataSource);
    }

    /**
     * Fourni un acces au dépot local de gares.
     * @param appDatabase
     * @return
     */
    @Provides
    ListeGareLocalDataSource provideListeGareLocalDataSource(AppDatabase appDatabase) {
        return new ListeGareLocalDataSource(appDatabase);
    }

    /**
     * Fourni le presenter
     * @param listeGareModeleContrat
     * @return
     */
    @Provides
    ListeGareContrat.ListeGarePresenterContrat provideListeGarePresenterContrat
            (ListeGareContrat.ListeGareModeleContrat listeGareModeleContrat) {
        return new ListeGarePresenter(listeGareModeleContrat);
    }

}
