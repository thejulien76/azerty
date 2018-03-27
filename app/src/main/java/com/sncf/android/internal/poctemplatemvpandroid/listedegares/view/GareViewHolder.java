package com.sncf.android.internal.poctemplatemvpandroid.listedegares.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sncf.android.internal.poctemplatemvpandroid.R;
import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Gare;

/**
 *
 * ViewHodler du modèle Gare (contient les items graphiques à afficher)
 *
 * @author PZII11871
 * @version 1.0
 * @since 15/03/2018
 */

public class GareViewHolder extends RelativeLayout {

    private TextView nomGare;
    private TextView communeGare;
    private TextView departementGare;
    private View layoutItem;

    /**
     * Constructeur
     * @param context
     */
    public GareViewHolder(Context context) {
        super(context);
        initView();
    }

    /**
     *
     * @param context
     * @param attrs
     */
    public GareViewHolder(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public GareViewHolder(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * Initialisation de la vue.
     */
    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_gare, this);
        nomGare = findViewById(R.id.nomGare);
        communeGare = findViewById(R.id.communeGare);
        departementGare = findViewById(R.id.departementGare);
        layoutItem = findViewById(R.id.layoutItem);
    }

    /**
     * Attacher la valeur
     * @param gare
     * @param listener
     */
    public void setData(Gare gare, ListeGareEvenementListener listener) {
        nomGare.setText(gare.getNomGare());
        communeGare.setText(gare.getCommune());
        departementGare.setText(gare.getDepartement());
        layoutItem.setOnClickListener(v -> listener.afficherLocalisation(gare));
    }
}
