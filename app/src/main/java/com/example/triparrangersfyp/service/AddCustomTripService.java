package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.CustomTrip;
import com.example.triparrangersfyp.util.Endpoint;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AddCustomTripService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.ADD_CUSTOM_TRIP)
    Call<CustomTrip> Addcustomtrip(
            @Field("ctrip_title") String ctrip_title,
            @Field("ctrip_description") String ctrip_description,
            @Field("ctrip_depDate") String ctrip_depDate,
            @Field("ctrip_arrDate") String ctrip_arrDate,
            @Field("ctrip_depTime") String ctrip_depTime,
            @Field("ctrip_arrTime") String ctrip_arrTime,
            @Field("ctrip_pickup") String ctrip_pickup,
            @Field("ctrip_dropoff") String ctrip_dropoff,
            @Field("customer_id") int customer_id);
}
