package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TA_Login {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.TA_LOGIN)
    Call<TravelAgency> TALogin(
            @Field("ta_email") String ta_email,
            @Field("ta_password") String ta_password
    );
}
