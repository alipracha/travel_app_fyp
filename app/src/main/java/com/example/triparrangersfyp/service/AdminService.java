package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Admin;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AdminService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.ADMIN_LOGIN)
    Call<Admin> Adminlogin(
            @Field("a_email") String a_email,
            @Field("a_password") String a_password
    );
}

