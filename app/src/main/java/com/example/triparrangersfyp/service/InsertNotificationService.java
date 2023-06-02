package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.FavouriteTrips;
import com.example.triparrangersfyp.model.NotificationModel;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InsertNotificationService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.Insert_Notification)
    Call<NotificationModel> InsertNotification(
            @Field("notic_title") String notic_title,
            @Field("typee") String typee,
            @Field("typeid") int typeid,
            @Field("notic_for") String notic_for,
            @Field("notic_for_id") int notic_for_id,
            @Field("price") String price
    );

}
