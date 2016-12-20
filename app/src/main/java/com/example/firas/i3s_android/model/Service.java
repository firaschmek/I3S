package com.example.firas.i3s_android.model;

/**
 * Created by firas on 11/10/2016.
 */

public    class Service
{
    public  String time;
    public boolean enabled;

    public Service(String time, boolean enabled) {
        this.time = time;
        this.enabled = enabled;
    }

    public Service() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Service{" +
                "time='" + time + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}