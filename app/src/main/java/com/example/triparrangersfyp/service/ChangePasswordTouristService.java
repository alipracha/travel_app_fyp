package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ChangePasswordTouristService {
    @Headers({"Accept:application/json"})
    @FormUrlEncoded
    @POST(Endpoint.UPDATE_CUSTOMER_PASSWORD)
    Call<Customer> UpdateCustomerPassword(
            @Field("c_id") int c_id,
            @Field("c_password") String c_password,
            @Field("new_password") String new_password);
}
