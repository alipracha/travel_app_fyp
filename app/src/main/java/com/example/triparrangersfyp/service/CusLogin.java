package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CusLogin {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.USER_LOGIN)
    Call<Customer> Login(
            @Field("c_email") String c_email,
            @Field("c_password") String c_password
    );
}