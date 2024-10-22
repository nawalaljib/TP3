package com.example.tp3;

public class Transaction {
    private int icon;
    private String title;
    private String date;
    private String amount;
    private String benificiaire;
    private String numero_compte;
    private String description;
    private String Etat;

    public Transaction(int icon, String title, String date, String amount,String benificiaire,String numero_compte, String description,String Etat) {
        this.icon = icon;
        this.title = title;
        this.date = date;
        this.amount = amount;
        this.benificiaire=benificiaire;
        this.numero_compte=numero_compte;
        this.description=description;
        this.Etat=Etat;

    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

    public String getBenificiaire() {
        return benificiaire;
    }

    public String getNumero_compte() {
        return numero_compte;
    }

    public String getDescription() {
        return description;
    }

    public String getEtat() {
        return Etat;
    }
}
