package com.example.firas.i3s_android.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.activities.HomeActivity;
import com.example.firas.i3s_android.model.Pointage;
import com.example.firas.i3s_android.model.Ronde;
import com.example.firas.i3s_android.model.Rondes;
import com.example.firas.i3s_android.model.Service;
import com.example.firas.i3s_android.model.Services;
import com.example.firas.i3s_android.utils.MySingleton;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import net.danlew.android.joda.DateUtils;

import org.joda.time.DateTime;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_WORLD_READABLE;

/**
 * Created by firas on 08/10/2016.
 */

public class AuthentificationFragment extends  BaseFragment {

Button btn_login;
    EditText identifiant_login_form,pwd_login_form;
public static  final String TAG="AuthenFragment";
    TextView cgv_login_form,pwd_oublie1_login_form ,pwd_oublie2_login_form,montrons_legales;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_authentification, container, false);
       btn_login= (Button) view.findViewById(R.id.btn_login);
        cgv_login_form= (TextView) view.findViewById(R.id.cgv_login_form);
        pwd_oublie1_login_form= (TextView) view.findViewById(R.id.pwd_oublie1_login_form);
    //    pwd_oublie2_login_form= (TextView) view.findViewById(R.id.pwd_oublie2_login_form);
        montrons_legales= (TextView) view.findViewById(R.id.montrons_legales);

        identifiant_login_form= (EditText) view.findViewById(R.id.identifiant_login_form);
        pwd_login_form= (EditText) view.findViewById(R.id.pwd_login_form);



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                identifiant_login_form.setText(null);
                pwd_login_form.setText(null);
                Intent intent=new Intent(baseActivity, HomeActivity.class);
                baseActivity.startActivity(intent);

            }
        });
        cgv_login_form.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     baseActivity.replaceFragment(new CGVFragment(),R.id.activity_main_FrameLayout);
                    }
                }
        );
      montrons_legales.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseActivity.replaceFragment(new MTLFragment(),R.id.activity_main_FrameLayout);
                    }
                }
        );
        pwd_oublie1_login_form.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseActivity.replaceFragment(new MTLFragment(),R.id.activity_main_FrameLayout);
                    }
                }
        );
     /*   pwd_oublie2_login_form.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseActivity.replaceFragment(new MTLFragment(),R.id.activity_main_FrameLayout);
                    }
                }
        );*/

        // creer fichier json pour ronde
        Pointage pointage1,pointage2,pointage3,pointage4,pointage5;
        pointage1=new Pointage("g",false,"--/--");
        pointage2=new Pointage("g",false,"--/--");
        pointage3=new Pointage("g",false,"--/--");
        pointage4=new Pointage("g",false,"--/--");
        pointage5=new Pointage("g",false,"--/--");
        Ronde ronde1,ronde2,ronde3,ronde4;
        ronde1=new Ronde(pointage1,pointage2,pointage3,pointage4,pointage5,"Ronde Zone A");
        ronde2=new Ronde(pointage1,pointage2,pointage3,pointage4,pointage5,"Ronde Zone B");
        ronde3=new Ronde(pointage1,pointage2,pointage3,pointage4,pointage5,"Ronde Zone C");
        List<Ronde> rondeList=new ArrayList<>();
        rondeList.add(ronde1);
        rondeList.add(ronde2);
        rondeList.add(ronde3);
        Rondes rondes=new Rondes(rondeList);
        JsonAdapter<Rondes> jsonAdapter;
        Moshi moshi;
        moshi = new Moshi.Builder().build();
        jsonAdapter = moshi.adapter(Rondes.class);


        String json,json2;

            json = jsonAdapter.toJson(rondes);



            MySingleton.getInstance().writeFile("ronde.json", baseActivity, json);



        Log.e(TAG,"here1");
        try {
            rondes = jsonAdapter.fromJson(MySingleton.getInstance().readFile("ronde.json", baseActivity));
            Log.e(TAG,"here2");
        } catch (IOException e) {
            e.printStackTrace();
        }
        json2=jsonAdapter.toJson(rondes);
        Log.e(TAG,"here3");
        Log.e(TAG,(rondes==null)+"");
        Log.e(TAG,(rondes.getRondeList()==null)+"");
        Log.e(TAG,"here4");
 Log.e(TAG,json2);

    }

    @Override
    public boolean onBackPressed() {
        baseActivity.finish();
        return true;
    }


}
