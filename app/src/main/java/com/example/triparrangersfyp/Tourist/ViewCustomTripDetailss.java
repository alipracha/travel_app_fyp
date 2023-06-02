package com.example.triparrangersfyp.Tourist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.Dialog;
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

import com.example.triparrangersfyp.Feedback;
import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.TermsAndConditions;
import com.example.triparrangersfyp.model.CustomTrip;
import com.example.triparrangersfyp.service.UpdateCustomTripStatusService;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCustomTripDetailss extends AppCompatActivity {

    TextView title, description, depD, ArrD, DepT, ArrT, Pickup, dropoff, status;
    String TripTitle, Des, DEP_D, Arr_D, Dep_T, Arr_T, Pick_Up, Drop_Off;
    int tripID;
    Button Accept, Reject;
    int getMemberID;
    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img;
    Toolbar toolbar;
    TinyDB tinyDb;
    CustomTrip customTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_custom_trip_detailss);



        title = findViewById(R.id.title_Tripp);
        description = findViewById(R.id.description_trip);
        depD = findViewById(R.id.DEpDatee);
        ArrD = findViewById(R.id.ARRDatee);
        DepT = findViewById(R.id.DEPTimee);
        ArrT = findViewById(R.id.ARRTimee);
        Pickup = findViewById(R.id.PICKupp);
        dropoff = findViewById(R.id.DROPoff);

        Accept = findViewById(R.id.AcceptRequest);
        Reject = findViewById(R.id.RejectRequest);

        tinyDb = new TinyDB(this);
        nav1 = findViewById(R.id.nav_menuu);
        drawer = findViewById(R.id.User_drawerr);
        img = findViewById(R.id.UserNavImage);
        toolbar = findViewById(R.id.User_Toolbar);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        nav1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.Menu_Home) {
                    startActivity(new Intent(getApplicationContext(), Users_Dashboard.class));
                } else if (item.getItemId() == R.id.MyBookings) {
                    startActivity(new Intent(getApplicationContext(), ConfirmedBookings_User.class));
                } else if (item.getItemId() == R.id.Menu_LogOut) {
                    new AlertDialog.Builder(ViewCustomTripDetailss.this).setIcon(R.drawable.ic_baseline_warning_24)
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

                } else if (item.getItemId() == R.id.Menu_Feedback) {
                    startActivity(new Intent(getApplicationContext(), Feedback.class));
                } else if (item.getItemId() == R.id.Menu_Profile) {
                    startActivity(new Intent(getApplicationContext(), UpdateProfile_Tourist.class));
                } else if (item.getItemId() == R.id.Menu_CustomizedTrip) {
                    startActivity(new Intent(getApplicationContext(), CustomizedTripsActivity.class));
                } else if (item.getItemId() == R.id.Menu_Favt_Trip) {
                    startActivity(new Intent(getApplicationContext(), ViewFavouriteTrips.class));
                } else if (item.getItemId() == R.id.your_trips_menu) {
                    startActivity(new Intent(ViewCustomTripDetailss.this, Tourists_Options.class));
                }
                else if (item.getItemId() == R.id.TouristTermsAndConditions) {
                    startActivity(new Intent(ViewCustomTripDetailss.this, TermsAndConditions.class));
                }
                else if (item.getItemId() == R.id.TouristAboutUs) {
                    final Dialog dialog = new Dialog(ViewCustomTripDetailss.this);
                    dialog.setContentView(R.layout.aboutus);
                    dialog.show();

                }

                return true;
            }
        });
        if (getIntent().getStringExtra("STATUS").equals("A")) {
            Reject.setVisibility(View.GONE);
            Accept.setText("Manage Expense");

        }

        tripID = getIntent().getIntExtra("ctrip_id", 0);
        TripTitle = getIntent().getStringExtra("ctrip_title");
        Des = getIntent().getStringExtra("ctrip_description");
        DEP_D = getIntent().getStringExtra("ctrip_depDate");
        Arr_D = getIntent().getStringExtra("ctrip_arrDate");
        Dep_T = getIntent().getStringExtra("ctrip_depTime");
        Arr_T = getIntent().getStringExtra("ctrip_arrTime");
        Pick_Up = getIntent().getStringExtra("ctrip_pickup");
        Drop_Off = getIntent().getStringExtra("ctrip_dropoff");
        getMemberID = tinyDb.getInt("CUSTOMER_ID");


        title.setText(TripTitle);
        description.setText(Des);
        depD.setText(DEP_D);
        ArrD.setText(Arr_D);
        DepT.setText(Dep_T);
        ArrT.setText(Arr_T);
        Pickup.setText(Pick_Up);
        dropoff.setText(Drop_Off);


        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getStringExtra("STATUS").equals("A")) {
                    startActivity(new Intent(getApplicationContext(),
                            AddExpenseActivity.class).putExtra("ctrip_id",tripID));

                } else {
                    UpdateStatus(getIntent().getIntExtra("REQ_ID", 0), "A");
                }
            }
        });

        Reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateStatus(getIntent().getIntExtra("REQ_ID", 0), "B");
            }
        });

    }

    private void UpdateStatus(int getReqID, String r) {
        customTrip = new CustomTrip();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        UpdateCustomTripStatusService service = RetrofitClient.getClient().create(UpdateCustomTripStatusService.class);
        Call<CustomTrip> call = service.updateCustomTripStatus(getReqID, r);

        call.enqueue(new Callback<CustomTrip>() {
            @Override
            public void onResponse(Call<CustomTrip> call, Response<CustomTrip> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    customTrip = response.body();
                    if (!customTrip.isError()) {
                        Toast.makeText(ViewCustomTripDetailss.this,
                                customTrip.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ViewCustomTripDetailss.this,
                                customTrip.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomTrip> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewCustomTripDetailss.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
