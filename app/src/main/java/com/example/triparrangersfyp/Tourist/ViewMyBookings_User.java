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

import com.bumptech.glide.Glide;
import com.example.triparrangersfyp.BuildConfig;
import com.example.triparrangersfyp.ChattingActivity;
import com.example.triparrangersfyp.ComplaintActivity;
import com.example.triparrangersfyp.Feedback;
import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.TermsAndConditions;
import com.example.triparrangersfyp.TravelAgency.AddTrip;
import com.example.triparrangersfyp.TravelAgency.Detailed_Trip;
import com.example.triparrangersfyp.TravelAgency.TA_Dashboard;
import com.example.triparrangersfyp.TravelAgency.TA_Trips;
import com.example.triparrangersfyp.TravelAgency.UpdateProfile_TA;
import com.example.triparrangersfyp.TravelAgency.ViewBookingRequests_TA;
import com.example.triparrangersfyp.model.Booking;
import com.example.triparrangersfyp.service.CancelTripAPIService;
import com.example.triparrangersfyp.service.UpdateBookingStatusService;
import com.example.triparrangersfyp.util.Endpoint;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMyBookings_User extends AppCompatActivity {

    TextView title, Cname, description, depD, ArrD, DepT, ArrT, Pickup, dropoff, price, seats;
    String TripTitle, CName, Des, DEP_D, Arr_D, Dep_T, Arr_T, Pick_Up, Drop_Off, Payment, Seat;
    int tripID;
    int getBookingID;
    Button Cancel, Complaint, Chatting;
    Booking booking;

    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img, tripImg;
    Toolbar toolbar;
    TinyDB tinyDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_bookings_user);

        title = findViewById(R.id.title_Trip_Tourist);
        Cname = findViewById(R.id.CustomerN);
        description= findViewById(R.id.description_Tourist);
        depD= findViewById(R.id.depDatee);
        ArrD= findViewById(R.id.ArrDatee);
        DepT= findViewById(R.id.DepTimee);
        ArrT= findViewById(R.id.ArrTimee);
        Pickup= findViewById(R.id.pickupp);
        dropoff= findViewById(R.id.pricee);
        price= findViewById(R.id.dropoff);
        seats= findViewById(R.id.Seatss);
        Cancel= findViewById(R.id.CancelTrip);
        Complaint=findViewById(R.id.Complaint);
        Chatting=findViewById(R.id.Chatt);
        nav1 = findViewById(R.id.nav_menuU);
        drawer = findViewById(R.id.TA_Drawer);
        img = findViewById(R.id.TANavImage);
        toolbar = findViewById(R.id.TA_Toolbar);
        tripImg=findViewById(R.id.setImg);
        tinyDb = new TinyDB(this);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        Glide.with(this)
                .load(Endpoint.IMAGE_URL + getIntent().getStringExtra("trip_image"))
                .into(tripImg);
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
                    new AlertDialog.Builder(ViewMyBookings_User.this).setIcon(R.drawable.ic_baseline_warning_24)
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
                } else if (item.getItemId() == R.id.TouristAboutUs) {
                    final Dialog dialog = new Dialog(ViewMyBookings_User.this);
                    dialog.setContentView(R.layout.aboutus);
                    dialog.show();

                }else if (item.getItemId() == R.id.TAMenu_Share) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Share Demo");
                        String shareMessage = "https://play.google.com/store/apps/details?=" + BuildConfig.APPLICATION_ID + "\n\n";
                        intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(intent, "ShareVia"));
                    } catch (Exception e) {
                        Toast.makeText(ViewMyBookings_User.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                }
                return true;
            }

        });

        tripID = getIntent().getIntExtra("trip_id",0);
        TripTitle = getIntent().getStringExtra("trip_title");
        CName = getIntent().getStringExtra("c_name");
        Des = getIntent().getStringExtra("trip_description");
        DEP_D = getIntent().getStringExtra("trip_depDate");
        Arr_D = getIntent().getStringExtra("trip_arrDate");
        Dep_T = getIntent().getStringExtra("trip_depTime");
        Arr_T = getIntent().getStringExtra("trip_arrTime");
        Pick_Up = getIntent().getStringExtra("trip_pickup");
        Drop_Off = getIntent().getStringExtra("trip_dropoff");
        Payment = getIntent().getStringExtra("b_price");
        Seat = getIntent().getStringExtra("b_seats");

        title.setText(TripTitle);
        Cname.setText(CName);
        description.setText(Des);
        depD.setText(DEP_D);
        ArrD.setText(Arr_D);
        DepT.setText(Dep_T);
        ArrT.setText(Arr_T);
        Pickup.setText(Pick_Up);
        dropoff.setText(Drop_Off);
        price.setText(Payment);
        seats.setText(Seat);



        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelTrip(getBookingID, "CA");
            }
        });

        Complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),
                        ComplaintActivity.class));
            }
        });

        Chatting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),
                        ChattingActivity.class);
                intent.putExtra("v1", tripID);
                intent.putExtra("v2", TripTitle);
                startActivity(intent);

            }
        });


    }
    private void CancelTrip(int getBookingID, String r) {
        booking = new Booking();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        CancelTripAPIService service = RetrofitClient.getClient().create( CancelTripAPIService.class);
        Call<Booking> call = service.CancelTrip(getBookingID, "CA");

        call.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    booking = response.body();
                    if (! booking.isError()) {
                        Toast.makeText(ViewMyBookings_User.this,
                                booking.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ViewMyBookings_User.this,
                                booking.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewMyBookings_User.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}