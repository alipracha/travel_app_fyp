package com.example.triparrangersfyp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelAgency {

    @SerializedName("ta_id")
    @Expose
    private int ta_id;

    @SerializedName("ta_name")
    @Expose
    private String ta_name;

    @SerializedName("ta_email")
    @Expose
    private String ta_email;

    @SerializedName("ta_password")
    @Expose
    private String ta_password;

    @SerializedName("ta_phone")
    @Expose
    private String ta_phone;

    @SerializedName("ta_cnic")
    @Expose
    private String ta_cnic;

    @SerializedName("ta_image")
    @Expose
    private String ta_image;

    @SerializedName("ta_status")
    @Expose
    private String ta_status;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("error")
    @Expose
    private boolean error;

    @SerializedName("message")
    @Expose
    private String message;

    public  TravelAgency() {
    }

    public int getTa_id() {
        return ta_id;
    }

    public void setTa_id(int ta_id) {
        this.ta_id = ta_id;
    }

    public String getTa_name() {
        return ta_name;
    }

    public TravelAgency(int ta_id, String ta_name, String ta_email,  String ta_phone,
                        String ta_cnic, String ta_status,  String ta_image) {
        this.ta_id = ta_id;
        this.ta_name = ta_name;
        this.ta_email = ta_email;
        this.ta_phone = ta_phone;
        this.ta_cnic = ta_cnic;
        this.ta_status = ta_status;
        this.ta_image = ta_image;


    }

    public void setTa_name(String ta_name) {
        this.ta_name = ta_name;
    }

    public String getTa_email() {
        return ta_email;
    }

    public void setTa_email(String ta_email) {
        this.ta_email = ta_email;
    }

    public String getTa_password() {
        return ta_password;
    }

    public void setTa_password(String ta_password) {
        this.ta_password = ta_password;
    }

    public String getTa_phone() {
        return ta_phone;
    }

    public void setTa_phone(String ta_phone) {
        this.ta_phone = ta_phone;
    }

    public String getTa_cnic() {
        return ta_cnic;
    }

    public void setTa_cnic(String ta_cnic) {
        this.ta_cnic = ta_cnic;
    }

    public String getTa_image() {
        return ta_image;
    }

    public void setTa_image(String ta_image) {
        this.ta_image = ta_image;
    }

    public String getTa_status() {
        return ta_status;
    }

    public void setTa_status(String ta_status) {
        this.ta_status = ta_status;
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
}
