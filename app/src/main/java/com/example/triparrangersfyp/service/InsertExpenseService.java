package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.ExpenseModel;
import com.example.triparrangersfyp.model.FeedbackModel;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InsertExpenseService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.Insert_Expense)
    Call<ExpenseModel> addExpense(
            @Field("Trip_ID") int Trip_ID,
            @Field("Member_ID") int Member_ID,
            @Field("Title") String Title,
            @Field("Expense_Description") String Expense_Description,
            @Field("Price") int Price
    );
}
