package com.example.triparrangersfyp.service;

import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.util.Endpoint;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AddTripService {
    @Headers({"Accept: application/json"})
    @Multipart
    @POST(Endpoint.ADD_TRIP)
    Call<Trips> AddTrip(
            @Part("trip_title") RequestBody trip_title,
            @Part("trip_description") RequestBody trip_description,
            @Part("trip_depDate") RequestBody trip_depDate,
            @Part("trip_arrDate") RequestBody trip_arrDate,
            @Part("trip_depTime") RequestBody trip_depTime,
            @Part("trip_arrTime") RequestBody trip_arrTime,
            @Part("trip_pickup") RequestBody trip_pickup,
            @Part("trip_dropoff") RequestBody trip_dropoff,
            @Part("trip_numSeats") RequestBody trip_numSeats,
            @Part("trip_payment") RequestBody trip_payment,
            @Part("travel_agency_id") RequestBody travel_agency_id,
            @Part MultipartBody.Part trip_image);
}

