package com.sncf.android.internal.poctemplatemvpandroid.listedegares.presenter;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;

import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Gare;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Localisation;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Record;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Records;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
/**
 *
 * Presenter
 *
 * @author PZII11871
 * @version 1.0
 * @since 15/03/2018
 */

public class ListeGarePresenter implements ListeGareContrat.ListeGarePresenterContrat {

    private ListeGareContrat.ListeGareVueContrat mListeGareVueContrat;
    private ListeGareContrat.ListeGareModeleContrat mListeGareModeleContrat;

    /**
     * Constructeur
     * @param listeGareModeleContrat
     */
    @Inject
    public ListeGarePresenter(ListeGareContrat.ListeGareModeleContrat listeGareModeleContrat) {
        mListeGareModeleContrat = listeGareModeleContrat;
    }

    /**
     * Attache la vue
     * @param listeGareVueContrat
     */
    @Override
    public void attacherVue(ListeGareContrat.ListeGareVueContrat listeGareVueContrat) {
        mListeGareVueContrat = listeGareVueContrat;
    }

    /**
     * Récupére les localisations.
     * @param gare
     */
    @Override
    public void recupererLocalisation(Gare gare) {
        mListeGareModeleContrat.recupererLocalisationLocale(gare)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Localisation>() {
                    @Override
                    public void onSuccess(@NonNull Localisation localisation) {
                        mListeGareVueContrat.recuperationLocalisationEnSucces(localisation);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mListeGareVueContrat.echec("Erreur requête bdd");
                    }
                });
    }

    /**
     * Récupére les gares.
     */
    @Override
    public void recupererGares() {
        mListeGareModeleContrat.recupererGaresADistance()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Records>() {
                    @Override
                    public void onSuccess(Records records) {
                        mListeGareVueContrat.appelReseauEnSucces();
                        List<Gare> gareList = traductionRecordsVersGares(records);
                        if (gareList != null) {
                            majGaresLocal(gareList);
                        } else {
                            mListeGareVueContrat.echec("Liste vide");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mListeGareVueContrat.echec(e.getLocalizedMessage());
                    }
                });
    }

    /**
     *  Mise à jour (update) des gares.
     * @param gareList
     */
    private void majGaresLocal(List<Gare> gareList) {
        Completable.fromAction(() -> mListeGareModeleContrat.supprimerToutesGares())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        insererGaresLocal(gareList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mListeGareVueContrat.echec("Erreur insertion bdd");
                        Log.d("BDD",e.getMessage());
                    }
                });
    }

    /**
     * Ajouter une gare dans le dépot local.
     * @param gareList
     */
    private void insererGaresLocal(List<Gare> gareList) {
        Completable.fromAction(() -> mListeGareModeleContrat.insererGares(gareList))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        getLocalGares();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mListeGareVueContrat.echec("Erreur insertion bdd");
                        Log.d("BDD",e.getMessage());
                    }
                });
    }

    /**
     * Récupérer les gares dans le dépot local.
     */
    private void getLocalGares() {
        mListeGareModeleContrat.recupererGaresLocale()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Gare>>() {
                    @Override
                    public void onSuccess(@NonNull List<Gare> gares) {
                        mListeGareVueContrat.recuperationGareEnSucces(gares);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mListeGareVueContrat.echec("Erreur requête bdd");
                    }
                });
    }


    /**
     *  Tradcution de l'objet Records en objet liste de Gare.
     * @param records
     * @return
     */
    List<Gare> traductionRecordsVersGares(Records records) {
        List<Gare> result = null;
        if (records != null) {
            List<Record> listeRecords = records.getRecordList();
            if (listeRecords != null && !listeRecords.isEmpty()) {
                result = new ArrayList<>();
                for (Record record : listeRecords) {
                    record.getGare().setLocalisation(traductionCoordonneesEnLocalisation(record.getGare().getCoordonnees()));
                    result.add(record.getGare());
                }
            }
        }
        return result;
    }

    /**
     * Traduction des coordonnées en objet Localisation.
     * @param coordonnees
     * @return
     */
    @TypeConverter
    Localisation traductionCoordonneesEnLocalisation(String[] coordonnees){
        Localisation localisation = null;
        if (coordonnees != null && coordonnees.length == 2){
            if (!isAlpha(coordonnees[0]) && !isAlpha(coordonnees[1])) {
                localisation = new Localisation();
                localisation.setLatitude(Float.valueOf(coordonnees[0]));
                localisation.setLongitude(Float.valueOf(coordonnees[1]));
            }
        }
        return localisation;
    }

    /**
     * Controle si la chaine de caractere contient que des caractere alpha.
     * @param name
     * @return
     */
    private boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }


}
