package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UpdateTripStatusService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.UPDATE_TRIP_STATUS)
    Call<Trips> updateTrip(
            @Field("trip_id") int trip_id,
            @Field("trip_status") String trip_status
    );

}
