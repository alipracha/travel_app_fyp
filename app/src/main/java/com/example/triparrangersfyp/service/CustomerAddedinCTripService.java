package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.util.Endpoint;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CustomerAddedinCTripService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.Get_Customers_Custom)
    Call<JsonObject> getCustomCustomersData(
            @Field("Member_ID") int Member_ID,
            @Field("Statuss") String Statuss
    );
}
