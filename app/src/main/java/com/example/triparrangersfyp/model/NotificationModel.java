package com.example.triparrangersfyp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationModel {

    @SerializedName("notic_title")
    @Expose
    private String notic_title;

    @SerializedName("typee")
    @Expose
    private String typee;

    @SerializedName("typeid")
    @Expose
    private int typeid;

    @SerializedName("notic_for")
    @Expose
    private String notic_for;

    @SerializedName("notic_for_id")
    @Expose
    private int notic_for_id;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("notic_id")
    @Expose
    private int notic_id;

    @SerializedName("dateTime")
    @Expose
    private String dateTime;

    public NotificationModel() {
    }

    public NotificationModel(String notic_title, String typee, int typeid, String notic_for, int notic_for_id, String price, String dateTime) {
        this.notic_title = notic_title;
        this.typee = typee;
        this.typeid = typeid;
        this.notic_for = notic_for;
        this.notic_for_id = notic_for_id;
        this.price = price;
        this.dateTime = dateTime;
    }

    public String getNotic_title() {
        return notic_title;
    }

    public void setNotic_title(String notic_title) {
        this.notic_title = notic_title;
    }

    public String getTypee() {
        return typee;
    }

    public void setTypee(String typee) {
        this.typee = typee;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getNotic_for() {
        return notic_for;
    }

    public void setNotic_for(String notic_for) {
        this.notic_for = notic_for;
    }

    public int getNotic_for_id() {
        return notic_for_id;
    }

    public void setNotic_for_id(int notic_for_id) {
        this.notic_for_id = notic_for_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getNotic_id() {
        return notic_id;
    }

    public void setNotic_id(int notic_id) {
        this.notic_id = notic_id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("error")
    @Expose
    private boolean error;

    @SerializedName("message")
    @Expose
    private String message;

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

    public NotificationModel(String notic_title, String typee, int typeid, String notic_for, int notic_for_id, String price) {
        this.notic_title = notic_title;
        this.typee = typee;
        this.typeid = typeid;
        this.notic_for = notic_for;
        this.notic_for_id = notic_for_id;
        this.price = price;
    }
}
