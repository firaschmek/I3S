package com.example.firas.i3s_android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.interfas.RondeRecyclerListener;
import com.example.firas.i3s_android.model.Ronde;

/**
 * Created by firas on 14/10/2016.
 */

public class RondeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView ronde_name,ronde_time;
    ImageView imgv_suiv_ronde1,imgv_suiv_ronde2,imgv_suiv_ronde3,imgv_suiv_ronde4,imgv_suiv_ronde5;
    RondeRecyclerListener rondeRecyclerListener;

    public RondeHolder(View itemView , RondeRecyclerListener rondeRecyclerListener) {
        super(itemView);
        this.rondeRecyclerListener=rondeRecyclerListener;
        ronde_name= (TextView) itemView.findViewById(R.id.ronde_name);
        ronde_time= (TextView) itemView.findViewById(R.id.ronde_time);
        imgv_suiv_ronde1= (ImageView) itemView.findViewById(R.id.imgv_suiv_ronde1);
        imgv_suiv_ronde2= (ImageView) itemView.findViewById(R.id.imgv_suiv_ronde2);
        imgv_suiv_ronde3= (ImageView) itemView.findViewById(R.id.imgv_suiv_ronde3);
        imgv_suiv_ronde4= (ImageView) itemView.findViewById(R.id.imgv_suiv_ronde4);
        imgv_suiv_ronde5= (ImageView) itemView.findViewById(R.id.imgv_suiv_ronde5);
itemView.setOnClickListener(this);
    }
    public void updateText(Ronde ronde) {
        ronde_name.setText(ronde.getRonde_name());
        ronde_time.setText(ronde.getRonde_time());
    }

    public void updateScan(Ronde ronde )
    {if(ronde!=null)
    {
        if (ronde.getPointage1().getType().toString().equalsIgnoreCase("v"))

        {
            imgv_suiv_ronde1.setImageResource(R.drawable.success);
        }
        else if(ronde.getPointage1().getType().toString().equalsIgnoreCase("r"))
        {
            imgv_suiv_ronde1.setImageResource(R.drawable.error);
        }


        if (ronde.getPointage2().getType().toString().equalsIgnoreCase("v"))

        {
            imgv_suiv_ronde2.setImageResource(R.drawable.success);
        }
        else if(ronde.getPointage2().getType().toString().equalsIgnoreCase("r"))
        {
            imgv_suiv_ronde2.setImageResource(R.drawable.error);
        }

        if (ronde.getPointage3().getType().toString().equalsIgnoreCase("v"))

        {
            imgv_suiv_ronde3.setImageResource(R.drawable.success);
        }
        else if(ronde.getPointage3().getType().toString().equalsIgnoreCase("r"))
        {
            imgv_suiv_ronde3.setImageResource(R.drawable.error);
        }

        if (ronde.getPointage4().getType().toString().equalsIgnoreCase("v"))

        {
            imgv_suiv_ronde4.setImageResource(R.drawable.success);
        }
        else if(ronde.getPointage4().getType().toString().equalsIgnoreCase("r"))
        {
            imgv_suiv_ronde4.setImageResource(R.drawable.error);
        }

        if (ronde.getPointage5().getType().toString().equalsIgnoreCase("v"))

        {
            imgv_suiv_ronde5.setImageResource(R.drawable.success);
        }
        else if(ronde.getPointage5().getType().toString().equalsIgnoreCase("r"))
        {
            imgv_suiv_ronde5.setImageResource(R.drawable.error);
        }

    }}
    @Override
    public void onClick(View v) {
        if (rondeRecyclerListener != null)
            rondeRecyclerListener.onItemClicked(v, getAdapterPosition());
    }
}
