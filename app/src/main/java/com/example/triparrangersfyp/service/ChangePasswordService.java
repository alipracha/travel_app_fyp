package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChangePasswordService {
    @Headers({"Accept:application/json"})
    @FormUrlEncoded
    @POST(Endpoint.UPDATE_TRAVELAGENCY_PASSWORD)
    Call<TravelAgency> UpdateTravelAgencyPassword(
            @Field("ta_id") int ta_id,
            @Field("ta_password") String ta_password,
            @Field("new_password") String new_password);
}
