package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Tracking;
import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InsertLocationService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.GET_LOCATION)
    Call<Tracking> getLocation(
            @Field("Latitude") double Latitude,
            @Field("Longitude") double Longitude,
            @Field("Customer_ID") double Customer_ID
    );
}
