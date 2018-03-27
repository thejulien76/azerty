package com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 * modèle : Gare.
 *
 * @author PZII11871
 * @version 1.0
 * @since 20/03/2018
 */

@Entity(tableName = "gares", foreignKeys =
@ForeignKey(entity = Localisation.class,
        parentColumns = "id",
        childColumns = "id"))
public class Gare implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("departement")
    private String departement;
    @SerializedName("commune")
    private String commune;
    @SerializedName("libelle_gare")
    private String nomGare;

    @SerializedName("coordonnees_geographiques")
    @Ignore
    private String[] coordonnees;

    @Ignore
    private Localisation localisation;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getNomGare() {
        return nomGare;
    }

    public void setNomGare(String nomGare) {
        this.nomGare = nomGare;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public String[] getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(String[] coordonnees) {
        this.coordonnees = coordonnees;
    }
}
