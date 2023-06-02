package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UpdateTravelAgencyDataService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.UPDATE_TRAVELAGENCY_PROFILE)
    Call<TravelAgency> updateTAData(
            @Field("ta_id") int ta_id,
            @Field("ta_name") String ta_name,
            @Field("ta_email") String ta_email,
            @Field("ta_phone") String ta_phone,
            @Field("ta_cnic") String ta_cnic

    );
}
