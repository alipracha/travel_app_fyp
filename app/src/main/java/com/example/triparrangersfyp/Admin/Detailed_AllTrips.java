package com.example.triparrangersfyp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.triparrangersfyp.BuildConfig;
import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.service.UpdateTripStatusService;
import com.example.triparrangersfyp.util.Endpoint;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detailed_AllTrips extends AppCompatActivity {

    TextView title, des, depD, ArrD, DepT, ArrT, PickL, DropL, payment, taId, taName,
            taPhone, tripStatus, taSeats;
    String TripTitle, TripDes, TripDepDate, TripArrDate, TripDepTime,
            TripArrTime, TripPickup, TripDropOff, TripPayment, ta_Name, ta_Phone, trip_status, trip_seats;
    int getTripID, ta_Id;
    ImageView tripPict;
    Button approve, reject;
    Trips trip;

    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img;
    Toolbar toolbar;
    TinyDB tinyDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_all_trips);
        title = findViewById(R.id.title_Trippp);
        des = findViewById(R.id.descriptionpp);
        depD = findViewById(R.id.DepDatepp);
        ArrD = findViewById(R.id.ArrivalDatepp);
        DepT = findViewById(R.id.DepTimepp);
        ArrT = findViewById(R.id.ArrivalTimepp);
        PickL = findViewById(R.id.T_PickupLocationpp);
        DropL = findViewById(R.id.T_DropoffLocationpp);
        payment = findViewById(R.id.paymentpp);
        approve = findViewById(R.id.Approve);
        reject = findViewById(R.id.Reject);
        taId = findViewById(R.id.TAid);
        taName = findViewById(R.id.TAname);
        taPhone = findViewById(R.id.TAphone);
        tripStatus = findViewById(R.id.Status);
        taSeats = findViewById(R.id.SeatsNum);
        tripPict=findViewById(R.id.tripPic);


        getTripID = getIntent().getIntExtra("trip_id", 0);
        TripTitle = getIntent().getStringExtra("trip_title");
        TripDes = getIntent().getStringExtra("trip_description");
        TripDepDate = getIntent().getStringExtra("trip_depDate");
        TripArrDate = getIntent().getStringExtra("trip_arrDate");
        TripDepTime = getIntent().getStringExtra("trip_depTime");
        TripArrTime = getIntent().getStringExtra("trip_arrTime");
        TripPickup = getIntent().getStringExtra("trip_pickup");
        TripDropOff = getIntent().getStringExtra("trip_dropoff");
        trip_seats = getIntent().getStringExtra("trip_numSeats");
        TripPayment = getIntent().getStringExtra("trip_payment");
        ta_Id = getIntent().getIntExtra("travel_agency_id", 0);
        ta_Name = getIntent().getStringExtra("ta_name");
        ta_Phone = getIntent().getStringExtra("ta_phone");
        trip_status = getIntent().getStringExtra("trip_status");

        tinyDb = new TinyDB(this);
        nav1= findViewById(R.id.Anav_menuU);
        drawer = findViewById(R.id.Admin_Drawer);
        img= findViewById(R.id.AdminNavImage);
        toolbar=findViewById(R.id.Admin_Toolbar);


        Glide.with(this)
                .load(Endpoint.IMAGE_URL + getIntent().getStringExtra("trip_image"))
                .into(tripPict);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });


        nav1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.Admin_Menu_Home) {

                    startActivity(new Intent(getApplicationContext(), Admin_Dashboard.class));
                }

                if (item.getItemId() == R.id.Admin_Manage_Profile) {

                    startActivity(new Intent(getApplicationContext(), UpdateAdminProfile.class));
                }

                else if (item.getItemId() == R.id.Admin_Menu_Share) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Share Demo");
                        String shareMessage = "https://play.google.com/store/apps/details?=" + BuildConfig.APPLICATION_ID + "\n\n";
                        intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(intent, "ShareVia"));
                    } catch (Exception e) {
                        Toast.makeText(Detailed_AllTrips.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                }
                else if (item.getItemId() == R.id.Admin_Menu_LogOut) {

                    new AlertDialog.Builder(Detailed_AllTrips.this).setIcon(R.drawable.ic_baseline_warning_24)
                            .setTitle("Exit").setMessage("Are you sure you want to exit?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tinyDb.clear();
                                    finishAffinity();
                                    startActivity(new Intent(getApplicationContext(), Login.class));
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                }

                return true;
            }

        });



        title.setText(TripTitle);
        des.setText(TripDes);
        depD.setText(TripDepDate);
        ArrD.setText(TripArrDate);
        DepT.setText(TripDepTime);
        ArrT.setText(TripArrTime);
        PickL.setText(TripPickup);
        DropL.setText(TripDropOff);
        taSeats.setText(trip_seats);
        payment.setText(TripPayment);
        taId.setText(String.valueOf(ta_Id));
        taName.setText(ta_Name);
        taPhone.setText(ta_Phone);

        if (trip_status.equals("A")) {
            tripStatus.setText("Approve");

        } else if (trip_status.equals("B")) {
            tripStatus.setText("Block");

        }

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateStatus(getTripID, "A");
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateStatus(getTripID, "B");
            }
        });


    }

    private void UpdateStatus(int getTripID, String r) {
        trip = new Trips();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait...");
        progressDialog.show();

        UpdateTripStatusService service = RetrofitClient.getClient().create(UpdateTripStatusService.class);
        Call<Trips> call = service.updateTrip(getTripID, r);


        call.enqueue(new Callback<Trips>() {
            @Override
            public void onResponse(Call<Trips> call, Response<Trips> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    trip = response.body();
                    if (!trip.isError()) {
                        Toast.makeText(Detailed_AllTrips.this,
                                trip.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Detailed_AllTrips.this,
                                trip.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Trips> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Detailed_AllTrips.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

