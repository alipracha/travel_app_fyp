package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.util.Endpoint;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GetComplainsTravelAgencyService {
    @Headers({"Accpet: application/json"})
    @GET(Endpoint.GET_COMPLAINTS_TRAVELAGENCY)
    Call<JsonObject> getComplainsTA();
}
