package com.example.triparrangersfyp.TravelAgency;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.triparrangersfyp.Admin.Detailed_User;
import com.example.triparrangersfyp.BuildConfig;
import com.example.triparrangersfyp.ChattingActivity;
import com.example.triparrangersfyp.Feedback;
import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.TermsAndConditions;
import com.example.triparrangersfyp.model.Chat;
import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.service.DeleteTripAPIService;
import com.example.triparrangersfyp.service.UpdateTripsDataService;
import com.example.triparrangersfyp.service.UpdateUserStatusService;
import com.example.triparrangersfyp.util.Endpoint;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detailed_Trip extends AppCompatActivity {

    EditText title, des, depD, ArrD, DepT, ArrT, PickL, DropL, SeatsNum, payment;
    String TripTitle, TripDes, TripDepDate, TripArrDate, TripDepTime, TripArrTime,
            TripPickup, TripDropOff, numSeats, TripPayment;
    int getTripID;
    Button update, delete, chat;
    Trips trip;

    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img, tripIMAGE;
    Toolbar toolbar;
    TinyDB tinyDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_trip);
        title= findViewById(R.id.title_Tripp);
        des= findViewById(R.id.descriptionp);
        depD= findViewById(R.id.DepDatep);
        ArrD= findViewById(R.id.ArrivalDatep);
        DepT= findViewById(R.id.DepTimep);
        ArrT= findViewById(R.id.ArrivalTimep);
        PickL= findViewById(R.id.T_PickupLocationp);
        DropL= findViewById(R.id.T_DropoffLocationp);
        SeatsNum= findViewById(R.id.T_Seatsp);
        payment= findViewById(R.id.paymentp);
        update=findViewById(R.id.UpdateTrip);
        delete=findViewById(R.id.DeleteTrip);
        chat=findViewById(R.id.Chat);
        tripIMAGE= findViewById(R.id.TRIPimage);
        nav1 = findViewById(R.id.nav_menuU);
        drawer = findViewById(R.id.TA_Drawer);
        img = findViewById(R.id.TANavImage);
        toolbar = findViewById(R.id.TA_Toolbar);
        tinyDb = new TinyDB(this);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        nav1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.TAMenu_Home) {

                    startActivity(new Intent(getApplicationContext(), TA_Dashboard.class));
                } else if (item.getItemId() == R.id.TAMyTrips) {

                    startActivity(new Intent(getApplicationContext(), TA_Trips.class));
                }
                else if (item.getItemId() == R.id.TAMenu_GenerateTrip) {

                    startActivity(new Intent(getApplicationContext(), AddTrip.class));
                }else if (item.getItemId() == R.id.TAMenu_Logout) {
                    new AlertDialog.Builder(Detailed_Trip.this).setIcon(R.drawable.ic_baseline_warning_24)
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

                } else if (item.getItemId() == R.id.TAMenu_Profile) {
                    startActivity(new Intent(getApplicationContext(), UpdateProfile_TA.class));
                } else if (item.getItemId() == R.id.TAMenu_Feedback) {
                    startActivity(new Intent(getApplicationContext(), Feedback.class));
                } else if (item.getItemId() == R.id.TATermsAndConditions) {
                    startActivity(new Intent(getApplicationContext(), TermsAndConditions.class));
                } else if (item.getItemId() == R.id.TAAboutUs) {
                    final Dialog dialog = new Dialog(Detailed_Trip.this);
                    dialog.setContentView(R.layout.aboutus);
                    dialog.show();

                } else if (item.getItemId() == R.id.TAMenu_Share) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Share Demo");
                        String shareMessage = "https://play.google.com/store/apps/details?=" + BuildConfig.APPLICATION_ID + "\n\n";
                        intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(intent, "ShareVia"));
                    } catch (Exception e) {
                        Toast.makeText(Detailed_Trip.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                }
                return true;
            }

        });

        getTripID = getIntent().getIntExtra("trip_id", 0);
        TripTitle = getIntent().getStringExtra("trip_title");
        TripDes = getIntent().getStringExtra("trip_description");
        TripDepDate = getIntent().getStringExtra("trip_depDate");
        TripArrDate = getIntent().getStringExtra("trip_arrDate");
        TripDepTime = getIntent().getStringExtra("trip_depTime");
        TripArrTime = getIntent().getStringExtra("trip_arrTime");
        TripPickup = getIntent().getStringExtra("trip_pickup");
        TripDropOff= getIntent().getStringExtra("trip_dropoff");
        numSeats=getIntent().getStringExtra("trip_numSeats");
        TripPayment= getIntent().getStringExtra("trip_payment");

        Glide.with(this)
                .load(Endpoint.IMAGE_URL + getIntent().getStringExtra("trip_image"))
                .into(tripIMAGE);
        title.setText(TripTitle);
        des.setText(TripDes);
        depD.setText(TripDepDate);
        ArrD.setText(TripArrDate);
        DepT.setText(TripDepTime);
        ArrT.setText(TripArrTime);
        PickL.setText(TripPickup);
        DropL.setText(TripDropOff);
        SeatsNum.setText(numSeats);
        payment.setText(TripPayment);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate())
                {
                    Update_TripRecord();
                }
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),
                        ChattingActivity.class);
                intent.putExtra("v1", getTripID);
                intent.putExtra("v2", TripTitle);
                startActivity(intent);
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Delete_Record();

            }
        });

    }

    private void Update_TripRecord() {
        trip = new Trips();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        UpdateTripsDataService service = RetrofitClient.getClient().create(UpdateTripsDataService.class);
        Call<Trips> call = service.updateTripData(getTripID, title.getText().toString(), des.getText().toString(),
                depD.getText().toString(), ArrD.getText().toString(),
                DepT.getText().toString(), ArrT.getText().toString(),
                PickL.getText().toString(), DropL.getText().toString(), SeatsNum.getText().toString(),
                payment.getText().toString());


        call.enqueue(new Callback<Trips>() {
            @Override
            public void onResponse(Call<Trips> call, Response<Trips> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    trip = response.body();
                    if (!trip.isError()) {
                        Toast.makeText(Detailed_Trip.this,
                                trip.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Detailed_Trip.this,
                                trip.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Trips> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Detailed_Trip.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
        private void Delete_Record()
        {
            trip = new Trips();
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("please wait...");
            progressDialog.show();

           DeleteTripAPIService service = RetrofitClient.getClient().create(DeleteTripAPIService .class);
            Call<Trips> call = service.DeleteTrip(getTripID);


            call.enqueue(new Callback<Trips>() {
                @Override
                public void onResponse(Call<Trips> call, Response<Trips> response) {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        trip = response.body();
                        if (!trip.isError()) {
                            Toast.makeText(Detailed_Trip.this,
                                    trip.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Detailed_Trip.this,
                                    trip.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Trips> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(Detailed_Trip.this,
                            t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
    }

    private boolean validate()
    {
        String taname, taemail, taphone, tacnic;
        boolean isvalid= true;

        if (title.getText().toString().trim().isEmpty()) {
            title.setError("Please Enter Trip Title");
            isvalid = false;
        }

        if (des.getText().toString().trim().isEmpty()) {
            des.setError("Please Enter Trip Description");
            isvalid = false;
        }

        if (depD.getText().toString().trim().isEmpty())
        {
            depD.setError("Please Choose Depaarture Date");
            isvalid = false;
        }

        if (ArrD.getText().toString().trim().isEmpty())
        {
            ArrD.setError("Please Choose Arrival Date");
            isvalid = false;
        }
        if (DepT.getText().toString().trim().isEmpty())
        {
            DepT.setError("Please Choose Departure Time");
            isvalid = false;
        }
        if (ArrT.getText().toString().trim().isEmpty())
        {
            ArrT.setError("Please Choose Arrival Time");
            isvalid = false;
        }

        if (PickL.getText().toString().trim().isEmpty())
        {
            PickL.setError("Please Enter Pickup Location");
            isvalid = false;
        }

        if (DropL.getText().toString().trim().isEmpty())
        {
            DropL.setError("Please Enter Dropoff Location");
            isvalid = false;
        }
        if (payment.getText().toString().trim().isEmpty())
        {
            payment.setError("Please Enter Payment");
            isvalid = false;
        }

        if (SeatsNum.getText().toString().trim().isEmpty())
        {
            SeatsNum.setError("Please Enter seats");
            isvalid = false;
        }
        return isvalid;
    }

}







