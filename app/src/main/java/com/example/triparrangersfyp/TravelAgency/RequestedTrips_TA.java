package com.example.triparrangersfyp.TravelAgency;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.triparrangersfyp.Adapter.TravelAgencyBookingAdapter;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.RequestBooking;
import com.example.triparrangersfyp.service.GetTravelAgencyBookingAPIService;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestedTrips_TA extends AppCompatActivity {

    List<RequestBooking> RequestList = new ArrayList<>();
    ProgressDialog progressDialog;
    TravelAgencyBookingAdapter adapter;
    RequestBooking request;
    ListView RequestTrip_LV;
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_trips_ta);
        RequestTrip_LV = findViewById(R.id.ListVieww);
        tinyDB = new TinyDB(this);
        GetTripsRequest();
    }

    private void GetTripsRequest() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        request = new RequestBooking();
        GetTravelAgencyBookingAPIService service = RetrofitClient.getClient().create(GetTravelAgencyBookingAPIService.class);
        Call<JsonObject> call = service.getBookingsData(tinyDB.getInt("TRAVEL_AGENCY_ID"), "P");
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());

                        JSONArray jsonArray = jsonObject.getJSONArray("records");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject data = jsonArray.getJSONObject(i);

                            RequestList.add(new RequestBooking
                                    (data.getInt("c_id"),
                                    data.getString("c_name"),
                                    data.getString("c_phone"),
                                    data.getString("c_cnic"),
                                    data.getInt("trip_id"),
                                    data.getString("trip_title"),
                                    data.getString("trip_description"),
                                    data.getString("trip_depDate"),
                                    data.getString("trip_arrDate"),
                                    data.getString("trip_arrTime"),
                                    data.getString("trip_depTime"),
                                    data.getString("trip_pickup"),
                                    data.getString("trip_dropoff"),
                                    data.getString("trip_image"),
                                    data.getInt("b_id"),
                                    data.getString("b_price"),
                                    data.getString("b_seats"),
                                    data.getString("b_status"),
                                    data.getString("b_datetime")

                            ));


                        }
                        adapter = new TravelAgencyBookingAdapter(RequestList,
                                RequestedTrips_TA.this);
                        RequestTrip_LV.setAdapter(adapter);

                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }
                }
                
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RequestedTrips_TA.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}