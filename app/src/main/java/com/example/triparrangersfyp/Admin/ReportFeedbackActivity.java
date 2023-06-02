package com.example.triparrangersfyp.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.service.ReportFeedbackService;
import com.google.gson.JsonObject;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportFeedbackActivity extends AppCompatActivity {
    TextView Positive, Negative;
    PieChart pieChart;
    Customer customer;
    ProgressDialog progressdialog;
    int positive_count = 0, negative_count = 0, total_users = 0;
    double Percentage_Positive = 0.0,
            Percentage_Negative = 0.0;
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_feedback);


        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportFeedbackActivity.this.finish();
            }
        });
        Positive = findViewById(R.id.PositiveTV);
        Negative = findViewById(R.id.NegativeTV);

        pieChart = findViewById(R.id.piechart1);
        getFeedbackreport();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setUserData();
            }
        }, 1000);


    }

    private void getFeedbackreport() {
        customer = new Customer();
        progressdialog = new ProgressDialog(this);
        progressdialog.setMessage("Please Wait");
        progressdialog.show();
        ReportFeedbackService services = RetrofitClient.getClient().create(ReportFeedbackService.class);
        Call<JsonObject> call = services.GetFeedback();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    progressdialog.dismiss();
                    if (response.code() == 200) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("records");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject data = jsonArray.getJSONObject(i);
                                if (data.getDouble("feedback_star") > 3.0) {
                                    positive_count++;

                                } else if (data.getDouble("feedback_star") < 3.0)  {
                                    negative_count++;
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

        total_users = (positive_count) + (negative_count) ;

        Percentage_Positive = ((Double.parseDouble(String.valueOf(positive_count)) / Double.parseDouble(String.valueOf(total_users))) * 100);
        Percentage_Negative = ((Double.parseDouble(String.valueOf(negative_count)) / Double.parseDouble(String.valueOf(total_users))) * 100);


        Positive.setText(new DecimalFormat("##.##").format(Percentage_Positive) + "%");
        Negative.setText(new DecimalFormat("##.##").format(Percentage_Negative) + "%");

        pieChart.addPieSlice(
                new PieModel(
                        "Positive",
                        positive_count,
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Negative",
                        negative_count,
                        Color.parseColor("#EF5350")));


        // To animate the pie chart
        pieChart.startAnimation();
    }
}