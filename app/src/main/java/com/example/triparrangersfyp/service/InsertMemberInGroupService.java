package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.CustomTripMember;
import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.model.FeedbackModel;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InsertMemberInGroupService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.Insert_Member_Group)
    Call<CustomTripMember> insertMemberInGroup(
            @Field("Trip_ID") int Trip_ID,
            @Field("GroupAdmin_ID") int GroupAdmin_ID,
            @Field("Member_ID") int Member_ID,
            @Field("Statuss") String Statuss
    );
}
