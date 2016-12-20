package com.example.firas.i3s_android.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.model.Pointage;
import com.example.firas.i3s_android.model.Ronde;
import com.example.firas.i3s_android.model.Rondes;
import com.example.firas.i3s_android.model.Services;
import com.example.firas.i3s_android.utils.DialogUtils;
import com.example.firas.i3s_android.utils.MySingleton;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.danlew.android.joda.DateUtils;

import org.joda.time.DateTime;

import java.io.IOException;

/**
 * Created by firas on 14/10/2016.
 */

public class SuiviRondeFragment extends BaseFragment {
    Ronde ronde;
    JsonAdapter<Rondes> jsonAdapter;
    Moshi moshi;
    Button btn_main_courante, btn_terminer_ronde;
    ImageView imgv_suiv_ronde1, imgv_suiv_ronde2, imgv_suiv_ronde3, imgv_suiv_ronde4, imgv_suiv_ronde5;
    int indexround = -1;
    DateTime now;
    String time;
    Rondes rondes;
    int indiceronda;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.suivi_ronde_fragment, container, false);
        baseActivity.getSupportActionBar().show();
        indiceronda=getArguments().getInt("roundindice");
        btn_main_courante = (Button) view.findViewById(R.id.btn_mainCourante);
        btn_terminer_ronde = (Button) view.findViewById(R.id.btn_terminerRonde);
        imgv_suiv_ronde1 = (ImageView) view.findViewById(R.id.imgv_suiv_ronde1);
        imgv_suiv_ronde2 = (ImageView) view.findViewById(R.id.imgv_suiv_ronde2);
        imgv_suiv_ronde3 = (ImageView) view.findViewById(R.id.imgv_suiv_ronde3);
        imgv_suiv_ronde4 = (ImageView) view.findViewById(R.id.imgv_suiv_ronde4);
        imgv_suiv_ronde5 = (ImageView) view.findViewById(R.id.imgv_suiv_ronde5);
        now = DateTime.now();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     time= DateUtils.formatDateTime(baseActivity, now, DateUtils.FORMAT_SHOW_TIME);

        btn_terminer_ronde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ronde != null) {
                    if (ronde.getPointage1().getType().toString().equalsIgnoreCase("g"))

                    {

                        ronde.setPointage1(new Pointage("r", true,time));
                    }

                    if (ronde.getPointage2().getType().toString().equalsIgnoreCase("g"))

                    {

                        ronde.setPointage2(new Pointage("r", true,time));
                    }
                    if (ronde.getPointage3().getType().toString().equalsIgnoreCase("g"))

                    {

                        ronde.setPointage3(new Pointage("r", true,time));
                    }
                    if (ronde.getPointage4().getType().toString().equalsIgnoreCase("g"))

                    {

                        ronde.setPointage4(new Pointage("r", true,time));
                    }

                    if (ronde.getPointage5().getType().toString().equalsIgnoreCase("g"))

                    {

                        ronde.setPointage5(new Pointage("r", true,time));
                    }


                } else {
                    Log.e("suiviRndeFragment", "ronde null !!!!");
                }
                rondes.getRondeList().set(indiceronda,ronde);
                String json = jsonAdapter.toJson(rondes);
                MySingleton.getInstance().writeFile("ronde.json", baseActivity, json);
                DialogUtils.showConfirmDialog(baseActivity,
                        getResources().getString(R.string.app_name),
                        getResources().getString(R.string.terminer_ronde_msg_dialog),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                baseActivity.replaceFragment(new ListeRondeFragment(), R.id.frame);
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
        btn_main_courante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indexround != -1) {

                    DialogUtils.showConfirmDialog(baseActivity,
                            getResources().getString(R.string.app_name),
                            getResources().getString(R.string.autorisation_camera),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("indexround", indexround);
                                    bundle.putInt("roundindice",indiceronda);
                                    RondeScannerFragment rondeScannerFragment = new RondeScannerFragment();
                                    rondeScannerFragment.setArguments(bundle);
                                    baseActivity.replaceFragment(rondeScannerFragment, R.id.frame);

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





                } else {
                    Toast.makeText(baseActivity, "svp selectionner une ronde", Toast.LENGTH_SHORT).show();
                }

            }
        });
        imgv_suiv_ronde1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexround = 0;
                Toast.makeText(baseActivity, "t'as selectionné scan 1", Toast.LENGTH_SHORT).show();
            }
        });
        imgv_suiv_ronde2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexround = 1;
                Toast.makeText(baseActivity, "t'as selectionné scan 2", Toast.LENGTH_SHORT).show();
            }
        });
        imgv_suiv_ronde3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexround = 2;
                Toast.makeText(baseActivity, "t'as selectionné scan 3", Toast.LENGTH_SHORT).show();
            }
        });
        imgv_suiv_ronde4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexround = 3;
                Toast.makeText(baseActivity, "t'as selectionné scan 4", Toast.LENGTH_SHORT).show();
            }
        });
        imgv_suiv_ronde5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexround = 4;
                Toast.makeText(baseActivity, "t'as selectionné scan 5", Toast.LENGTH_SHORT).show();
            }
        });
        readJsonData();
        if (ronde.getPointage1().getType().toString().equalsIgnoreCase("v"))

        {
            imgv_suiv_ronde1.setImageResource(R.drawable.success);
        } else if (ronde.getPointage1().getType().toString().equalsIgnoreCase("r")) {
            imgv_suiv_ronde1.setImageResource(R.drawable.error);
        }


        if (ronde.getPointage2().getType().toString().equalsIgnoreCase("v"))

        {
            imgv_suiv_ronde2.setImageResource(R.drawable.success);
        } else if (ronde.getPointage2().getType().toString().equalsIgnoreCase("r")) {
            imgv_suiv_ronde2.setImageResource(R.drawable.error);
        }

        if (ronde.getPointage3().getType().toString().equalsIgnoreCase("v"))

        {
            imgv_suiv_ronde3.setImageResource(R.drawable.success);
        } else if (ronde.getPointage3().getType().toString().equalsIgnoreCase("r")) {
            imgv_suiv_ronde3.setImageResource(R.drawable.error);
        }

        if (ronde.getPointage4().getType().toString().equalsIgnoreCase("v"))

        {
            imgv_suiv_ronde4.setImageResource(R.drawable.success);
        } else if (ronde.getPointage4().getType().toString().equalsIgnoreCase("r")) {
            imgv_suiv_ronde4.setImageResource(R.drawable.error);
        }

        if (ronde.getPointage5().getType().toString().equalsIgnoreCase("v"))

        {
            imgv_suiv_ronde5.setImageResource(R.drawable.success);
        } else if (ronde.getPointage5().getType().toString().equalsIgnoreCase("r")) {
            imgv_suiv_ronde5.setImageResource(R.drawable.error);
        }


    }

    @Override
    public boolean onBackPressed() {

        if (ronde != null)
            return false;
        return true;
    }

    public void readJsonData() {


        moshi = new Moshi.Builder().build();
        jsonAdapter = moshi.adapter(Rondes.class);

        try {
            rondes = jsonAdapter.fromJson(MySingleton.getInstance().readFile("ronde.json", baseActivity));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ronde=rondes.getRondeList().get(indiceronda);
        Log.e("ronde", jsonAdapter.toJson(rondes));
        if (ronde == null) {
            Log.e("Pointage1", "null");
            ronde = new Ronde();
            ronde.setPointage1(new Pointage("g", false,time));
            ronde.setPointage2(new Pointage("g", false,time));
            ronde.setPointage3(new Pointage("g", false,time));
            ronde.setPointage4(new Pointage("g", false,time));
            ronde.setPointage5(new Pointage("g", false,time));
            String json = jsonAdapter.toJson(rondes);
            MySingleton.getInstance().writeFile("ronde.json", baseActivity, json);
        }

        if (ronde.getPointage1() != null)
            Log.e("pointage", ronde.getPointage1().toString());
    }
}


