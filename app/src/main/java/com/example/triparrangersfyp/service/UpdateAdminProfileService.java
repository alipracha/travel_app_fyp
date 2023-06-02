package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Admin;
import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UpdateAdminProfileService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.UPDATE_ADMIN_PROFILE)
    Call<Admin> updateAdminData(
            @Field("a_id") int a_id,
            @Field("a_email") String a_email


    );
}
