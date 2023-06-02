package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.util.Endpoint;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetTouristsBookingService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.GET_BOOKING_TOURISTS)
    Call<JsonObject> getUsersBookingsData(
            @Field("b_customer_id") int b_customer_id,
            @Field("b_status") String b_status
    );
}
