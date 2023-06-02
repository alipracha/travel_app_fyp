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

import com.example.triparrangersfyp.Adapter.CustomizedTripsBookedAdapter;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.CustomTrip;
import com.example.triparrangersfyp.service.CustomerAddedinCTripService;
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

public class CustomizedTripsActivity extends AppCompatActivity {

    List<CustomTrip> customList = new ArrayList<>();
    ProgressDialog progressDialog;
    CustomizedTripsBookedAdapter adapter;
    CustomTrip request;
    ListView customTrip_LV;
    TinyDB tinyDB;
    Button Searchbutton;
    String[] status = {"Pending", "Approved", "Completed", "Blocked"};

    @Override

        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized_trips);

        customTrip_LV = findViewById(R.id.CustomListVieww);
        Spinner spinner = findViewById(R.id.CustomSpinner);
        Searchbutton = findViewById(R.id.CustomSearchBtn);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, status);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    GetTripsRequest("P");
                } else if (position == 1) {
                    GetTripsRequest("A");
                } else if (position == 2) {
                    GetTripsRequest("C");
                }
                else if (position == 3) {
                    GetTripsRequest("B");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tinyDB = new TinyDB(this);


    }

    private void GetTripsRequest(String status) {
        customList.clear();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        request = new CustomTrip();
        CustomerAddedinCTripService service = RetrofitClient.getClient().create(CustomerAddedinCTripService.class);
        Call<JsonObject> call = service.getCustomCustomersData(tinyDB.getInt("CUSTOMER_ID"), status);

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

                            customList.add(new CustomTrip
                                    (
                                            data.getInt("CTripRequest_ID"),
                                            data.getInt("ctrip_id"),
                                            data.getString("ctrip_title"),
                                            data.getString("ctrip_description"),
                                            data.getString("ctrip_depDate"),
                                            data.getString("ctrip_arrDate"),
                                            data.getString("ctrip_arrTime"),
                                            data.getString("ctrip_depTime"),
                                            data.getString("ctrip_pickup"),
                                            data.getString("ctrip_dropoff"),
                                            data.getInt("customer_id"),
                                            data.getInt("Trip_ID"),
                                            data.getInt("Member_ID"),
                                            data.getString("Statuss")


                                    ));
                        }
                        adapter = new CustomizedTripsBookedAdapter(customList,
                                CustomizedTripsActivity.this);
                        customTrip_LV.setAdapter(adapter);

                    } catch (JSONException e) {
                        customTrip_LV.setAdapter(null);
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CustomizedTripsActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}