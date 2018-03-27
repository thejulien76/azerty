package com.sncf.android.internal.poctemplatemvpandroid.listedegares.api;

import android.app.Application;

import com.google.gson.Gson;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.api.RequestInterface;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Records;

import java.io.IOException;
import java.nio.charset.Charset;

import io.reactivex.Single;
import io.reactivex.exceptions.Exceptions;
import okio.Buffer;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.Calls;
import retrofit2.mock.MockRetrofit;

/**
 * Copyright (c) 2016 - Société Nationale des Chemins de fer Français
 * <p/>
 * Toute reproduction ou diffusion intégrale ou partielle par quelque procédé que ce soit ne peut
 * être faite sans l'autorisation préalable de la SNCF.
 *
 * @author PNVE12891
 * @version 1.0.0-1
 * @since 15/03/2018
 */
public class MockRequestInterface implements RequestInterface {

    private static final String LISTE_DE_GARES_JSON = "liste_de_gares.json" ;
    private final Application app;
    private final BehaviorDelegate<RequestInterface> behaviorDelegate;
    private final Gson gson;

    public MockRequestInterface(Application app, MockRetrofit mockRetrofit, Gson gson) {
        this.app = app;
        this.behaviorDelegate = mockRetrofit.create(RequestInterface.class);
        this.gson = gson;
    }

    @Override
    public Single<Records> getRecords() {
        try {
            Buffer buffer = new Buffer();
            buffer.readFrom(app.getAssets().open(LISTE_DE_GARES_JSON));
            String json = buffer.readString(Charset.defaultCharset());
            Records records = gson.fromJson(json, Records.class);

            return behaviorDelegate.returning(Calls.response(records)).getRecords();
        } catch (IOException e) {
            throw Exceptions.propagate(e);
        }
    }

}
