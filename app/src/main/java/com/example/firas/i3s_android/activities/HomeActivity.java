package com.example.firas.i3s_android.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.fragments.AuthentificationFragment;
import com.example.firas.i3s_android.fragments.ListeRondeFragment;
import com.example.firas.i3s_android.fragments.MaincouranteFragment;
import com.example.firas.i3s_android.fragments.PointageFragment;
import com.example.firas.i3s_android.fragments.SOSFragment;
import com.example.firas.i3s_android.fragments.SuiviRondeFragment;
import com.example.firas.i3s_android.interfas.PictureTakenCallback;
import com.example.firas.i3s_android.model.Ronde;
import com.example.firas.i3s_android.utils.MarshMallowPermission;
import com.example.firas.i3s_android.utils.MySingleton;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends BaseActivity {

    PictureTakenCallback pictureTakenCallback;
    PrimaryDrawerItem main_courant_item,pointage_item,suivi_ronde_item,sos_item,quit_item;

    Ronde ronde;
    JsonAdapter<Ronde> jsonAdapter;
    Moshi moshi;
    private ImageView main_courante_img;
    Bitmap photo=null;
    MaincouranteFragment maincouranteFragment;
    MaincouranteFragment fragment;
    private static final int CAMERA_REQUEST = 1888;
    AccountHeader headerResult;
    Drawer result;
    private static final int REQUEST_CAMERA = 1;
    private static final int SELECT_FILE = 2;
    private int PROFILE_PIC_COUNT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        main_courant_item = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.mani_courante_drawer).withIcon(R.drawable.task);
        pointage_item=new PrimaryDrawerItem().withIdentifier(2).withName(R.string.pointage_drawer).withIcon(R.drawable.qr_code_scanning_on_a_fingerprint);
        suivi_ronde_item=new PrimaryDrawerItem().withIdentifier(3).withName(R.string.suivi_ronde_drawer).withIcon(R.drawable.three_circling_arrows);
        sos_item=new PrimaryDrawerItem().withIdentifier(4).withName(R.string.sos_drawer).withIcon(R.drawable.alarm);;
        quit_item=new PrimaryDrawerItem().withIdentifier(5).withName(R.string.quitter_drawer).withIcon(R.drawable.exit);
       toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (MySingleton.getInstance().readFile("ronde.json", this) == null) {
            MySingleton.getInstance().writeFile("ronde.json", this, "");
        }
