package com.example.triparrangersfyp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Complain {

    @SerializedName("Complaint_ID")
    @Expose
    private int Complaint_ID;

    @SerializedName("Complaint_Status")
    @Expose
    private String Complaint_Status;

    @SerializedName("date_time")
    @Expose
    private String date_time;

    @SerializedName("Complaint_Subject")
    @Expose
    private String Complaint_Subject;

    @SerializedName("Complaint_Description")
    @Expose
    private String Complaint_Description;

    @SerializedName("Sender_ID")
    @Expose
    private int Sender_ID;

    @SerializedName("Receiver_ID")
    @Expose
    private int Receiver_ID;

    @SerializedName("Sender_Type")
    @Expose
    private int Sender_Type;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("error")
    @Expose
    private boolean error;

    @SerializedName("message")
    @Expose
    private String message;

    public Complain() {
    }

    public Complain(int complaint_ID, String complaint_Status, String date_time, String complaint_Subject, String complaint_Description, int sender_ID, int receiver_ID, int sender_Type, String code, boolean error, String message) {
        Complaint_ID = complaint_ID;
        Complaint_Status = complaint_Status;
        this.date_time = date_time;
        Complaint_Subject = complaint_Subject;
        Complaint_Description = complaint_Description;
        Sender_ID = sender_ID;
        Receiver_ID = receiver_ID;
        Sender_Type = sender_Type;
        this.code = code;
        this.error = error;
        this.message = message;
    }

    public int getComplaint_ID() {
        return Complaint_ID;
    }

    public void setComplaint_ID(int complaint_ID) {
        Complaint_ID = complaint_ID;
    }

    public String getComplaint_Status() {
        return Complaint_Status;
    }

    public void setComplaint_Status(String complaint_Status) {
        Complaint_Status = complaint_Status;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getComplaint_Subject() {
        return Complaint_Subject;
    }

    public void setComplaint_Subject(String complaint_Subject) {
        Complaint_Subject = complaint_Subject;
    }

    public String getComplaint_Description() {
        return Complaint_Description;
    }

    public void setComplaint_Description(String complaint_Description) {
        Complaint_Description = complaint_Description;
    }

    public int getSender_ID() {
        return Sender_ID;
    }

    public void setSender_ID(int sender_ID) {
        Sender_ID = sender_ID;
    }

    public int getReceiver_ID() {
        return Receiver_ID;
    }

    public void setReceiver_ID(int receiver_ID) {
        Receiver_ID = receiver_ID;
    }

    public int getSender_Type() {
        return Sender_Type;
    }

    public void setSender_Type(int sender_Type) {
        Sender_Type = sender_Type;
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