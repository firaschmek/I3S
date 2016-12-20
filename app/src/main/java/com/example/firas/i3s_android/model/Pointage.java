package com.example.firas.i3s_android.model;

/**
 * Created by firas on 14/10/2016.
 */

public class Pointage {
    String type ;
    boolean enabled;
    String time;

    public Pointage() {
    }

    public Pointage(String type, boolean enabled, String time) {
        this.type = type;
        this.enabled = enabled;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
