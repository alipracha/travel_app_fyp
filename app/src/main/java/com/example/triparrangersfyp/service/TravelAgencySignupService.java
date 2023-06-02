package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.util.Endpoint;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface TravelAgencySignupService {
    @Headers({"Accept: application/json"})
    @Multipart
    @POST(Endpoint.TRAVEL_AGENECY_SIGNUP)
    Call<TravelAgency> TAnewAccount(
            @Part("ta_name") RequestBody ta_name,
            @Part("ta_email") RequestBody ta_email,
            @Part("ta_password") RequestBody ta_password,
            @Part("ta_phone") RequestBody ta_phone,
            @Part("ta_cnic") RequestBody ta_cnic,
            @Part MultipartBody.Part ta_image);
}
