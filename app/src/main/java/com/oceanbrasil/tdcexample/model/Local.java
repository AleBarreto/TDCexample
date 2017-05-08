package com.oceanbrasil.tdcexample.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by ocean on 01/05/17.
 */

public class Local implements ClusterItem{

    private String titulo;
    private String descricao;
    private double latitude;
    private double longitude;

    public Local() {
    }

    public Local(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(this.latitude,this.longitude);
    }
}
