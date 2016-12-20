package com.example.firas.i3s_android.model;

/**
 * Created by firas on 11/10/2016.
 */

public class Services {
public Service prise_service;
    public Service fin_service;

    public Services(Service prise_service, Service fin_service) {
        this.prise_service = prise_service;
        this.fin_service = fin_service;
    }

    public Services() {
    }

    public Service getPrise_service() {
        return prise_service;
    }

    public void setPrise_service(Service prise_service) {
        this.prise_service = prise_service;
    }

    public Service getFin_service() {
        return fin_service;
    }

    public void setFin_service(Service fin_service) {
        this.fin_service = fin_service;
    }

    @Override
    public String toString() {
        return "Services{" +
                "prise_service=" + prise_service.toString() +
                ", fin_service=" + fin_service.toString() +
                '}';
    }
}

