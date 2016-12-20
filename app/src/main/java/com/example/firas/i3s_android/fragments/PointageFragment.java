package com.example.firas.i3s_android.fragments;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.activities.HomeActivity;
import com.example.firas.i3s_android.model.Service;
import com.example.firas.i3s_android.model.Services;
import com.example.firas.i3s_android.utils.DialogUtils;
import com.example.firas.i3s_android.utils.FontManager;
import com.example.firas.i3s_android.utils.MySingleton;
import com.google.zxing.Result;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.danlew.android.joda.DateUtils;

import org.joda.time.DateTime;

import java.io.IOException;
import java.io.InputStream;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by firas on 10/10/2016.
 */

public class PointageFragment extends BaseFragment {
    private ZXingScannerView mScannerView;
    DateTime now;
    private LinearLayout qrCameraLayout;
    private TextView prise_service_txtv, fin_service_txtv;
    private Services services;
    private Service prise_service, fin_service;
    JsonAdapter<Services> jsonAdapter;
    Moshi moshi;
    ImageView btn_pointer;
public static final String TAG="PointageFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pointage, container, false);
        Log.e(TAG,"entered");
        HomeActivity homeActivity=new HomeActivity();
        baseActivity.getSupportActionBar().show();
       //baseActivity.setSupportActionBar(homeActivity.getToolbar());
        prise_service_txtv = (TextView) view.findViewById(R.id.prise_service_txtv);
        fin_service_txtv = (TextView) view.findViewById(R.id.fin_service_txtv);
        btn_pointer= (ImageView) view.findViewById(R.id.btn_pointer);
        now = DateTime.now();
        readJsonDataAndColorText();
        return view;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        Typeface iconFont = FontManager.getTypeface(baseActivity.getApplicationContext(), FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(view.findViewById(R.id.pointage_container), iconFont);
        btn_pointer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((prise_service != null) && (fin_service != null)) {
                    Toast.makeText(baseActivity, "Pointage terminé pour aujourd'hui", Toast.LENGTH_SHORT).show();
                }
                else

                    DialogUtils.showConfirmDialog(baseActivity,
                            getResources().getString(R.string.app_name),
                            getResources().getString(R.string.autorisation_camera),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                baseActivity.replaceFragment(new SimpleScannerFragment(),R.id.frame);

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





    }


    public void readJsonDataAndColorText() {
        if (MySingleton.getInstance().readFile("pointage_service.json", baseActivity) == null) {
            MySingleton.getInstance().writeFile("pointage_service.json", baseActivity, "");
        }
        moshi = new Moshi.Builder().build();
        jsonAdapter = moshi.adapter(Services.class);
        services = new Services();
        try {
            services = jsonAdapter.fromJson(MySingleton.getInstance().readFile("pointage_service.json", baseActivity));
        } catch (IOException e) {
            e.printStackTrace();
        }
        prise_service = services.getPrise_service();
        fin_service = services.getFin_service();
        if (prise_service != null) {
            if (prise_service.isEnabled()) {
                prise_service_txtv.setTextColor(Color.WHITE);
                prise_service_txtv.setBackgroundColor(Color.GREEN);
                prise_service_txtv.setText(prise_service.getTime());
            }
        }
        if (fin_service != null) {
            if (fin_service.isEnabled()) {
                fin_service_txtv.setTextColor(Color.WHITE);
                fin_service_txtv.setBackgroundColor(Color.GREEN);
                fin_service_txtv.setText(fin_service.getTime());
            }
        }



    }

    @Override
    public void onResume() {
        super.onResume();
        readJsonDataAndColorText();
    }

    @Override
    public boolean onBackPressed() {

        return false;
    }




}
