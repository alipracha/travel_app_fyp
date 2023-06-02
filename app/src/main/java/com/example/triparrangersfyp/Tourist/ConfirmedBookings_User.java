package com.example.triparrangersfyp.Tourist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.triparrangersfyp.Adapter.TravelAgencyBookingAdapter;
import com.example.triparrangersfyp.Adapter.UserBookingsAdapter;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.TravelAgency.RequestedTrips_TA;
import com.example.triparrangersfyp.model.RequestBooking;
import com.example.triparrangersfyp.service.GetTouristsBookingService;
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

public class ConfirmedBookings_User extends AppCompatActivity {

    List<RequestBooking> RequestList = new ArrayList<>();
    ProgressDialog progressDialog;
    UserBookingsAdapter adapter;
    RequestBooking request;
    ListView RequestTrip_LV;
    TinyDB tinyDB;
    Button Searchbutton;

    String[] status = {"Accepted", "Rejected", "Pending", "Completed"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_bookings_user);

        RequestTrip_LV = findViewById(R.id.ListViewww);
        Spinner spinner = findViewById(R.id.Spinner);
        Searchbutton = findViewById(R.id.SearchBtn);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, status);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    GetTripsRequest("A");
                } else if (position == 1) {
                    GetTripsRequest("CA");
                } else if (position == 2) {
                    GetTripsRequest("P");
                } else if (position == 3) {
                    GetTripsRequest("C");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tinyDB = new TinyDB(this);


    }

    private void GetTripsRequest(String status) {
        RequestList.clear();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        request = new RequestBooking();
        GetTouristsBookingService service = RetrofitClient.getClient().create(GetTouristsBookingService.class);
        Call<JsonObject> call = service.getUsersBookingsData(tinyDB.getInt("CUSTOMER_ID"), status);

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
                        adapter = new UserBookingsAdapter(RequestList,
                                ConfirmedBookings_User.this);
                        RequestTrip_LV.setAdapter(adapter);

                    } catch (JSONException e) {
                        RequestTrip_LV.setAdapter(null);
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ConfirmedBookings_User.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}