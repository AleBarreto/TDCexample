package com.oceanbrasil.tdcexample.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oceanbrasil.tdcexample.R;
import com.oceanbrasil.tdcexample.model.Carro;

import java.util.HashMap;
import java.util.Map;

public class ActivityGeoFire extends AppCompatActivity implements OnMapReadyCallback {

    private static final String LOG = "MAPLOG"; // para filtro de Log.
    private static final LatLng LOCAL_USUARIO_FAKE = new LatLng(-27.602214, -48.551934); // latitude e longitude usada para definir localização fake usuário.
    private static final double LOCAL_RADIUS = 0.6; // raio de ação


    private GoogleMap googleMap; // Objeto Google Map
    private Map<String,Marker> markers; // HashMap de marcadores

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_fire);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        markers = new HashMap<>();

    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        addPosicaoSimulacao( googleMap );

        consultar();

    }

    private void consultar(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("carros_posicao"); // acessando a coleção de posição de carros.
        GeoFire geoFire = new GeoFire(ref); // instancia do objeto GeoFire

        GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation( LOCAL_USUARIO_FAKE.latitude , LOCAL_USUARIO_FAKE.longitude ), LOCAL_RADIUS ); // Fazer uma consulta utilizando GeoFire e GeoQuery
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() { // Callbacks
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(location.latitude, location.longitude)).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_name)));
                markers.put(key, marker);
            }

            @Override
            public void onKeyExited(String key) {
                Marker marker = markers.get(key);
                if (marker != null) {
                    marker.remove();
                    markers.remove(key);
                }
            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {
                Log.d(LOG,String.format("Key %s moved within the search area to [%f,%f]", key, location.latitude, location.longitude));
            }

            @Override
            public void onGeoQueryReady() {
                Log.d(LOG,"Tudo ok");
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {
                Log.e(LOG,"Ocorreu erro ao fazer consulta: " + error);
            }
        });

    }


    private void addPosicaoSimulacao( GoogleMap googleMap ){
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(-27.602214, -48.551934))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_person)));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-27.602214, -48.551934), 16));
        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(-27.602214, -48.551934))
                .fillColor(Color.parseColor("#503F51B5"))
                .radius(600);

        googleMap.addCircle(circleOptions);

    }

    /**
     * Simula de forma bem ampla como salvar
     */
    private void salvarDados(){
        //Criando objeto Carro
        Carro carro = new Carro();
        carro.setMarca("Honda");
        carro.setAno("2017");
        carro.setCor("preto");

        DatabaseReference referenciaBd = FirebaseDatabase.getInstance().getReference().child("carros"); // acessando coleção de carros

        String keyIdGerada = referenciaBd.push().getKey(); // obtendo a keyid antes da salvar

        referenciaBd.child(keyIdGerada).setValue(carro); // salvando o obejto carro.


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("carros_posicao"); //  acessando a coleção de posição de carros.
        GeoFire geoFire = new GeoFire(ref); // Utilizando a lib GeoFire
        geoFire.setLocation( keyIdGerada,new GeoLocation( -27.597402, -48.550786 )); // Salvando a posicao do carro


    }

}
