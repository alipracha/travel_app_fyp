package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Booking;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InsertBookingService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.Insert_Booking)
    Call<Booking> InsertBooking(
            @Field("b_customer_id") int b_customer_id,
            @Field("b_travel_agency_id") int b_travel_agency_id,
            @Field("b_trip_id") int b_trip_id,
            @Field("b_price") String b_price,
            @Field("b_seats") String b_seats
    );



}
