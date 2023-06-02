package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.FeedbackModel;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AddFeedbackService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.Insert_Feedback)
    Call<FeedbackModel> addFeedback(
            @Field("feedback_star") double feedback_star,
            @Field("feedback_msg") String feedback_msg,
            @Field("feedback_c_id") int feedback_c_id
    );
}
