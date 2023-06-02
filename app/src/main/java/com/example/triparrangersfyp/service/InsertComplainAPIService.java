package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Booking;
import com.example.triparrangersfyp.model.Complain;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InsertComplainAPIService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.Insert_Complain)
    Call<Complain> InsertComplain(
            @Field("Complaint_Subject") String Complaint_Subject,
            @Field("Complaint_Description") String Complaint_Description,
            @Field("Sender_ID") int Sender_ID,
            @Field("Receiver_ID") int Receiver_ID,
            @Field("Sender_Type") String Sender_Type
    );
}
