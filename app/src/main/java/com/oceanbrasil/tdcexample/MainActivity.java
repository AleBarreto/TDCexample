package com.oceanbrasil.tdcexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.oceanbrasil.tdcexample.activities.ActivityAgrupamento;
import com.oceanbrasil.tdcexample.activities.ActivityEstiloMap;
import com.oceanbrasil.tdcexample.activities.ActivityGeoFire;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickAgrupamento(View view) {
        abrirActivity(ActivityAgrupamento.class); // Tela de agrupamento
    }

    public void clickGeoFire(View view) {
        abrirActivity(ActivityGeoFire.class); // tela GeoFire
    }

    public void clickEstiloMap(View view) {
        abrirActivity(ActivityEstiloMap.class); // tela Estilo Mapa
    }

    /**
     * Abrir activity passando a class
     * @param aClass
     */
    private void abrirActivity(Class aClass){
        startActivity(new Intent(MainActivity.this,aClass));
    }


}
