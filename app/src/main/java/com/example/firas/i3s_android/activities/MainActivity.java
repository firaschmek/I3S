package com.example.firas.i3s_android.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.fragments.AuthentificationFragment;

import static java.security.AccessController.getContext;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
replaceFragment(new AuthentificationFragment(),R.id.activity_main_FrameLayout);


    }
}
