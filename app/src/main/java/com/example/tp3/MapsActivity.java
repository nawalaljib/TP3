package com.example.tp3;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;

import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;
import com.example.tp3.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private List<Agence> agences;
    private HashMap<Marker, Agence> markerAgenceMap = new HashMap<>(); // Pour stocker les relations entre marqueurs et agences
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Charger le layout
        setContentView(R.layout.activity_maps);

        // Initialiser la liste des agences
        initAgences();

        // Récupérer le fragment de la carte
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Toast.makeText(this, "Erreur lors du chargement de la carte", Toast.LENGTH_SHORT).show();
        }

        // Initialiser SearchView après setContentView
        searchView = findViewById(R.id.searchView);

        // Configurer le SearchView
        setupSearchView();

        // Configurer les boutons
        setupButtons();
    }


    private void setupSearchView() {
        searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAgence(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void searchAgence(String query) {
        for (Agence agence : agences) {
            if (agence.getNomAgence().toLowerCase().contains(query.toLowerCase())) {
                LatLng location = new LatLng(agence.getLatitude(), agence.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                Toast.makeText(MapsActivity.this, "Agence trouvée: " + agence.getNomAgence(), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Toast.makeText(MapsActivity.this, "Aucune agence trouvée", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Ajouter des marqueurs pour chaque agence
        for (Agence agence : agences) {
            LatLng location = new LatLng(agence.getLatitude(), agence.getLongitude());
            Marker marker = mMap.addMarker(new MarkerOptions().position(location).title(agence.getNomAgence()));
            markerAgenceMap.put(marker, agence); // Associe le marqueur à l'agence
        }

        // Déplacer la caméra sur la première agence
        if (!agences.isEmpty()) {
            LatLng firstAgenceLocation = new LatLng(agences.get(0).getLatitude(), agences.get(0).getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLng(firstAgenceLocation));
        }

        // Ajouter un listener pour les clics sur les marqueurs
        mMap.setOnMarkerClickListener(marker -> {
            Agence agence = markerAgenceMap.get(marker);
            if (agence != null) {
                showAgencyInfo(agence);
            }
            return false;
        });
    }

    private void showAgencyInfo(Agence agence) {
        new AlertDialog.Builder(this)
                .setTitle(agence.getNomAgence())
                .setMessage("Adresse: " + agence.getAdresse() + "\n" +
                        "Responsable: " + agence.getNomResponsable() + "\n" +
                        "Téléphone: " + agence.getTelephone())
                .setPositiveButton("OK", null)
                .show();
    }

    private void initAgences() {
        agences = new ArrayList<>();
        agences.add(new Agence("Agence 1", "Adresse 1", "Responsable 1", "0123456789", 34.020882, -6.841650));
        agences.add(new Agence("Agence 2", "Adresse 2", "Responsable 2", "0987654321", 34.023456, -6.845678));

    }

    private void setupButtons() {
        Button btnCall = findViewById(R.id.btn_call_center);
        Button btnSendSms = findViewById(R.id.btn_send_sms);
        Button btnSendEmail = findViewById(R.id.btn_send_email);

        // Appel
        btnCall.setOnClickListener(v -> {
            String phoneNumber = "0123456789";
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        });

        // Envoi d'un SMS
        btnSendSms.setOnClickListener(v -> {
            String smsNumber = "0123456789";
            Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + smsNumber));
            smsIntent.putExtra("sms_body", "Votre message ici");
            startActivity(smsIntent);
        });

        // Envoi d'un email
        btnSendEmail.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:example@gmail.com"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Réclamation");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Votre message ici");
            startActivity(Intent.createChooser(emailIntent, "Envoyer email via..."));
        });
    }
}
