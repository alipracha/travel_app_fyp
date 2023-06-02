package com.example.triparrangersfyp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trips {
    @SerializedName("trip_id")
    @Expose
    private int trip_id;

    @SerializedName("trip_title")
    @Expose
    private String trip_title;

    @SerializedName("trip_description")
    @Expose
    private String trip_description;

    @SerializedName("trip_depDate")
    @Expose
    private String trip_depDate;

    @SerializedName("trip_arrDate")
    @Expose
    private String trip_arrDate;

    @SerializedName("trip_depTime")
    @Expose
    private String trip_depTime;

    @SerializedName("trip_arrTime")
    @Expose
    private String trip_arrTime;

    @SerializedName("trip_pickup")
    @Expose
    private String trip_pickup;

    @SerializedName("trip_dropoff")
    @Expose
    private String trip_dropoff;

    @SerializedName("trip_numSeats")
    @Expose
    private String trip_numSeats;

    @SerializedName("trip_payment")
    @Expose
    private String trip_payment;

    @SerializedName("trip_status")
    @Expose
    private String trip_status;

    @SerializedName("trip_image")
    @Expose
    private String trip_image;

    @SerializedName("travel_agency_id")
    @Expose
    private int travel_agency_id;

    @SerializedName("ta_name")
    @Expose
    private String ta_name;

    @SerializedName("ta_phone")
    @Expose
    private String ta_phone;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("error")
    @Expose
    private boolean error;

    @SerializedName("message")
    @Expose
    private String message;

    public Trips() {
    }



    public Trips(int trip_id, String trip_title, String trip_description, String trip_depDate, String trip_arrDate, String trip_depTime, String trip_arrTime,
                 String trip_pickup, String trip_dropoff, String trip_numSeats,
                 String trip_payment, String trip_status, String trip_image) {
        this.trip_id = trip_id;
        this.trip_title = trip_title;
        this.trip_description = trip_description;
        this.trip_depDate = trip_depDate;
        this.trip_arrDate = trip_arrDate;
        this.trip_depTime = trip_depTime;
        this.trip_arrTime = trip_arrTime;
        this.trip_pickup = trip_pickup;
        this.trip_dropoff = trip_dropoff;
        this.trip_numSeats = trip_numSeats;
        this.trip_payment = trip_payment;
        this.trip_status = trip_status;
        this.trip_image = trip_image;
    }

    public Trips(int trip_id, String trip_title, String trip_description, String trip_depDate, String trip_arrDate, String trip_depTime, String trip_arrTime,
                 String trip_pickup, String trip_dropoff, String trip_numSeats, String trip_payment,
                 String trip_status, String trip_image, int travel_agency_id, String ta_name, String ta_phone) {
        this.trip_id = trip_id;
        this.trip_title = trip_title;
        this.trip_description = trip_description;
        this.trip_depDate = trip_depDate;
        this.trip_arrDate = trip_arrDate;
        this.trip_depTime = trip_depTime;
        this.trip_arrTime = trip_arrTime;
        this.trip_pickup = trip_pickup;
        this.trip_dropoff = trip_dropoff;
        this.trip_numSeats = trip_numSeats;
        this.trip_payment = trip_payment;
        this.trip_status = trip_status;
        this.trip_image = trip_image;
        this.travel_agency_id = travel_agency_id;
        this.ta_name = ta_name;
        this.ta_phone = ta_phone;
    }

    public Trips(int trip_id, String trip_title, String trip_description, String trip_depDate, String trip_arrDate, String trip_depTime, String trip_arrTime,
                 String trip_pickup, String trip_dropoff, String trip_numSeats, String trip_payment,
                 String trip_status, String trip_image, int travel_agency_id) {
        this.trip_id = trip_id;
        this.trip_title = trip_title;
        this.trip_description = trip_description;
        this.trip_depDate = trip_depDate;
        this.trip_arrDate = trip_arrDate;
        this.trip_depTime = trip_depTime;
        this.trip_arrTime = trip_arrTime;
        this.trip_pickup = trip_pickup;
        this.trip_dropoff = trip_dropoff;
        this.trip_numSeats = trip_numSeats;
        this.trip_payment = trip_payment;
        this.trip_status = trip_status;
        this.trip_image = trip_image;
        this.travel_agency_id = travel_agency_id;
    }

    public String getTrip_numSeats() {
        return trip_numSeats;
    }

    public void setTrip_numSeats(String trip_numSeats) {
        this.trip_numSeats = trip_numSeats;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getTrip_title() {
        return trip_title;
    }

    public void setTrip_title(String trip_title) {
        this.trip_title = trip_title;
    }

    public String getTrip_description() {
        return trip_description;
    }

    public void setTrip_description(String trip_description) {
        this.trip_description = trip_description;
    }

    public String getTrip_depDate() {
        return trip_depDate;
    }

    public void setTrip_depDate(String trip_depDate) {
        this.trip_depDate = trip_depDate;
    }

    public String getTrip_arrDate() {
        return trip_arrDate;
    }

    public void setTrip_arrDate(String trip_arrDate) {
        this.trip_arrDate = trip_arrDate;
    }

    public String getTrip_depTime() {
        return trip_depTime;
    }

    public void setTrip_depTime(String trip_depTime) {
        this.trip_depTime = trip_depTime;
    }

    public String getTrip_arrTime() {
        return trip_arrTime;
    }

    public void setTrip_arrTime(String trip_arrTime) {
        this.trip_arrTime = trip_arrTime;
    }

    public String getTrip_status() {
        return trip_status;
    }

    public void setTrip_status(String trip_status) {
        this.trip_status = trip_status;
    }

    public String getTrip_pickup() {
        return trip_pickup;
    }

    public void setTrip_pickup(String trip_pickup) {
        this.trip_pickup = trip_pickup;
    }

    public String getTrip_dropoff() {
        return trip_dropoff;
    }

    public void setTrip_dropoff(String trip_dropoff) {
        this.trip_dropoff = trip_dropoff;
    }

    public String getTrip_payment() {
        return trip_payment;
    }

    public void setTrip_payment(String trip_payment) {
        this.trip_payment = trip_payment;
    }

    public String getTrip_image() {
        return trip_image;
    }

    public void setTrip_image(String trip_image) {
        this.trip_image = trip_image;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTravel_agency_id() {
        return travel_agency_id;
    }

    public void setTravel_agency_id(int travel_agency_id) {
        this.travel_agency_id = travel_agency_id;
    }

    public String getTa_name() {
        return ta_name;
    }

    public void setTa_name(String ta_name) {
        this.ta_name = ta_name;
    }

    public String getTa_phone() {
        return ta_phone;
    }

    public void setTa_phone(String ta_phone) {
        this.ta_phone = ta_phone;
    }
}
