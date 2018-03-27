package com.sncf.android.internal.poctemplatemvpandroid.listedegares.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sncf.android.internal.poctemplatemvpandroid.R;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Gare;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Localisation;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.presenter.ListeGareContrat;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.DaggerActivity;
import io.reactivex.annotations.NonNull;

/**
 *
 * View affichant la liste des gares.
 *
 * @author PZII11871
 * @version 1.0
 * @since 15/03/2018
 */

public class ListeGareActivity extends DaggerActivity implements ListeGareContrat.ListeGareVueContrat, ListeGareEvenementListener {

    private static final String BUNDLE_EXTRA_LISTE_GARES = "BUNDLE_EXTRA_LISTE_GARES";

    @Inject
    ListeGareContrat.ListeGarePresenterContrat listeGarePresenter;
    private ProgressDialog progressDialog;
    private List<Gare> listeGare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = initProgressDialog(this);
        progressDialog.show();

        listeGarePresenter.attacherVue(this);
        listeGarePresenter.recupererGares();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(BUNDLE_EXTRA_LISTE_GARES, (Serializable) listeGare);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        listeGare = (List<Gare>) savedInstanceState.get(BUNDLE_EXTRA_LISTE_GARES);
    }

    @Override
    public void appelReseauEnSucces() {
        progressDialog.setTitle("Insertion en base de données");
    }

    @Override
    public void recuperationGareEnSucces(@NonNull List<Gare> listeGare) {
        this.listeGare = listeGare;
        progressDialog.dismiss();
        ListView listViewGares = findViewById(R.id.listViewGares);
        ListGareAdapter listGareAdapter = new ListGareAdapter(listeGare, this, this);
        listViewGares.setAdapter(listGareAdapter);
    }

    @Override
    public void recuperationLocalisationEnSucces(@NonNull Localisation localisation) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + localisation.getLatitude() + "," + localisation.getLongitude());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    public void echec(String messagerErreur) {
        progressDialog.dismiss();
        Toast.makeText(this, "Error "+ messagerErreur, Toast.LENGTH_SHORT).show();
    }

    public ProgressDialog initProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Window window = progressDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.progress_bar_layout);
            TextView messageProgressBar = progressDialog.findViewById(R.id.textViewProgressBar);
            messageProgressBar.setText("Récupération des gares à distance");
        }
        return progressDialog;
    }

    @Override
    public void afficherLocalisation(Gare gare) {
        listeGarePresenter.recupererLocalisation(gare);
    }
}
