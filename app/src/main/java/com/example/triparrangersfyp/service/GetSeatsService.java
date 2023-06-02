package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.util.Endpoint;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetSeatsService {
        @Headers({"Accept: application/json"})
        @FormUrlEncoded
        @POST(Endpoint.GET_SEATS)
        Call<Trips> getSeatsList(
                @Field("trip_id") int trip_id
        );
    }

