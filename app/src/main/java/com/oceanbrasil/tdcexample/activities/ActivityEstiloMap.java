package com.oceanbrasil.tdcexample.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.oceanbrasil.tdcexample.R;

public class ActivityEstiloMap extends AppCompatActivity implements OnMapReadyCallback {

    private static final LatLng LOCAL_USUARIO_FAKE = new LatLng(-27.602214, -48.551934); // latitude e longitude usada para definir localização fake usuário.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estilo_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(ActivityEstiloMap.this,R.raw.style_json));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LOCAL_USUARIO_FAKE, 16));

    }
}
