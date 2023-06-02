package com.example.triparrangersfyp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.triparrangersfyp.Adapter.TravelAgencyReportAdapter;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.model.Report;
import com.example.triparrangersfyp.service.ReportTravelAgencyWiseService;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportTravelAgencyActivity extends AppCompatActivity {

    Customer customer;
    ProgressDialog progressDialog;
    ListView travel_agency_report_LV;
    List<Report> reportsList = new ArrayList<>();
    List<String> agencyName = new ArrayList<>();
    TravelAgencyReportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_travel_agency);
        travel_agency_report_LV = findViewById(R.id.travel_agency_report_LV);
        getTAreport();
    }

    private void getTAreport() {
        agencyName.clear();
        reportsList.clear();
        customer = new Customer();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        ReportTravelAgencyWiseService services = RetrofitClient.getClient().create(ReportTravelAgencyWiseService.class);
        Call<JsonObject> call = services.getTATrips();
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
                                agencyName.add(data.getString("ta_name"));
                            }

                            ManageReport();

                        } catch (Exception exception) {

                            exception.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    private void ManageReport() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String s : agencyName) {
            map.put(s, Collections.frequency(agencyName, s));
        }
        System.out.println(map);
        for (String key : map.keySet()) {
            int value = map.get(key);
            reportsList.add(new Report(key, value));
        }
        adapter = new TravelAgencyReportAdapter(reportsList, getApplicationContext());
        travel_agency_report_LV.setAdapter(adapter);
    }
}