replaceFragment(new PointageFragment(),R.id.frame);
      moshi = new Moshi.Builder().build();
        jsonAdapter = moshi.adapter(Ronde.class);

        try {
            ronde = jsonAdapter.fromJson(MySingleton.getInstance().readFile("ronde.json", this));
        } catch (IOException e) {
            e.printStackTrace();
        }
       headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background_material)
               .withSelectionListEnabledForSingleProfile(false)
                .addProfiles(
                        new ProfileDrawerItem().withName("Alain Delon").withIcon(getResources().getDrawable(R.drawable.ic_account_circle_black_24dp))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        Log.e("Profile","logged");
                      /*  MarshMallowPermission marshMallowPermission = new MarshMallowPermission(HomeActivity.this);
                        if (!marshMallowPermission.checkPermissionForCamera()) {
                            Log.e("MARSHALLOW","no permission !!");
                            marshMallowPermission.requestPermissionForCamera();
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);}
                        else
                        {

                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST);
                        }*/

                        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(HomeActivity.this);
                        builder.setTitle("Add Photo!");
                        builder.setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {

                                if (items[item].equals("Take Photo")) {
                                    PROFILE_PIC_COUNT = 1;
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(intent, REQUEST_CAMERA);
                                } else if (items[item].equals("Choose from Library")) {
                                    PROFILE_PIC_COUNT = 1;
                                    Intent intent = new Intent(
                                            Intent.ACTION_PICK,
                                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    startActivityForResult(intent,SELECT_FILE);
                                } else if (items[item].equals("Cancel")) {
                                    PROFILE_PIC_COUNT = 0;
                                    dialog.dismiss();
                                }
                            }
                        });
                        builder.show();
                        return false;
                    }
                })
                .build();

         result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        main_courant_item,

                        pointage_item,

                        suivi_ronde_item,
                        new DividerDrawerItem(),
                        sos_item,
                        new DividerDrawerItem(),
                        quit_item


                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        Log.e("home_actv",position+"")  ;

                        switch (position)
                        {
                            case 1:
                                toolbar.setTitle(R.string.mani_courante_drawer);
                         replaceFragment(new MaincouranteFragment(),R.id.frame);
                                break;

                            case 2:
                                replaceFragment(new PointageFragment(),R.id.frame);
                                toolbar.setTitle(R.string.pointage_drawer);
                                break;
                            case 3:
                         toolbar.setTitle(R.string.suivi_ronde_drawer);
                                try {
                                    ronde = jsonAdapter.fromJson(MySingleton.getInstance().readFile("ronde.json", getApplicationContext()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if(ronde!=null)
                                replaceFragment(new ListeRondeFragment(),R.id.frame);
                                else
                                    replaceFragment(new SuiviRondeFragment(),R.id.frame);
                                break;
                            case 5:
                                toolbar.setTitle(R.string.sos_drawer);
                                replaceFragment(new SOSFragment(),R.id.frame);
                                break;
                            case 7:
                                finish();
                                break;


                        }


                        return false;
                    }
                })
                .build();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK
        Bitmap bitmap=null;

        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    Uri imageUri = data.getData();
                    try {
                         bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                break;
            case 2:
                if(resultCode == RESULT_OK){
                    Uri imageUri = data.getData();
                    try {
                         bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }

                Log.e("onActivityResult","logged");

                final IProfile profile = new ProfileDrawerItem().withName("Alain Delon").withIcon(bitmap);

                headerResult=new AccountHeaderBuilder()
                        .withActivity(this)
                        .withSelectionListEnabledForSingleProfile(false)
                        .withHeaderBackground(R.drawable.background_material)
                        .addProfiles(
                                profile )
                        .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                            @Override
                            public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                                Log.e("Profile","logged");

                                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                                return false;
                            }
                        })
                        .build();
//rebuilding drawer
                Log.e("headerResult","logged");
                result = new DrawerBuilder()
                        .withActivity(this)
                        .withToolbar(toolbar)
                        .withAccountHeader(headerResult)
                        .addDrawerItems(
                                main_courant_item,
                                pointage_item,
                                suivi_ronde_item,
                                new DividerDrawerItem(),
                                sos_item,
                                new DividerDrawerItem(),
                                quit_item


                        )
                        .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                // do something with the clicked item :D

                                Log.e("home_actv",position+"")  ;

                                switch (position)
                                {
                                    case 1:
                                        toolbar.setTitle(R.string.mani_courante_drawer);
                              replaceFragment(new MaincouranteFragment(),R.id.frame);
                                        break;

                                    case 2:
                                        replaceFragment(new PointageFragment(),R.id.frame);
                                        toolbar.setTitle(R.string.pointage_drawer);
                                        break;
                                    case 3:
                                        toolbar.setTitle(R.string.suivi_ronde_drawer);
                                        try {
                                            ronde = jsonAdapter.fromJson(MySingleton.getInstance().readFile("ronde.json", getApplicationContext()));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        if(ronde!=null)
                                            replaceFragment(new ListeRondeFragment(),R.id.frame);
                                        else
                                            replaceFragment(new SuiviRondeFragment(),R.id.frame);
                                        break;
                                    case 5:
                                        toolbar.setTitle(R.string.sos_drawer);
                                        replaceFragment(new SOSFragment(),R.id.frame);
                                        break;
                                    case 7:

                                      finish();
                                        break;


                                }


                                return false;
                            }
                        })
                        .build();
                Log.e("Result","logged");


    }

    public void setPictureTakenCallback(PictureTakenCallback pictureTakenCallback) {
        this.pictureTakenCallback = pictureTakenCallback;
    }


}