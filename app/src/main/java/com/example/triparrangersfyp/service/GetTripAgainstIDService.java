package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.util.Endpoint;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetTripAgainstIDService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.GET_TRIPS_AGAINST_ID)
    Call<JsonObject> getTrips(
            @Field("travel_agency_id") int travel_agency_id
    );

}
