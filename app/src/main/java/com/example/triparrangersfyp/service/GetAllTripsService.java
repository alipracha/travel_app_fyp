package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.util.Endpoint;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GetAllTripsService {
    @Headers({"Accept: application/json"})
    @GET(Endpoint.GET_ALL_TRIPS)
    Call<JsonObject> getTripsList();
}
