package com.example.firas.i3s_android.application;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by firas on 11/10/2016.
 */

public class MainAppli extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // It's important to initialize the ResourceZoneInfoProvider; otherwise
        // joda-time-android will not work.
        JodaTimeAndroid.init(this);
    }
}
