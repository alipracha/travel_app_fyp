package com.example.triparrangersfyp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Booking {

        @SerializedName("b_customer_id")
        @Expose
        private int b_customer_id;

        @SerializedName("b_travel_agency_id")
        @Expose
        private int b_travel_agency_id;

        @SerializedName("b_trip_id")
        @Expose
        private int b_trip_id;

        @SerializedName("b_price")
        @Expose
        private String b_price;

        @SerializedName("b_seats")
        @Expose
        private int b_seats;

        @SerializedName("b_status")
        @Expose
        private int b_status;

        @SerializedName("b_datetime")
        @Expose
        private int  b_datetime;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("error")
    @Expose
    private boolean error;

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("c_name")
    @Expose
    private String c_name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public Booking() {
    }

    public Booking(int b_customer_id, String c_name) {
        this.b_customer_id = b_customer_id;
        this.c_name = c_name;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public int getB_customer_id() {
        return b_customer_id;
    }

    public void setB_customer_id(int b_customer_id) {
        this.b_customer_id = b_customer_id;
    }

    public int getB_travel_agency_id() {
        return b_travel_agency_id;
    }

    public void setB_travel_agency_id(int b_travel_agency_id) {
        this.b_travel_agency_id = b_travel_agency_id;
    }

    public int getB_trip_id() {
        return b_trip_id;
    }

    public void setB_trip_id(int b_trip_id) {
        this.b_trip_id = b_trip_id;
    }

    public String getB_price() {
        return b_price;
    }

    public void setB_price(String b_price) {
        this.b_price = b_price;
    }

    public int getB_seats() {
        return b_seats;
    }

    public void setB_seats(int b_seats) {
        this.b_seats = b_seats;
    }

    public int getB_status() {
        return b_status;
    }

    public void setB_status(int b_status) {
        this.b_status = b_status;
    }

    public int getB_datetime() {
        return b_datetime;
    }

    public void setB_datetime(int b_datetime) {
        this.b_datetime = b_datetime;
    }

    public Booking(int b_customer_id, int b_travel_agency_id, int b_trip_id, String b_price, int b_seats, int b_status, int b_datetime, String code, boolean error, String message) {
        this.b_customer_id = b_customer_id;
        this.b_travel_agency_id = b_travel_agency_id;
        this.b_trip_id = b_trip_id;
        this.b_price = b_price;
        this.b_seats = b_seats;
        this.b_status = b_status;
        this.b_datetime = b_datetime;
        this.code = code;
        this.error = error;
        this.message = message;
    }
}
