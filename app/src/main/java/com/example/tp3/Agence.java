package com.example.tp3;

public class Agence {
    private String nomAgence;
    private String adresse;
    private String nomResponsable;
    private String telephone;
    private double latitude;
    private double longitude;

    public Agence(String nomAgence, String adresse, String nomResponsable, String telephone, double latitude, double longitude) {
        this.nomAgence = nomAgence;
        this.adresse = adresse;
        this.nomResponsable = nomResponsable;
        this.telephone = telephone;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNomAgence() {
        return nomAgence;
    }

    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNomResponsable() {
        return nomResponsable;
    }

    public void setNomResponsable(String nomResponsable) {
        this.nomResponsable = nomResponsable;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
