package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Chat;
import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.util.Endpoint;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface SendImageChatService {
    @Headers({"Accept: application/json"})
    @Multipart
    @POST(Endpoint.Send_Image)
    Call<Chat> SendImage(
            @Part("Chat_SenderID") RequestBody Chat_SenderID,
            @Part("Chat_ReceiverID") RequestBody Chat_ReceiverID,
            @Part("sender_type") RequestBody sender_type,
            @Part("Trip_ID") RequestBody Trip_ID,
            @Part MultipartBody.Part Imagee);
}
