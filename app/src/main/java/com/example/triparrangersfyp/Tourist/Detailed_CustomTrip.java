package com.example.triparrangersfyp.Tourist;

import static com.example.triparrangersfyp.util.Constant.C_TRIP_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.CustomTrip;
import com.example.triparrangersfyp.service.DeleteCustomizedTripService;
import com.example.triparrangersfyp.service.UpdateCustomTripCompleteService;
import com.example.triparrangersfyp.service.UpdateCustomizedService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detailed_CustomTrip extends AppCompatActivity {
    EditText title, des, depD, ArrD, DepT, ArrT, PickL, DropL;
    String TripTitle, TripDes, TripDepDate, TripArrDate, TripDepTime, TripArrTime,
            TripPickup, TripDropOff, status;
    int getTripID;
    Button update, delete, Addmember, AddExpenses, Complete;
    CustomTrip customTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_custom_trip);

        title = findViewById(R.id.ctitle_Trip);
        des = findViewById(R.id.cdescription);
        depD = findViewById(R.id.cDepDate);
        ArrD = findViewById(R.id.cArrivalDate);
        DepT = findViewById(R.id.cDepTime);
        ArrT = findViewById(R.id.cArrivalTime);
        PickL = findViewById(R.id.cPickupLocation);
        DropL = findViewById(R.id.cDropoffLocation);
        update = findViewById(R.id.CUpdateTrip);
        delete = findViewById(R.id.CDeleteTrip);
        Addmember = findViewById(R.id.CAddMember);
        AddExpenses = findViewById(R.id.AddExpensess);
        Complete = findViewById(R.id.Completed);

        getTripID = getIntent().getIntExtra("ctrip_id", 0);
        TripTitle = getIntent().getStringExtra("ctrip_title");
        TripDes = getIntent().getStringExtra("ctrip_description");
        TripDepDate = getIntent().getStringExtra("ctrip_depDate");
        TripArrDate = getIntent().getStringExtra("ctrip_arrDate");
        TripDepTime = getIntent().getStringExtra("ctrip_depTime");
        TripArrTime = getIntent().getStringExtra("ctrip_arrTime");
        TripPickup = getIntent().getStringExtra("ctrip_pickup");
        TripDropOff = getIntent().getStringExtra("ctrip_dropoff");
        status=getIntent().getStringExtra("STATUS");


        C_TRIP_ID = getTripID;

        title.setText(TripTitle);
        des.setText(TripDes);
        depD.setText(TripDepDate);
        ArrD.setText(TripArrDate);
        DepT.setText(TripDepTime);
        ArrT.setText(TripArrTime);
        PickL.setText(TripPickup);
        DropL.setText(TripDropOff);

        if (status.equals("C")) {
            Complete.setVisibility(View.GONE);
        }



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    Update_TripRecord();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Delete_Record();

            }
        });

        AddExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Detailed_CustomTrip.this, AddExpenseActivity.class)
                        .putExtra("ctrip_id",getTripID));

            }
        });
        Addmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Detailed_CustomTrip.this, ViewCustomTripMembers.class));
            }
        });

        Complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateCustomTCompleteStatus(getTripID,"C");
                startActivity(new Intent(Detailed_CustomTrip.this, CompleteCustomTrip.class)
                        .putExtra("ctrip_id",getTripID));
            }
        });


    }

    public boolean validation() {
        if (title.getText().toString().isEmpty() || des.getText().toString().isEmpty() ||
                depD.getText().toString().isEmpty() || ArrD.getText().toString().isEmpty() ||
                DepT.getText().toString().isEmpty() || ArrT.getText().toString().isEmpty() ||
                PickL.getText().toString().isEmpty() || DropL.getText().toString().isEmpty()) {

            Toast.makeText(this, "Required Fields are missing", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

    private void Update_TripRecord() {
        customTrip = new CustomTrip();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        UpdateCustomizedService service = RetrofitClient.getClient().create(UpdateCustomizedService.class);
        Call<CustomTrip> call = service.updateCustomTripData(getTripID, title.getText().toString(), des.getText().toString(),
                depD.getText().toString(), ArrD.getText().toString(),
                DepT.getText().toString(), ArrT.getText().toString(),
                PickL.getText().toString(), DropL.getText().toString()
        );

        call.enqueue(new Callback<CustomTrip>() {
            @Override
            public void onResponse(Call<CustomTrip> call, Response<CustomTrip> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    customTrip = response.body();
                    if (!customTrip.isError()) {
                        Toast.makeText(Detailed_CustomTrip.this,
                                customTrip.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Detailed_CustomTrip.this,
                                customTrip.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomTrip> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Detailed_CustomTrip.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void Delete_Record() {
        customTrip = new CustomTrip();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait...");
        progressDialog.show();

        DeleteCustomizedTripService service = RetrofitClient.getClient().create(DeleteCustomizedTripService.class);
        Call<CustomTrip> call = service.DeleteCustomTrip(getTripID);


        call.enqueue(new Callback<CustomTrip>() {
            @Override
            public void onResponse(Call<CustomTrip> call, Response<CustomTrip> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    customTrip = response.body();
                    if (!customTrip.isError()) {
                        Toast.makeText(Detailed_CustomTrip.this,
                                customTrip.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Detailed_CustomTrip.this,
                                customTrip.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomTrip> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Detailed_CustomTrip.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void UpdateCustomTCompleteStatus(int getTripID, String r) {
        customTrip = new CustomTrip();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        UpdateCustomTripCompleteService service = RetrofitClient.getClient().create(UpdateCustomTripCompleteService.class);
        Call<CustomTrip> call = service.updateCustomTripStatusComplete(getTripID, r);

        call.enqueue(new Callback<CustomTrip>() {
            @Override
            public void onResponse(Call<CustomTrip> call, Response<CustomTrip> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    customTrip = response.body();
                    if (!customTrip.isError()) {
                        Toast.makeText(Detailed_CustomTrip.this,
                                customTrip.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Detailed_CustomTrip.this,
                                customTrip.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomTrip> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Detailed_CustomTrip.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}








