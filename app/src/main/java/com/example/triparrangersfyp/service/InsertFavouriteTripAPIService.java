package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Booking;
import com.example.triparrangersfyp.model.FavouriteTrips;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InsertFavouriteTripAPIService {
    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST(Endpoint.Insert_Fvt_Trip)
    Call<FavouriteTrips> InsertFavTrip(
            @Field("fav_trip_id") int fav_trip_id,
            @Field("fav_c_id") int fav_c_id
    );

}


