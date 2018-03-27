package com.sncf.android.internal.poctemplatemvpandroid.listedegares.presenter;

import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Gare;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Localisation;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Records;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 *
 * Contrat du presenter, de la vue
 *
 * @author PZII11871
 * @version 1.0
 * @since 20/03/2018
 */

public interface ListeGareContrat {

    /**
     *  Contrat de communication sur la donnée gare.
     */
    interface ListeGareVueContrat {

        /**
         * Communique le Succes de l'appel reseau
         */
        void appelReseauEnSucces();

        /**
         * Recupération de la liste de gare est en succes.
         * @param listeGare
         */
        void recuperationGareEnSucces(List<Gare> listeGare);

        /**
         * Recupére de la liste de localisation
         * @param localisation
         */
        void recuperationLocalisationEnSucces(Localisation localisation);

        /**
         * Une Erreur a eu lieu.
         * @param messagerErreur
         */
        void echec(String messagerErreur);
    }

    /**
     * Actions du Présenter
     */
    interface ListeGarePresenterContrat {

        void attacherVue(ListeGareContrat.ListeGareVueContrat listeGareVueContrat);

        void recupererLocalisation(Gare gare);

        void recupererGares();
    }

    /**
     * Actions sur la donnée
     */
    interface ListeGareModeleContrat {

        /**
         *  recuperation des gares dans le depot distant à partir du WS.
         * @return
         */
        Single<Records> recupererGaresADistance();

        /**
         *  recuperation des gares dans le depot local à partir du WS.
         * @return
         */
        Single<List<Gare>> recupererGaresLocale();

        /**
         *  recuperation la localisation dans le depot local.
         * @return
         */
        Single<Localisation> recupererLocalisationLocale(Gare gare);

        /**
         *  Permet d'insérer une liste de gare.
         * @return
         */
        void insererGares(List<Gare> gareList);

        /**
         *  supprime toutes les gares.
         * @return
         */
        void supprimerToutesGares();

    }
}
