package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.util.Endpoint;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetTourMembersService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.GET_TOUR_MEMBERS_API)
    Call<JsonObject> getMembers(
            @Field("b_trip_id") int b_trip_id
    );

}
