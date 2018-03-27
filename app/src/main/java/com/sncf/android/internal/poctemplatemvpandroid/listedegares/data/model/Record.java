package com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * modèle : Record.
 *
 * @author PZII11871
 * @version 1.0
 * @since 06/03/2018
 */
public class Record {

    @SerializedName("fields")
    private Gare gare;

    public Gare getGare() {
        return gare;
    }

    public void setGare(Gare gare) {
        this.gare = gare;
    }
}
