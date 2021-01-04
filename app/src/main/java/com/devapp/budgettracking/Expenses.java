package com.devapp.budgettracking;

import java.io.Serializable;

public class Expenses implements Serializable {

    int id;
    String name;
    float prix;
    double latitude;
    double longitude;

    public Expenses() {

    }

    public Expenses(int id, String name, double latitude, double longitude, float prix) {
        this.id = id;
        this.name = name;
        this.prix = prix;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // ID
    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    // Name
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Latitude
    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // Longitude
    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // Prix
    public float getPrix() {
        return this.prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
