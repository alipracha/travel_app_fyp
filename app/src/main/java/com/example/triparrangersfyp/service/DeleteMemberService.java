package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Booking;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DeleteMemberService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.DELETE_MEMBERS_API)
    Call<Booking> deleteMember(
            @Field("b_customer_id") int b_customer_id,
            @Field("b_trip_id") int b_trip_id
    );

}
