package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.util.Endpoint;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ReportDatewiseService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.DATEWISE_REPORT)
    Call<JsonObject> getDatewiseReport(
            @Field("start_datetime") String start_datetime,
            @Field("end_datetime") String end_datetime
    );

}
