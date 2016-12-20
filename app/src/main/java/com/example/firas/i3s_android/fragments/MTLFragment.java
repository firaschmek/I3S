package com.example.firas.i3s_android.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firas.i3s_android.R;
import com.example.firas.i3s_android.activities.BaseActivity;
import com.example.firas.i3s_android.activities.MainActivity;

/**
 * Created by firas on 12/10/2016.
 */

public class MTLFragment extends BaseFragment{
    ImageView back_btn;
    TextView mtl_header;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mtl, container, false);
        mtl_header= (TextView) view.findViewById(R.id.mtl_header);
        mtl_header.setText("Mentions légales");
        WebView webView= (WebView)view.findViewById(R.id.webview_test);
        back_btn= (ImageView) view.findViewById(R.id.back_btn);
       back_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               baseActivity.replaceFragment(new AuthentificationFragment(),R.id.activity_main_FrameLayout);
           }
       });
        webView.loadUrl("file:///android_asset/Mentions légales.html");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                Log.e("webview",view.getTitle());
            }
        });
        return  view;
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}
