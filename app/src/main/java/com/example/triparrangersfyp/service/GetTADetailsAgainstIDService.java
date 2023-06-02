package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.util.Endpoint;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetTADetailsAgainstIDService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.GET_TA_DETAILS_AGAINST_ID)
    Call<TravelAgency> getTADetailsAgainstID(
            @Field("travel_agency_id") int travel_agency_id
    );
}
