package com.example.firas.i3s_android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.interfas.DrawerRecyclerListener;

import java.util.List;

/**
 * Created by firas on 10/10/2016.
 */

public class DrawerAdapter extends RecyclerView.Adapter<DrawerHolder> {

    List<String> drawer_item;
    DrawerRecyclerListener drawerRecyclerListener;

    public DrawerAdapter(List<String> drawer_item, DrawerRecyclerListener drawerRecyclerListener) {
        this.drawer_item = drawer_item;
        this.drawerRecyclerListener = drawerRecyclerListener;
    }

    @Override
    public DrawerHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drawer, parent, false);
        DrawerHolder drawerHolder = new DrawerHolder(v, drawerRecyclerListener);
        return drawerHolder;


    }

    @Override
    public void onBindViewHolder(DrawerHolder holder, int position) {
holder.updateText(drawer_item.get(position));
    }

    @Override
    public int getItemCount() {
        return drawer_item.size();
    }
}
