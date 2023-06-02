package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Customer_Signup {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.INSERT_USER)
    Call<Customer> AddCustomer(
            @Field("c_name") String c_name,
            @Field("c_email") String c_email,
            @Field("c_password") String c_password,
            @Field("c_phone") String c_phone,
            @Field("c_cnic") String c_cnic
    );

}
