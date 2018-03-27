package com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.api;

import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Records;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 *
 * Interface contenant les requêtes faite sur l'API.
 *
 * @author PZII11871
 * @version 1.0
 * @since 20/03/2018
 */

public interface RequestInterface {

    /**
     *  Retourne une liste d'objet Records.
     * @return
     */
    @GET("api/records/1.0/search/?dataset=liste-des-gares&facet=fret&facet=voyageurs&facet=code_ligne&facet=departement")
    Single<Records> getRecords();
}
