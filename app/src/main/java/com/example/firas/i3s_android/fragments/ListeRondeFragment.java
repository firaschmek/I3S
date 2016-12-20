package com.example.firas.i3s_android.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.adapter.RondeAdapter;
import com.example.firas.i3s_android.interfas.RondeRecyclerListener;
import com.example.firas.i3s_android.model.Ronde;
import com.example.firas.i3s_android.model.Rondes;
import com.example.firas.i3s_android.utils.MySingleton;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by firas on 14/10/2016.
 */

public class ListeRondeFragment extends BaseFragment implements RondeRecyclerListener{
    RecyclerView liste_ronde_recycler;
    RondeAdapter rondeAdapter;
    List<Ronde> rondeList;
    Ronde ronde;
    Rondes rondes;
    JsonAdapter<Rondes> jsonAdapter;
    Moshi moshi;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.liste_ronde_fragment, container, false);
       liste_ronde_recycler= (RecyclerView) view.findViewById(R.id.liste_ronde_recycler);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (MySingleton.getInstance().readFile("ronde.json", baseActivity) == null) {
            MySingleton.getInstance().writeFile("ronde.json", baseActivity, "");
        }

        moshi = new Moshi.Builder().build();
        jsonAdapter = moshi.adapter(Rondes.class);

        try {
            rondes = jsonAdapter.fromJson(MySingleton.getInstance().readFile("ronde.json", baseActivity));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rondeList=new ArrayList<>();
        rondeList=rondes.getRondeList();

        LinearLayoutManager ronde_recycler_view_llm = new LinearLayoutManager(getContext());
     liste_ronde_recycler .setLayoutManager(ronde_recycler_view_llm);
        liste_ronde_recycler.setHasFixedSize(true);
        rondeAdapter=new RondeAdapter(rondeList,this);
        liste_ronde_recycler.setAdapter(rondeAdapter);
        rondeAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onItemClicked(View v, int position) {
        Bundle bundle1=new Bundle();
        bundle1.putInt("roundindice",position);
        SuiviRondeFragment suiviRondeFragment=new SuiviRondeFragment();
        suiviRondeFragment.setArguments(bundle1);
        baseActivity.replaceFragment(suiviRondeFragment,R.id.frame);
    }
}
