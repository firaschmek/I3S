package com.example.firas.i3s_android.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.utils.DialogUtils;

/**
 * Created by firas on 14/10/2016.
 */

public class SOSFragment extends  BaseFragment{


Button btn_sos;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.sos_fragment, container, false);
        btn_sos= (Button) view.findViewById(R.id.btn_sos);
        btn_sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.showConfirmDialog(baseActivity,
                        getResources().getString(R.string.app_name),
                        getResources().getString(R.string.sos_msg_confirmation),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        },
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        },
                        R.string.dialog_OK,
                        R.string.dialog_CANCEL
                );
            }
        });

        return view;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
