package com.example.triparrangersfyp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.service.ReportBookingService;
import com.example.triparrangersfyp.service.ReportRegisteredUserService;
import com.google.gson.JsonObject;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportTripBookingsActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    TextView tvUPending, tvCanclled, tvUAccepted, tvUCompleted;
    List<String> statusList = new ArrayList<>();
    int accepted = 0;
    int pending = 0;
    int completed = 0;
    PieChart pieChart;
    int cancelled = 0;
    int total_users = 0;
    double Percentage_A = 0.0,
            Percentage_P = 0.0,
            Percentage_C = 0.0,
            Percentage_CA = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_trip_bookings);
        tvUPending = findViewById(R.id.tvUP);
        tvCanclled = findViewById(R.id.tvUR);
        tvUAccepted = findViewById(R.id.tvUA);
        tvUCompleted = findViewById(R.id.tvUC);
        pieChart = findViewById(R.id.piechart);
        getBookingreport();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setUserData();
            }
        }, 1000);

    }

    private void getBookingreport() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        ReportBookingService services = RetrofitClient.getClient().create(ReportBookingService.class);
        Call<JsonObject> call = services.GetBooking();
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
                                statusList.add(data.getString("b_status"));
                                if (data.getString("b_status").equals("A")) {
                                    accepted++;
                                } else if (data.getString("b_status").equals("P")) {
                                    pending++;
                                } else if (data.getString("b_status").equals("CA")) {
                                    cancelled++;
                                } else if (data.getString("b_status").equals("C")) {
                                    completed++;
                                }

                            }

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


    private void setUserData() {
        // Set the percentage of orders

        total_users = statusList.size();

        Percentage_A = ((Double.parseDouble(String.valueOf(accepted)) / Double.parseDouble(String.valueOf(total_users))) * 100);
        Percentage_P = ((Double.parseDouble(String.valueOf(pending)) / Double.parseDouble(String.valueOf(total_users))) * 100);
        Percentage_CA = ((Double.parseDouble(String.valueOf(cancelled)) / Double.parseDouble(String.valueOf(total_users))) * 100);
        Percentage_C = ((Double.parseDouble(String.valueOf(completed)) / Double.parseDouble(String.valueOf(total_users))) * 100);

        tvUPending.setText(new DecimalFormat("##.##").format(Percentage_P) + "%");
        tvCanclled.setText(new DecimalFormat("##.##").format(Percentage_CA) + "%");
        tvUAccepted.setText(new DecimalFormat("##.##").format(Percentage_A) + "%");
        tvUCompleted.setText(new DecimalFormat("##.##").format(Percentage_C) + "%");

        pieChart.addPieSlice(
                new PieModel(
                        "Accepted",
                        accepted,
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Canceled",
                        cancelled,
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Complete",
                        completed,
                        Color.parseColor("#29B6F6")));
        pieChart.addPieSlice(
                new PieModel(
                        "Pending",
                        pending,
                        Color.parseColor("#FFA726")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}