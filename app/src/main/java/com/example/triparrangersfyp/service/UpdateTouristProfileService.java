package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UpdateTouristProfileService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.UPDATE_CUSTOMER_PROFILE)
    Call<Customer> updateTouristData(
            @Field("c_id") int c_id,
            @Field("c_name") String c_name,
            @Field("c_email") String c_email,
            @Field("c_phone") String c_phone,
            @Field("c_cnic") String c_cnic

    );
}
