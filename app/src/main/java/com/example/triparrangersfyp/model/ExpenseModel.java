package com.example.triparrangersfyp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpenseModel {
    @SerializedName("Texpense_ID ")
    @Expose
    private int Texpense_ID ;

    @SerializedName("Trip_ID")
    @Expose
    private int Trip_ID;

    @SerializedName("Member_ID")
    @Expose
    private int Member_ID;

    @SerializedName("Title")
    @Expose
    private String Title;

    @SerializedName("Expense_Description")
    @Expose
    private String Expense_Description;

    @SerializedName("Price")
    @Expose
    private int Price;

    @SerializedName("Expense_DateTime")
    @Expose
    private String Expense_DateTime;

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

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("error")
    @Expose
    private boolean error;

    @SerializedName("message")
    @Expose
    private String message;


    public ExpenseModel() {
    }

    public ExpenseModel(int texpense_ID, int trip_ID, int member_ID, String title, String expense_Description, int price, String expense_DateTime, String code, boolean error, String message) {
        Texpense_ID = texpense_ID;
        Trip_ID = trip_ID;
        Member_ID = member_ID;
        Title = title;
        Expense_Description = expense_Description;
        Price = price;
        Expense_DateTime = expense_DateTime;
        this.code = code;
        this.error = error;
        this.message = message;
    }

    public ExpenseModel(int texpense_ID, int trip_ID, int member_ID, String title, String expense_Description, int price, String expense_DateTime, int c_id, String c_name, String c_phone, String c_cnic) {
        Texpense_ID = texpense_ID;
        Trip_ID = trip_ID;
        Member_ID = member_ID;
        Title = title;
        Expense_Description = expense_Description;
        Price = price;
        Expense_DateTime = expense_DateTime;
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_phone = c_phone;
        this.c_cnic = c_cnic;

    }


    public ExpenseModel(int texpense_ID, String title, String expense_Description, String expense_DateTime, int price) {
        Texpense_ID = texpense_ID;
        Title = title;
        Expense_Description = expense_Description;
        Expense_DateTime = expense_DateTime;
        Price = price;
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

    public int getTexpense_ID() {
        return Texpense_ID;
    }

    public void setTexpense_ID(int texpense_ID) {
        Texpense_ID = texpense_ID;
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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getExpense_Description() {
        return Expense_Description;
    }

    public void setExpense_Description(String expense_Description) {
        Expense_Description = expense_Description;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getExpense_DateTime() {
        return Expense_DateTime;
    }

    public void setExpense_DateTime(String expense_DateTime) {
        Expense_DateTime = expense_DateTime;
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
