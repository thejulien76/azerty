package com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.remote;

import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Records;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.api.RequestInterface;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 *
 * Acces a la donnée externe
 *
 * @author PNVE12891
 * @version 1.0
 * @since 20/03/2018
 */

public class ListeGareRemoteDataSource {

    private RequestInterface requestInterface;

    @Inject
    public ListeGareRemoteDataSource(RequestInterface requestInterface) {
        this.requestInterface = requestInterface;
    }

    public Single<Records> recupererGaresADistance() {
        return requestInterface.getRecords();
    }

}
