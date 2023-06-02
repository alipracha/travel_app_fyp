package com.example.triparrangersfyp.model;

public class Report {

    private String travelAgenecyName;
    private int total;

    public Report() {
    }

    public Report(String travelAgenecyName, int total) {
        this.travelAgenecyName = travelAgenecyName;
        this.total = total;
    }

    public String getTravelAgenecyName() {
        return travelAgenecyName;
    }

    public void setTravelAgenecyName(String travelAgenecyName) {
        this.travelAgenecyName = travelAgenecyName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
