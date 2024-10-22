package com.example.tp3;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class AcceuilActivity extends AppCompatActivity {
    private ListView transactionListView;
    private List<Transaction> transactionList;
    private Button agencesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        transactionListView = findViewById(R.id.transactionListView);
        transactionList = new ArrayList<>();
        agencesButton = findViewById(R.id.btn_agences);

        // Ajouter des transactions
        transactionList.add(new Transaction(R.drawable.internet, "Facture Internet", "09/10/18", "299,00 MAD","Mohammed","42524771162"," facture internet Orange","Complet"));
        transactionList.add(new Transaction(R.drawable.emission, "Emission d'une somme", "09/10/18", "5000,0 MAD","Nawal","42524771162"," Emission","Complet"));
        transactionList.add(new Transaction(R.drawable.paiement, "Paiement express", "09/10/18", "2990,0 MAD","Fatyma","42524771162"," paiement en faveur de Fatyma","en cours"));
        transactionList.add(new Transaction(R.drawable.visa, "Paiement par carte", "09/10/18", "500,00 MAD","Lc","42524771162"," paiement par carte","Complet"));
        transactionList.add(new Transaction(R.drawable.retrait, "Retrait d'espèces", "09/10/18", "1000,0 MAD","Ali","42524771162","Retrait","Complet"));

        TransactionAdapter adapter = new TransactionAdapter(this, transactionList);
        transactionListView.setAdapter(adapter);

        // Gérer le clic sur un élément de la liste
        transactionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupérer la transaction sélectionnée
                Transaction transaction = transactionList.get(position);

                // Créer une intention pour ouvrir DetailsActivity
                Intent intent = new Intent(AcceuilActivity.this, DetailsActivity.class);
                intent.putExtra("reference", transaction.getTitle());
                intent.putExtra("date", transaction.getDate());
                intent.putExtra("montant", transaction.getAmount());
                intent.putExtra("beneficiaire", transaction.getBenificiaire());
                intent.putExtra("etat", transaction.getEtat());
                intent.putExtra("num_compte", transaction.getNumero_compte());
                intent.putExtra("description", transaction.getDescription());

                // Démarrer l'activité DetailsActivity
                startActivity(intent);
            }
        });
        // Gérer le clic sur le bouton des agences bancaires
        agencesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer une intention pour ouvrir l'écran des agences
                Intent intent = new Intent(AcceuilActivity.this, MapsActivity.class);
                startActivity(intent); // Démarrer l'activité des agences
            }
        });
    }

}