package com.example.triparrangersfyp.nearbyareas;

import com.example.triparrangersfyp.nearbyareas.mapmodel.PlacesResults;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleMapAPI {
    @GET(Endpoint.NEARBY_PLACES)
    Call<PlacesResults> getNearBy(
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("type") String type,
            @Query("keyword") String keyword,
            @Query("key") String key
    );
}
