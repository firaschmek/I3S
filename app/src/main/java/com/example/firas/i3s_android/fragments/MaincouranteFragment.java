package com.example.firas.i3s_android.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.activities.HomeActivity;
import com.example.firas.i3s_android.interfas.PictureTakenCallback;

/**
 * Created by firas on 15/10/2016.
 */

public class MaincouranteFragment extends BaseFragment {
    ImageView take_picture_imgv;
    Button pict;
   public static final int CAMERA_REQUEST = 1888;
Bitmap photo=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_courante_fragment, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.type_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(baseActivity,
                R.array.type_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        return view;
    }


    @Override
    public boolean onBackPressed() {
        return false;
    }


}
