package com.example.firas.i3s_android.model;

import java.util.List;

/**
 * Created by firas on 14/10/2016.
 */

public class Ronde {
    Pointage pointage1;
    Pointage pointage2;
    Pointage pointage3;
    Pointage pointage4;
    Pointage pointage5;
String ronde_name;
    String ronde_time;

    public String getRonde_name() {
        return ronde_name;
    }

    public void setRonde_name(String ronde_name) {
        this.ronde_name = ronde_name;
    }

    public String getRonde_time() {
        return ronde_time;
    }

    public void setRonde_time(String ronde_time) {
        this.ronde_time = ronde_time;
    }

    public Ronde() {
    }


    public Ronde(Pointage pointage1, Pointage pointage2, Pointage pointage3, Pointage pointage4, Pointage pointage5, String ronde_name) {
        this.pointage1 = pointage1;
        this.pointage2 = pointage2;
        this.pointage3 = pointage3;
        this.pointage4 = pointage4;
        this.pointage5 = pointage5;
        this.ronde_name = ronde_name;
    }

    public Pointage getPointage1() {
        return pointage1;
    }

    public void setPointage1(Pointage pointage1) {
        this.pointage1 = pointage1;
    }

    public Pointage getPointage2() {
        return pointage2;
    }

    public void setPointage2(Pointage pointage2) {
        this.pointage2 = pointage2;
    }

    public Pointage getPointage3() {
        return pointage3;
    }

    public void setPointage3(Pointage pointage3) {
        this.pointage3 = pointage3;
    }

    public Pointage getPointage4() {
        return pointage4;
    }

    public void setPointage4(Pointage pointage4) {
        this.pointage4 = pointage4;
    }

    public Pointage getPointage5() {
        return pointage5;
    }

    public void setPointage5(Pointage pointage5) {
        this.pointage5 = pointage5;
    }
}


