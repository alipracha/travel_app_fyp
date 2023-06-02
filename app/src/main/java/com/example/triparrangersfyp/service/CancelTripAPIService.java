package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Booking;
import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CancelTripAPIService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.CANCEL_TRIP)
    Call<Booking> CancelTrip(
            @Field("b_id") int b_id,
            @Field("b_status") String b_status
    );
}
