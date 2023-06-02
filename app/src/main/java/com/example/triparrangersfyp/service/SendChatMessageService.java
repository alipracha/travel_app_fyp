package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Booking;
import com.example.triparrangersfyp.model.Chat;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SendChatMessageService {
        @Headers({"Accept: application/json"})
        @FormUrlEncoded
        @POST(Endpoint.Insert_Message)
        Call<Chat> InsertMessage(
                @Field("Chat_Message") String Chat_Message,
                @Field("Chat_SenderID") int Chat_SenderID,
                @Field("Chat_ReceiverID") int Chat_ReceiverID,
                @Field("sender_type") String sender_type,
                @Field("Trip_ID") int Trip_ID
        );



}
