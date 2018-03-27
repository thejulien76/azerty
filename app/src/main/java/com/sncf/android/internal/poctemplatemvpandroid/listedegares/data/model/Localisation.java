package com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 *
 * modèle : Localisation.
 *
 * @author PZII11871
 * @version 1.0
 * @since 20/03/2018
 */

@Entity(tableName = "localisations")
public class Localisation implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name ="longitude")
    private float longitude;

    @ColumnInfo(name ="latitude")
    private float latitude;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

}
