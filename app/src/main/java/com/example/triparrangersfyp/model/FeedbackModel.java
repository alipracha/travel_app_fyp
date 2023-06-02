package com.example.triparrangersfyp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;

public class FeedbackModel {
    @SerializedName("feedback_star")
    @Expose
    private double feedback_star;

    @SerializedName("feedback_msg")
    @Expose
    private String feedback_msg;

    @SerializedName("feedback_datetime")
    @Expose
    private String feedback_datetime;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("error")
    @Expose
    private boolean error;

    @SerializedName("message")
    @Expose
    private String message;

    public FeedbackModel() {
    }

    public FeedbackModel(double feedback_star, String feedback_msg, String feedback_datetime) {
        this.feedback_star = feedback_star;
        this.feedback_msg = feedback_msg;
        this.feedback_datetime = feedback_datetime;
    }

    public double getFeedback_star() {
        return feedback_star;
    }

    public void setFeedback_star(double feedback_star) {
        this.feedback_star = feedback_star;
    }

    public String getFeedback_msg() {
        return feedback_msg;
    }

    public void setFeedback_msg(String feedback_msg) {
        this.feedback_msg = feedback_msg;
    }

    public String getFeedback_datetime() {
        return feedback_datetime;
    }

    public void setFeedback_datetime(String feedback_datetime) {
        this.feedback_datetime = feedback_datetime;
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

