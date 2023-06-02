package com.example.triparrangersfyp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.service.ReportRegisteredUserService;
import com.google.gson.JsonObject;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import androidx.appcompat.widget.Toolbar;
import android.os.Handler;
import java.text.DecimalFormat;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportRegisteredUserActivity extends AppCompatActivity {

    TextView  tvBlocked, tvAccepted;
    PieChart pieChart;
    Customer customer;
    ProgressDialog progressdialog;
    int  UB_count = 0, UA_count = 0, total_users = 0;
    double Percentage_UB = 0.0,
            Percentage_UA = 0.0;
    String U_status;
    Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_registered_user);

        tb= findViewById(R.id.toolbars37);
        setSupportActionBar(tb);

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportRegisteredUserActivity.this.finish();
            }
        });
        tvBlocked = findViewById(R.id.tvRejected);
        tvAccepted = findViewById(R.id.tvAccepted);

        pieChart = findViewById(R.id.piechart);
        getusersreport();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setUserData();
            }
        }, 1000);


    }
    private void getusersreport() {
        customer = new Customer();
        progressdialog = new ProgressDialog(this);
        progressdialog.setMessage("Please Wait");
        progressdialog.show();
        ReportRegisteredUserService services = RetrofitClient.getClient().create(ReportRegisteredUserService.class);
        Call<JsonObject> call = services.getUsersList();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()){
                    progressdialog.dismiss();
                    if(response.code()==200){

                        try {
                            JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());
                            JSONArray jsonArray  = jsonObject.getJSONArray("records");

                            for (int i=0;i<jsonArray.length();i++) {

                                JSONObject data = jsonArray.getJSONObject(i);

                                U_status = data.getString("c_status");
                                if (U_status.equals("A")) {
                                    UA_count++;
                                }

                                if (U_status.equals("B")) {
                                    UB_count++;
                                }

                            }

                        }catch (Exception exception){

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
    private void setUserData()
    {
        // Set the percentage of orders

        total_users =  (UB_count) + (UA_count);

        Percentage_UB = ((Double.parseDouble(String.valueOf(UB_count)) / Double.parseDouble(String.valueOf(total_users))) * 100);
        Percentage_UA = ((Double.parseDouble(String.valueOf(UA_count)) / Double.parseDouble(String.valueOf(total_users))) * 100);

        tvBlocked.setText(new DecimalFormat("##.##").format(Percentage_UB) + "%");
        tvAccepted.setText(new DecimalFormat("##.##").format(Percentage_UA) + "%");

        pieChart.addPieSlice(
                new PieModel(
                        "Accepted",
                        UA_count,
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Blocked",
                        UB_count,
                        Color.parseColor("#EF5350")));


        // To animate the pie chart
        pieChart.startAnimation();
    }
}