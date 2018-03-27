package com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 * modéle : Records.
 *
 * @author PZII11871
 * @version 1.0
 * @since 06/03/2018
 */

public class Records {

    @SerializedName("records")
    List<Record> recordList;

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }
}
