package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UpdateUserStatusService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.UPDATE_USER_STATUS)
    Call<Customer>updateUser(
            @Field("c_id") int c_id,
            @Field("c_status") String c_status
    );

}
