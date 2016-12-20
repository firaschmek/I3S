package com.example.firas.i3s_android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.interfas.DrawerRecyclerListener;

/**
 * Created by firas on 10/10/2016.
 */

public class DrawerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView drawer_item_textv;
    DrawerRecyclerListener drawerRecyclerListener;

    public DrawerHolder(View itemView,DrawerRecyclerListener drawerRecyclerListener) {
        super(itemView);
        this.drawerRecyclerListener=drawerRecyclerListener;
        drawer_item_textv= (TextView) itemView.findViewById(R.id.drawer_item_txtv);
        itemView.setOnClickListener(this);
    }

    public void updateText(String item_txt) {
        drawer_item_textv.setText(item_txt);
    }
    @Override
    public void onClick(View v) {
if(drawerRecyclerListener!=null)

{
    drawerRecyclerListener.onItemClicked(v,getAdapterPosition());
}
    }
}
