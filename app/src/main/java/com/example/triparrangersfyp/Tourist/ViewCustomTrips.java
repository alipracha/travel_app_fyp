package com.example.triparrangersfyp.Tourist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.triparrangersfyp.Adapter.CustomTripAdapter;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.CustomTrip;
import com.example.triparrangersfyp.service.GetCustomTripsService;
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

public class ViewCustomTrips extends AppCompatActivity {

    ListView listView;
    ProgressDialog progressDialog;
    List<CustomTrip> ctripsList = new ArrayList<>();
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_custom_trips);

        listView = findViewById(R.id.CustomTripListView);

        tinyDB = new TinyDB(this);
        GetCustomTrips(tinyDB.getInt("CUSTOMER_ID"));

    } private void GetCustomTrips(int C_id) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        GetCustomTripsService service = RetrofitClient.getClient().create(GetCustomTripsService.class);
        Call<JsonObject> call = service.getCustomTrips(C_id);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.code() == 200) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());

                            JSONArray jsonArray = jsonObject.getJSONArray("records");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                ctripsList.add(new CustomTrip(

                                        data.getInt("ctrip_id"),
                                        data.getString("ctrip_title"),
                                        data.getString("ctrip_description"),
                                        data.getString("ctrip_depDate"),
                                        data.getString("ctrip_arrDate"),
                                        data.getString("ctrip_depTime"),
                                        data.getString("ctrip_arrTime"),
                                        data.getString("ctrip_pickup"),
                                        data.getString("ctrip_dropoff"),
                                        data.getString("ctrip_status")

                                ));
                            }

                            CustomTripAdapter adapter = new CustomTripAdapter(ViewCustomTrips.this, ctripsList);
                            listView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewCustomTrips.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}






