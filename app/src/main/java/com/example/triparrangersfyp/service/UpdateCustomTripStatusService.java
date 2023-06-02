package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.CustomTrip;
import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UpdateCustomTripStatusService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.UPDATE_CUSTOM_TRIP_STATUS)
    Call<CustomTrip> updateCustomTripStatus(
            @Field("CTripRequest_ID") int CTripRequest_ID,
            @Field("Statuss") String Statuss
    );
}
