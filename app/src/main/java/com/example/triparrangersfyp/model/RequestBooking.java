package com.example.triparrangersfyp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestBooking {
    @SerializedName("c_id")
    @Expose
    private int c_id;

    @SerializedName("c_name")
    @Expose
    private String c_name;

    @SerializedName("c_phone")
    @Expose
    private String c_phone;

    @SerializedName("c_cnic")
    @Expose
    private String c_cnic;

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

    @SerializedName("trip_image")
    @Expose
    private String trip_image;

    @SerializedName("b_id")
    @Expose
    private int b_id;

    @SerializedName("b_price")
    @Expose
    private String b_price;

    @SerializedName("b_seats")
    @Expose
    private String b_seats;

    @SerializedName("b_status")
    @Expose
    private String b_status;

    @SerializedName("b_datetime")
    @Expose
    private String b_datetime;

    @SerializedName("code")
    @Expose
    private String code;

    public RequestBooking(int c_id, String c_name, String c_phone, String c_cnic, int trip_id, String trip_title, String trip_description, String trip_depDate, String trip_arrDate, String trip_depTime, String trip_arrTime, String trip_pickup, String trip_dropoff,String trip_image, int b_id, String b_price, String b_seats, String b_status, String b_datetime) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_phone = c_phone;
        this.c_cnic = c_cnic;
        this.trip_id = trip_id;
        this.trip_title = trip_title;
        this.trip_description = trip_description;
        this.trip_depDate = trip_depDate;
        this.trip_arrDate = trip_arrDate;
        this.trip_depTime = trip_depTime;
        this.trip_arrTime = trip_arrTime;
        this.trip_pickup = trip_pickup;
        this.trip_dropoff = trip_dropoff;
        this.trip_image=trip_image;
        this.b_id = b_id;
        this.b_price = b_price;
        this.b_seats = b_seats;
        this.b_status = b_status;
        this.b_datetime = b_datetime;
    }

    public String getTrip_image() {
        return trip_image;
    }

    public void setTrip_image(String trip_image) {
        this.trip_image = trip_image;
    }

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

    @SerializedName("error")
    @Expose
    private boolean error;

    @SerializedName("message")
    @Expose
    private String message;

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_phone() {
        return c_phone;
    }

    public void setC_phone(String c_phone) {
        this.c_phone = c_phone;
    }

    public String getC_cnic() {
        return c_cnic;
    }

    public void setC_cnic(String c_cnic) {
        this.c_cnic = c_cnic;
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

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getB_price() {
        return b_price;
    }

    public void setB_price(String b_price) {
        this.b_price = b_price;
    }

    public String getB_seats() {
        return b_seats;
    }

    public void setB_seats(String b_seats) {
        this.b_seats = b_seats;
    }

    public String getB_status() {
        return b_status;
    }

    public void setB_status(String b_status) {
        this.b_status = b_status;
    }

    public String getB_datetime() {
        return b_datetime;
    }

    public void setB_datetime(String b_datetime) {
        this.b_datetime = b_datetime;
    }

    public RequestBooking() {
    }

}



