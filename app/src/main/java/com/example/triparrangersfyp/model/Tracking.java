package com.example.triparrangersfyp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tracking {
    @SerializedName("Location_ID")
    @Expose
    private int Location_ID;

    @SerializedName("Latitude")
    @Expose
    private double Latitude;

    @SerializedName("Longitude")
    @Expose
    private double Longitude;

    @SerializedName("Customer_ID")
    @Expose
    private double Customer_ID;

    public Tracking() {
    }

    public Tracking(int location_ID, double latitude, double longitude, double customer_ID) {
        Location_ID = location_ID;
        Latitude = latitude;
        Longitude = longitude;
        Customer_ID = customer_ID;
    }

    public int getLocation_ID() {
        return Location_ID;
    }

    public void setLocation_ID(int location_ID) {
        Location_ID = location_ID;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(double customer_ID) {
        Customer_ID = customer_ID;
    }
}
