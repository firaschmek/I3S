package com.example.firas.i3s_android.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.model.Service;
import com.example.firas.i3s_android.model.Services;
import com.example.firas.i3s_android.utils.DialogUtils;
import com.example.firas.i3s_android.utils.MarshMallowPermission;
import com.example.firas.i3s_android.utils.MySingleton;
import com.google.zxing.Result;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.danlew.android.joda.DateUtils;

import org.joda.time.DateTime;

import java.io.IOException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScannerFragment extends BaseFragment implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private Services services;
    private Service prise_service, fin_service;
    JsonAdapter<Services> jsonAdapter;
    Moshi moshi;
    DateTime now;
    private Toolbar toolbar;
    ImageView back_simple_scanner;
    ViewGroup containerfrag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simple_scanner_fragment, container, false);
        containerfrag=container;
        back_simple_scanner= (ImageView) view.findViewById(R.id.back_simple_scanner);
        back_simple_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerfrag.removeAllViews();
                baseActivity.replaceFragment(new PointageFragment(),R.id.frame);
            }
        });
        mScannerView = new ZXingScannerView(getActivity());
        now = DateTime.now();
       baseActivity.getSupportActionBar().hide();
       /* toolbar = (Toolbar)view. findViewById(R.id.toolbar_scanner);
        baseActivity.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseActivity.replaceFragment(new PointageFragment(),R.id.frame);
            }
        });*/
        moshi = new Moshi.Builder().build();
        jsonAdapter = moshi.adapter(Services.class);
        services = new Services();
       container.addView(mScannerView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        MarshMallowPermission marshMallowPermission = new MarshMallowPermission(baseActivity);
        if (!marshMallowPermission.checkPermissionForCamera()) {
            Log.e("MARSHALLOW","no permission !!");
            marshMallowPermission.requestPermissionForCamera();

            mScannerView.startCamera();

        } else {
            mScannerView.startCamera();}
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }

    @Override
    public void handleResult(Result rawResult) {


        if (MySingleton.getInstance().readFile("pointage_service.json", baseActivity) == null) {
            MySingleton.getInstance().writeFile("pointage_service.json", baseActivity, "");
        }

        DialogUtils.showConfirmDialog(baseActivity,
                getResources().getString(R.string.app_name),
                getResources().getString(R.string.msg_confirmation_pointage),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DialogUtils.showConfirmDialog(baseActivity,
                                getResources().getString(R.string.app_name),
                                getResources().getString(R.string.msg_activation_gps),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        readAndWriteService();
                                    /*    Intent i = new
                                                Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                        startActivity(i);*/
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
                },
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                },
                R.string.dialog_OK,
                R.string.dialog_CANCEL
        );
baseActivity.replaceFragment(new PointageFragment(),R.id.frame);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(SimpleScannerFragment.this);
            }
        }, 2000);

    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
    public  void readAndWriteService()
    {


        try {
            services = jsonAdapter.fromJson(MySingleton.getInstance().readFile("pointage_service.json", baseActivity));
        } catch (IOException e) {
            e.printStackTrace();
        }
        prise_service = services.getPrise_service();
        fin_service = services.getFin_service();
        if (prise_service == null) {
            Log.e("prise_service == null", (prise_service == null) + "");
            prise_service = new Service(DateUtils.formatDateTime(baseActivity, now, DateUtils.FORMAT_SHOW_TIME), true);
            services.setPrise_service(prise_service);
            String json = jsonAdapter.toJson(services);
            MySingleton.getInstance().writeFile("pointage_service.json", baseActivity, json);

        } else if (fin_service == null) {
            Log.e("fin_service == null", (fin_service == null) + "");
            fin_service = new Service(DateUtils.formatDateTime(baseActivity, now, DateUtils.FORMAT_SHOW_TIME), true);
            services.setFin_service(fin_service);
            String json = jsonAdapter.toJson(services);
            Log.e("JSON", json);
            MySingleton.getInstance().writeFile("pointage_service.json", baseActivity, json);

        }
        containerfrag.removeAllViews();
        baseActivity.replaceFragment(new PointageFragment(), R.id.frame);

    }
}
