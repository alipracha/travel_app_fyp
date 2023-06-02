package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Booking;
import com.example.triparrangersfyp.model.RequestBooking;
import com.example.triparrangersfyp.util.Endpoint;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetTravelAgencyBookingAPIService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.GET_BOOKING_TA)
    Call<JsonObject> getBookingsData(
            @Field("b_travel_agency_id") int b_travel_agency_id,
            @Field("b_status") String b_status
    );
}
