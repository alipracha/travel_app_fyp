package com.example.triparrangersfyp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomTrip {
    @SerializedName("CTripRequest_ID")
    @Expose
    private int CTripRequest_ID;
    @SerializedName("ctrip_id")
    @Expose
    private int ctrip_id;

    @SerializedName("Trip_ID")
    @Expose
    private int Trip_ID;

    @SerializedName("ctrip_title")
    @Expose
    private String ctrip_title;

    @SerializedName("ctrip_description")
    @Expose
    private String ctrip_description;

    @SerializedName("ctrip_depDate")
    @Expose
    private String ctrip_depDate;

    @SerializedName("ctrip_arrDate")
    @Expose
    private String ctrip_arrDate;

    @SerializedName("ctrip_depTime")
    @Expose
    private String ctrip_depTime;

    @SerializedName("ctrip_arrTime")
    @Expose
    private String ctrip_arrTime;

    @SerializedName("ctrip_pickup")
    @Expose
    private String ctrip_pickup;

    @SerializedName("ctrip_dropoff")
    @Expose
    private String ctrip_dropoff;

    @SerializedName("Statuss")
    @Expose
    private String Statuss;

    @SerializedName("customer_id")
    @Expose
    private int customer_id;

    @SerializedName("Member_ID")
    @Expose
    private int Member_ID;

    @SerializedName("ctrip_status")
    @Expose
    private String ctrip_status;

    public CustomTrip(int CTripRequest_ID, int ctrip_id, String ctrip_title, String ctrip_description, String ctrip_depDate, String ctrip_arrDate, String ctrip_depTime, String ctrip_arrTime,
                      String ctrip_pickup, String ctrip_dropoff, int customer_id, int Trip_ID, int Member_ID,
                      String Statuss) {

        this.CTripRequest_ID = CTripRequest_ID;
        this.ctrip_id = ctrip_id;
        this.ctrip_title = ctrip_title;
        this.ctrip_description = ctrip_description;
        this.ctrip_depDate = ctrip_depDate;
        this.ctrip_arrDate = ctrip_arrDate;
        this.ctrip_depTime = ctrip_depTime;
        this.ctrip_arrTime = ctrip_arrTime;
        this.ctrip_pickup = ctrip_pickup;
        this.ctrip_dropoff = ctrip_dropoff;
        this.customer_id = customer_id;
        this.Trip_ID = Trip_ID;
        this.Member_ID = Member_ID;
        this.Statuss = Statuss;

    }

    public CustomTrip(int ctrip_id, String ctrip_title, String ctrip_description,
                      String ctrip_depDate, String ctrip_arrDate, String ctrip_depTime,
                      String ctrip_arrTime, String ctrip_pickup, String ctrip_dropoff, String ctrip_status) {

        this.ctrip_id = ctrip_id;
        this.ctrip_title = ctrip_title;
        this.ctrip_description = ctrip_description;
        this.ctrip_depDate = ctrip_depDate;
        this.ctrip_arrDate = ctrip_arrDate;
        this.ctrip_depTime = ctrip_depTime;
        this.ctrip_arrTime = ctrip_arrTime;
        this.ctrip_pickup = ctrip_pickup;
        this.ctrip_dropoff = ctrip_dropoff;
        this.ctrip_status = ctrip_status;
    }

    public String getCtrip_status() {
        return ctrip_status;
    }

    public void setCtrip_status(String ctrip_status) {
        this.ctrip_status = ctrip_status;
    }

    public int getTrip_ID() {
        return Trip_ID;
    }

    public void setTrip_ID(int trip_ID) {
        Trip_ID = trip_ID;
    }

    public int getMember_ID() {
        return Member_ID;
    }

    public void setMember_ID(int member_ID) {
        Member_ID = member_ID;
    }

    public int getCTripRequest_ID() {
        return CTripRequest_ID;
    }

    public void setCTripRequest_ID(int CTripRequest_ID) {
        this.CTripRequest_ID = CTripRequest_ID;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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

    public String getStatuss() {
        return Statuss;
    }

    public void setStatuss(String statuss) {
        Statuss = statuss;
    }

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("error")
    @Expose
    private boolean error;

    @SerializedName("message")
    @Expose
    private String message;

    public CustomTrip(String ctrip_title, String ctrip_description, String ctrip_depDate,
                      String ctrip_arrDate, String ctrip_depTime, String ctrip_arrTime, String ctrip_pickup,
                      String ctrip_dropoff) {
        this.ctrip_title = ctrip_title;
        this.ctrip_description = ctrip_description;
        this.ctrip_depDate = ctrip_depDate;
        this.ctrip_arrDate = ctrip_arrDate;
        this.ctrip_depTime = ctrip_depTime;
        this.ctrip_arrTime = ctrip_arrTime;
        this.ctrip_pickup = ctrip_pickup;
        this.ctrip_dropoff = ctrip_dropoff;
    }

    public CustomTrip() {
    }

    public CustomTrip(int CTripRequest_ID, int ctrip_id, String ctrip_title, String ctrip_description, String ctrip_depDate,
                      String ctrip_arrDate, String ctrip_depTime, String ctrip_arrTime, String ctrip_pickup,
                      String ctrip_dropoff) {
        this.CTripRequest_ID = CTripRequest_ID;
        this.ctrip_id = ctrip_id;
        this.ctrip_title = ctrip_title;
        this.ctrip_description = ctrip_description;
        this.ctrip_depDate = ctrip_depDate;
        this.ctrip_arrDate = ctrip_arrDate;
        this.ctrip_depTime = ctrip_depTime;
        this.ctrip_arrTime = ctrip_arrTime;
        this.ctrip_pickup = ctrip_pickup;
        this.ctrip_dropoff = ctrip_dropoff;
    }

    public int getCtrip_id() {
        return ctrip_id;
    }

    public void setCtrip_id(int ctrip_id) {
        this.ctrip_id = ctrip_id;
    }

    public String getCtrip_title() {
        return ctrip_title;
    }

    public void setCtrip_title(String ctrip_title) {
        this.ctrip_title = ctrip_title;
    }

    public String getCtrip_description() {
        return ctrip_description;
    }

    public void setCtrip_description(String ctrip_description) {
        this.ctrip_description = ctrip_description;
    }

    public String getCtrip_depDate() {
        return ctrip_depDate;
    }

    public void setCtrip_depDate(String ctrip_depDate) {
        this.ctrip_depDate = ctrip_depDate;
    }

    public String getCtrip_arrDate() {
        return ctrip_arrDate;
    }

    public void setCtrip_arrDate(String ctrip_arrDate) {
        this.ctrip_arrDate = ctrip_arrDate;
    }

    public String getCtrip_depTime() {
        return ctrip_depTime;
    }

    public void setCtrip_depTime(String ctrip_depTime) {
        this.ctrip_depTime = ctrip_depTime;
    }

    public String getCtrip_arrTime() {
        return ctrip_arrTime;
    }

    public void setCtrip_arrTime(String ctrip_arrTime) {
        this.ctrip_arrTime = ctrip_arrTime;
    }

    public String getCtrip_pickup() {
        return ctrip_pickup;
    }

    public void setCtrip_pickup(String ctrip_pickup) {
        this.ctrip_pickup = ctrip_pickup;
    }

    public String getCtrip_dropoff() {
        return ctrip_dropoff;
    }

    public void setCtrip_dropoff(String ctrip_dropoff) {
        this.ctrip_dropoff = ctrip_dropoff;
    }
}