package com.example.triparrangersfyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.triparrangersfyp.Adapter.TravelAgencyBookingAdapter;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.TravelAgency.AddTrip;
import com.example.triparrangersfyp.TravelAgency.RequestedTrips_TA;
import com.example.triparrangersfyp.model.Booking;
import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.model.RequestBooking;
import com.example.triparrangersfyp.service.GetTravelAgencyBookingAPIService;
import com.example.triparrangersfyp.service.ReportBookingService;
import com.example.triparrangersfyp.service.ReportDatewiseService;
import com.example.triparrangersfyp.service.ReportRegisteredTripsService;
import com.google.gson.JsonObject;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatewiseReporttt extends AppCompatActivity {

    TextView start, end;
    DatePickerDialog.OnDateSetListener setListener1, setListener2;
    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
    Button search;
    String firstDate, SecondDate;
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
        setContentView(R.layout.activity_datewise_reporttt);
        start = findViewById(R.id.startDate);
        search=findViewById(R.id.DatewiseBtn);
        end = findViewById(R.id.endDate);
        tvUPending = findViewById(R.id.PendingTV);
        tvCanclled = findViewById(R.id.CancelledTV);
        tvUAccepted = findViewById(R.id.AcceptedTV);
        tvUCompleted = findViewById(R.id.CompletedTV);
        pieChart = findViewById(R.id.piecharttt);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DatewiseReporttt.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener1, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "-" + month + "-" + dayOfMonth;
                firstDate = date;
                start.setText(date);
            }
        };

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(DatewiseReporttt.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener2, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "-" + month + "-" + dayOfMonth;
                SecondDate = date;
                end.setText(date);
            }
        };

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDatewisereport(firstDate, SecondDate);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setUserData();
                    }
                }, 1000);

            }
        });


    }

    private void getDatewisereport(String date1, String date2) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        ReportDatewiseService services = RetrofitClient.getClient().create(ReportDatewiseService.class);
        Call<JsonObject> call = services.getDatewiseReport(date1, date2);
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