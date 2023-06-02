package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.NotificationModel;
import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.util.Endpoint;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GetNotificationService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.GET_NOTIFICATION_AGAINST_ID)
    Call<JsonObject> getNotifications(
            @Field("notic_for_id") int notic_for_id
    );
}
