package com.example.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailsActivity extends AppCompatActivity {

    private TextView referenceTextView, dateTextView, montantTextView, beneficiaireTextView, etatTextView,descriptionTextView,numeroTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Récupérer les vues
        referenceTextView = findViewById(R.id.referenceTextView);
        dateTextView = findViewById(R.id.dateTextView);
        montantTextView = findViewById(R.id.montantTextView);
        beneficiaireTextView = findViewById(R.id.beneficiaireTextView);
        descriptionTextView=findViewById(R.id.descriptionTextView);
        etatTextView = findViewById(R.id.etatTextView);
        numeroTextView=findViewById(R.id.numeroTextView);

        // Récupérer les données passées par l'intention
        Intent intent = getIntent();
        String reference = intent.getStringExtra("reference");
        String date = intent.getStringExtra("date");
        String montant = intent.getStringExtra("montant");
        String beneficiaire = intent.getStringExtra("beneficiaire");
        String description = intent.getStringExtra("description");
        String num_compte=intent.getStringExtra("num_compte");
        String etat = intent.getStringExtra("etat");

        // Afficher les données
        referenceTextView.setText(reference);
        dateTextView.setText( date);
        montantTextView.setText( montant);
        beneficiaireTextView.setText( beneficiaire);
        etatTextView.setText( etat);
        descriptionTextView.setText( description);
        numeroTextView.setText(num_compte);
    }
}