package com.sncf.android.internal.poctemplatemvpandroid.listedegares.presenter;

import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Gare;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Localisation;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Record;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Records;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListeGarePresenterTest {

    private ListeGarePresenter listeGarePresenter;

    @Before
    public void setUp() {
        listeGarePresenter = new ListeGarePresenter(null);
    }

    @After
    public void tearDown() {
        listeGarePresenter = null;
    }

    @Test
    public void traductionRecordsVersGares_recordsNull() {
        List<Gare> gares = listeGarePresenter.traductionRecordsVersGares(null);
        Assert.assertNull(gares);
    }

    @Test
    public void traductionRecordsVersGares_recordsListNull() {
        Records records = new Records();
        List<Gare> gares = listeGarePresenter.traductionRecordsVersGares(records);
        Assert.assertNull(gares);
    }

    @Test
    public void traductionRecordsVersGares_recordsListEmpty() {
        Records records = new Records();
        records.setRecordList(new ArrayList<>());
        List<Gare> gares = listeGarePresenter.traductionRecordsVersGares(records);
        Assert.assertNull(gares);
    }

    @Test
    public void traductionRecordsVersGares_recordsListWithNoGare() {
        Records records = initRecords(0);
        List<Gare> gares = listeGarePresenter.traductionRecordsVersGares(records);
        Assert.assertNull(gares);
    }

    @Test
    public void traductionRecordsVersGares_recordsListWithOneElement() {
        Records records = initRecords(1);
        List<Gare> gares = listeGarePresenter.traductionRecordsVersGares(records);
        Assert.assertNotNull(gares);
        Assert.assertEquals(1, gares.size());
    }

    private Records initRecords(int nbRecord) {
        Records res = new Records();
        List<Record> recordList = new ArrayList<>();
        for (int i = 0; i < nbRecord; i++) {
            Record record = new Record();
            Gare gare = new Gare();
            gare.setId(i);
            gare.setNomGare("Nom " + i);
            gare.setCommune("Commune " + i);
            gare.setDepartement("Departement " + i);
            record.setGare(gare);
            recordList.add(record);
        }
        res.setRecordList(recordList);
        return res;
    }

    @Test
    public void traductionCoordonneesEnLocalisation_coordonneesNull(){
        Localisation localisation = listeGarePresenter.traductionCoordonneesEnLocalisation(null);
        Assert.assertNull(localisation);
    }

    @Test
    public void traductionCoordonneesEnLocalisation_coordonneesArrayEmpty() {
        String[] coordonnees = new String[0];
        Localisation localisation = listeGarePresenter.traductionCoordonneesEnLocalisation(coordonnees);
        Assert.assertNull(localisation);
    }

    @Test
    public void traductionCoordonneesEnLocalisation_coordonneesArrayLenghtCanOnlyEqualToTwo() {
        String[] coordonnees_1 = new String[1];
        String[] coordonnees_2 = new String[2];
        String[] coordonnees_3 = new String[3];

        coordonnees_1[0] = "0.455544";

        coordonnees_2[0] = "0.455544";
        coordonnees_2[1] = "-2.5666998";

        coordonnees_3[0] = "0.455544";
        coordonnees_3[1] = "-2.5666998";
        coordonnees_3[2] = "-2.5666998";

        Localisation localisation = listeGarePresenter.traductionCoordonneesEnLocalisation(coordonnees_1);
        Assert.assertNull(localisation);

        localisation = listeGarePresenter.traductionCoordonneesEnLocalisation(coordonnees_2);
        Assert.assertNotNull(localisation);

        localisation = listeGarePresenter.traductionCoordonneesEnLocalisation(coordonnees_3);
        Assert.assertNull(localisation);
    }

    @Test
    public void traductionCoordonneesEnLocalisation_coordonneesWithLenghtEqualToTwo(){
        String[] coordonnees = new String[2];
        coordonnees[0] = "0.4555441";
        coordonnees[1] = "-2.5666998";
        Localisation localisation = listeGarePresenter.traductionCoordonneesEnLocalisation(coordonnees);
        Assert.assertNotNull(localisation);
        Assert.assertNotNull(localisation.getLatitude());
        Assert.assertNotNull(localisation.getLongitude());
        Assert.assertEquals(0.4555441f, localisation.getLatitude(), 0.0001);
        Assert.assertEquals(-2.5666998f, localisation.getLongitude(), 0.0001);
    }

    @Test
    public void traductionCoordonneesEnLocalisation_coordonneesArrayWithAlphabeticalCharacter(){
        String[] coordonnees = new String[2];
        coordonnees[0] = "0.4555j441";
        coordonnees[1] = "gfdscc";
        Localisation localisation = listeGarePresenter.traductionCoordonneesEnLocalisation(coordonnees);
        Assert.assertNull(localisation);
    }

}
