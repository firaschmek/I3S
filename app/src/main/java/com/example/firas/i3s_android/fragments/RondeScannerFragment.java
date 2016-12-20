package com.example.firas.i3s_android.fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.model.Pointage;
import com.example.firas.i3s_android.model.Ronde;
import com.example.firas.i3s_android.model.Rondes;
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

public class RondeScannerFragment extends BaseFragment implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    JsonAdapter<Rondes> jsonAdapter;
    Ronde ronde;
    Moshi moshi;
Rondes  rondes;
    DateTime now;
    String time;
    int roundindice;
    ImageView back_simple_scanner;
    ViewGroup containerfrag;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simple_scanner_fragment, container, false);
        back_simple_scanner= (ImageView) view.findViewById(R.id.back_simple_scanner);
        back_simple_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle      bundle1=new Bundle();
                bundle1.putInt("roundindice",roundindice);
                SuiviRondeFragment     suiviRondeFragment=new SuiviRondeFragment();

                suiviRondeFragment.setArguments(bundle1);
         containerfrag.removeAllViews();
                baseActivity.replaceFragment(suiviRondeFragment, R.id.frame);

            }
        });
        containerfrag=container;
        baseActivity.getSupportActionBar().hide();
        mScannerView = new ZXingScannerView(getActivity());
        now = DateTime.now();
        moshi = new Moshi.Builder().build();
        jsonAdapter = moshi.adapter(Rondes.class);
roundindice=getArguments().getInt("roundindice");
        ronde = new Ronde();
        time= DateUtils.formatDateTime(baseActivity, now, DateUtils.FORMAT_SHOW_TIME);
        container.addView(mScannerView);
        containerfrag=container;
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

        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        int indexround = getArguments().getInt("indexround");
        if (MySingleton.getInstance().readFile("ronde.json", baseActivity) == null) {
            MySingleton.getInstance().writeFile("ronde.json", baseActivity, "");
        }
        try {
            rondes = jsonAdapter.fromJson(MySingleton.getInstance().readFile("ronde.json", baseActivity));
        } catch (IOException e) {
            e.printStackTrace();
        }
int indiceronda=getArguments().getInt("roundindice");
        ronde=rondes.getRondeList().get(indiceronda);
        Toast.makeText(baseActivity, rawResult.getText(), Toast.LENGTH_SHORT).show();
        switch (indexround) {
            case 0:
                if (rawResult.getText().equalsIgnoreCase("1324")) {
                    Log.e("1234", "true");
                    if (ronde.getPointage1().getType().toString().equalsIgnoreCase("g"))

                    {
                        Log.e("1234", "true2");
                        ronde.setPointage1(new Pointage("v", true,time));

                    }
                }
                break;
            case 1:
                if (rawResult.getText().equalsIgnoreCase("A")) {
                    if (ronde.getPointage1().getType().toString().equalsIgnoreCase("g"))

                    {

                        ronde.setPointage1(new Pointage("r", true,time));


                        ronde.setPointage2(new Pointage("v", true,time));


                    } else {
                        ronde.setPointage2(new Pointage("v", true,time));
                    }
                }
                break;
            case 2:
                if (rawResult.getText().equalsIgnoreCase("B")) {
                    if (ronde.getPointage1().getType().toString().equalsIgnoreCase("g"))

                    {

                        ronde.setPointage1(new Pointage("r", true,time));


                        ronde.setPointage3(new Pointage("v", true,time));


                    } else {
                        ronde.setPointage3(new Pointage("v", true,time));
                    }
                    if (ronde.getPointage2().getType().toString().equalsIgnoreCase("g"))

                    {

                        ronde.setPointage2(new Pointage("r", true,time));


                        ronde.setPointage3(new Pointage("v", true,time));


                    } else {
                        ronde.setPointage3(new Pointage("v", true,time));
                    }
                }
                break;
            case 3:
                if (rawResult.getText().equalsIgnoreCase("C")) {
                    if (ronde.getPointage1().getType().toString().equalsIgnoreCase("g"))

                    {

                        ronde.setPointage1(new Pointage("r", true,time));


                        ronde.setPointage4(new Pointage("v", true,time));


                    } else {
                        ronde.setPointage4(new Pointage("v", true,time));
                    }
                    if (ronde.getPointage2().getType().toString().equalsIgnoreCase("g"))

                    {

                        ronde.setPointage2(new Pointage("r", true,time));


                        ronde.setPointage4(new Pointage("v", true,time));


                    } else {
                        ronde.setPointage4(new Pointage("v", true,time));
                    }

                    if (ronde.getPointage3().getType().toString().equalsIgnoreCase("g"))

                    {

                        ronde.setPointage3(new Pointage("r", true,time));


                        ronde.setPointage4(new Pointage("v", true,time));


                    } else {
                        ronde.setPointage4(new Pointage("v", true,time));
                    }
                }
                break;
            case 4:
                if (rawResult.getText().equalsIgnoreCase("D")) {
                    if (ronde.getPointage1().getType().toString().equalsIgnoreCase("g"))

                    {

                        ronde.setPointage1(new Pointage("r", true,time));


                        ronde.setPointage5(new Pointage("v", true,time));


                    } else {
                        ronde.setPointage5(new Pointage("v", true,time));
                    }
                    if (ronde.getPointage2().getType().toString().equalsIgnoreCase("g"))

                    {

                        ronde.setPointage2(new Pointage("r", true,time));


                        ronde.setPointage5(new Pointage("v", true,time));


                    } else {
                        ronde.setPointage5(new Pointage("v", true,time));
                    }

                    if (ronde.getPointage3().getType().toString().equalsIgnoreCase("g"))

                    {

                        ronde.setPointage3(new Pointage("r", true,time));


                        ronde.setPointage5(new Pointage("v", true,time));


                    } else {
                        ronde.setPointage5(new Pointage("v", true,time));
                    }
                    if (ronde.getPointage4().getType().toString().equalsIgnoreCase("g"))

                    {

                        ronde.setPointage4(new Pointage("r", true,time));
                        ronde.setPointage5(new Pointage("v", true,time));


                    } else {
                        ronde.setPointage5(new Pointage("v", true,time));
                    }
                }

        }

        ronde.setRonde_time(time);
        rondes.getRondeList().set(indiceronda,ronde);
        Log.e("rondescan", jsonAdapter.toJson(rondes));
        Log.e("getPointage1", ronde.getPointage1().getType());

        String json = jsonAdapter.toJson(rondes);
        MySingleton.getInstance().writeFile("ronde.json", baseActivity, json);
  Bundle      bundle1=new Bundle();
        bundle1.putInt("roundindice",roundindice);
   SuiviRondeFragment     suiviRondeFragment=new SuiviRondeFragment();
        suiviRondeFragment.setArguments(bundle1);
        containerfrag.removeAllViews();
        baseActivity.replaceFragment(suiviRondeFragment, R.id.frame);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(RondeScannerFragment.this);
            }
        }, 2000);

    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    public void readAndWriteService() {


    }
}
