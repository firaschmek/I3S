package com.example.firas.i3s_android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.interfas.RondeRecyclerListener;
import com.example.firas.i3s_android.model.Ronde;

import java.util.List;

/**
 * Created by firas on 14/10/2016.
 */

public class RondeAdapter extends RecyclerView.Adapter<RondeHolder> {
    List<Ronde> liste_ronde;
    RondeRecyclerListener rondeRecyclerListener;

    public RondeAdapter(List<Ronde> liste_ronde, RondeRecyclerListener rondeRecyclerListener) {
        this.liste_ronde = liste_ronde;
        this.rondeRecyclerListener = rondeRecyclerListener;
    }

    @Override
    public RondeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ronde_item, parent, false);
        RondeHolder rondeHolder= new RondeHolder(v, rondeRecyclerListener);
        return rondeHolder;
    }

    @Override
    public void onBindViewHolder(RondeHolder holder, int position) {
holder.updateText(liste_ronde.get(position));
        holder.updateScan(liste_ronde.get(position));
    }

    @Override
    public int getItemCount() {
        return liste_ronde.size();
    }
}
