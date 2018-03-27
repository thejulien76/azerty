package com.sncf.android.internal.poctemplatemvpandroid.listedegares.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sncf.android.internal.poctemplatemvpandroid.listedegares.data.model.Gare;

import java.util.List;

/**
 *
 * Adapter pour la vue Liste Gare.
 *
 * @author PZII11871
 * @version 1.0
 * @since 09/03/2018
 */

public class ListGareAdapter extends BaseAdapter {

    private List<Gare> listeGares;
    private Context context;
    private ListeGareEvenementListener listener;

    public ListGareAdapter(List<Gare> listeGares, Context context, ListeGareEvenementListener listener) {
        this.listeGares = listeGares;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return listeGares.size();
    }

    @Override
    public Gare getItem(int position) {
        return listeGares.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GareViewHolder convertedView;
        if (convertView == null) {
            convertedView = new GareViewHolder(context);
        } else {
            convertedView = (GareViewHolder) convertView;
        }
        convertedView.setData(getItem(position), listener);
        return convertedView;
    }
}
