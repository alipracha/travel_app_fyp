package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UpdateTripsDataService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.UPDATE_TRIP_RECORD)
    Call<Trips> updateTripData(
            @Field("trip_id") int trip_id,
            @Field("trip_title") String trip_title,
            @Field("trip_description") String trip_description,
            @Field("trip_depDate") String trip_depDate,
            @Field("trip_arrDate") String trip_arrDate,
            @Field("trip_depTime") String trip_depTime,
            @Field("trip_arrTime") String trip_arrTime,
            @Field("trip_pickup") String trip_pickup,
            @Field("trip_dropoff") String trip_dropoff,
            @Field("trip_numSeats") String trip_numSeats,
            @Field("trip_payment") String trip_payment

    );
}
