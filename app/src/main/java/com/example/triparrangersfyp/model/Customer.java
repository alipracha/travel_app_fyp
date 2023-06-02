package com.example.triparrangersfyp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {
    @SerializedName("c_id")
    @Expose
    private int c_id;

    @SerializedName("c_name")
    @Expose
    private String c_name;

    @SerializedName("c_email")
    @Expose
    private String c_email;

    @SerializedName("c_password")
    @Expose
    private String c_password;

    @SerializedName("c_phone")
    @Expose
    private String c_phone;

    @SerializedName("c_cnic")
    @Expose
    private String c_cnic;

    @SerializedName("c_status")
    @Expose
    private String c_status;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("error")
    @Expose
    private boolean error;

    @SerializedName("message")
    @Expose
    private String message;

    public Customer() {
    }

    public Customer(int c_id, String c_name, String c_email, String c_phone, String c_cnic, String c_status) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_email = c_email;
        this.c_phone = c_phone;
        this.c_cnic = c_cnic;
        this.c_status = c_status;
    }


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

    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public String getC_password() {
        return c_password;
    }

    public void setC_password(String c_password) {
        this.c_password = c_password;
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

    public String getC_status() {
        return c_status;
    }

    public void setC_status(String c_status) {
        this.c_status = c_status;
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
}
