package com.example.triparrangersfyp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomTripMember {
    @SerializedName("CTripRequest_ID")
    @Expose
    private int CTripRequest_ID;

    @SerializedName("Trip_ID")
    @Expose
    private int Trip_ID;

    @SerializedName("GroupAdmin_ID")
    @Expose
    private int GroupAdmin_ID;

    @SerializedName("Member_ID")
    @Expose
    private int Member_ID;

    @SerializedName("Statuss")
    @Expose
    private String Statuss;

    @SerializedName("Customer_Name")
    @Expose
    private String Customer_Name;

    @SerializedName("Customer_Phone")
    @Expose
    private String Customer_Phone;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("error")
    @Expose
    private boolean error;

    @SerializedName("message")
    @Expose
    private String message;

    public CustomTripMember() {
    }

    public CustomTripMember(int CTripRequest_ID, int trip_ID, int groupAdmin_ID, int member_ID, String statuss, String customer_Name, String customer_Phone, String code, boolean error, String message) {
        this.CTripRequest_ID = CTripRequest_ID;
        Trip_ID = trip_ID;
        GroupAdmin_ID = groupAdmin_ID;
        Member_ID = member_ID;
        Statuss = statuss;
        Customer_Name = customer_Name;
        Customer_Phone = customer_Phone;
        this.code = code;
        this.error = error;
        this.message = message;
    }

    public CustomTripMember(String customer_Name, String customer_Phone) {
        Customer_Name = customer_Name;
        Customer_Phone = customer_Phone;
    }

    public int getCTripRequest_ID() {
        return CTripRequest_ID;
    }

    public void setCTripRequest_ID(int CTripRequest_ID) {
        this.CTripRequest_ID = CTripRequest_ID;
    }

    public int getTrip_ID() {
        return Trip_ID;
    }

    public void setTrip_ID(int trip_ID) {
        Trip_ID = trip_ID;
    }

    public int getGroupAdmin_ID() {
        return GroupAdmin_ID;
    }

    public void setGroupAdmin_ID(int groupAdmin_ID) {
        GroupAdmin_ID = groupAdmin_ID;
    }

    public int getMember_ID() {
        return Member_ID;
    }

    public void setMember_ID(int member_ID) {
        Member_ID = member_ID;
    }

    public String getStatuss() {
        return Statuss;
    }

    public void setStatuss(String statuss) {
        Statuss = statuss;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getCustomer_Phone() {
        return Customer_Phone;
    }

    public void setCustomer_Phone(String customer_Phone) {
        Customer_Phone = customer_Phone;
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
