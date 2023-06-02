package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.CustomTrip;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UpdateCustomTripCompleteService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.UPDATE_CUSTOM_TRIP_STATUS_COMPLETE)
    Call<CustomTrip> updateCustomTripStatusComplete(
            @Field("ctrip_id") int ctrip_id,
            @Field("ctrip_status") String ctrip_status
    );
}
