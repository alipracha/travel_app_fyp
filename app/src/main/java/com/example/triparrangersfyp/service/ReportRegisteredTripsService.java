package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.util.Endpoint;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ReportRegisteredTripsService {
    @Headers({"Accept: application/json"})
    @GET(Endpoint.GET_ALL_REGISTERED_TRIPS)
    Call<JsonObject> getTRIPSList();
}
