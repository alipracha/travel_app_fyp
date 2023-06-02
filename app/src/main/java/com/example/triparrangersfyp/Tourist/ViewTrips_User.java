package com.example.triparrangersfyp.Tourist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.FavouriteTrips;
import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.service.InsertFavouriteTripAPIService;
import com.example.triparrangersfyp.util.Endpoint;
import com.example.triparrangersfyp.util.TinyDB;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewTrips_User extends AppCompatActivity {
    TextView title, des, depD, ArrD, DepT, ArrT, PickL, DropL, payment, tripStatus, TripSeats;
    String TripTitle, TripDes, TripDepDate, TripArrDate, TripDepTime,
            TripArrTime, TripPickup, TripDropOff, TripPayment, trip_status, tripSeats;
    int getTripID, ta_Id;
    ImageView fvrttrip, detail_trip_img;
    Button BookTrip, ViewTA;
    TinyDB tinyDB;
    FavouriteTrips favouriteTrips;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trips_user);
        tinyDB = new TinyDB(this);

        title = findViewById(R.id.title_TripU);
        des = findViewById(R.id.descriptionU);
        depD = findViewById(R.id.DepDateU);
        ArrD = findViewById(R.id.ArrivalDateU);
        DepT = findViewById(R.id.DepTimeU);
        ArrT = findViewById(R.id.ArrivalTimeU);
        PickL = findViewById(R.id.T_PickupLocationU);
        DropL = findViewById(R.id.T_DropoffLocationU);
        TripSeats = findViewById(R.id.Seatsnum);
        payment = findViewById(R.id.paymentU);
        BookTrip = findViewById(R.id.BookTrip);
        ViewTA = findViewById(R.id.ViewTA);
        tripStatus = findViewById(R.id.Status);
        fvrttrip= findViewById(R.id.fvrtTrip);
        detail_trip_img=findViewById(R.id.detail_trip_img);


        if (tinyDB.getInt("LOGIN_PREF") == 2) {
            BookTrip.setVisibility(View.GONE);
        }

        getTripID = getIntent().getIntExtra("trip_id", 0);
        ta_Id = getIntent().getIntExtra("travel_agency_id", 0);
        TripTitle = getIntent().getStringExtra("trip_title");
        TripDes = getIntent().getStringExtra("trip_description");
        TripDepDate = getIntent().getStringExtra("trip_depDate");
        TripArrDate = getIntent().getStringExtra("trip_arrDate");
        TripDepTime = getIntent().getStringExtra("trip_depTime");
        TripArrTime = getIntent().getStringExtra("trip_arrTime");
        TripPickup = getIntent().getStringExtra("trip_pickup");
        TripDropOff = getIntent().getStringExtra("trip_dropoff");
        tripSeats = getIntent().getStringExtra("trip_numSeats");
        TripPayment = getIntent().getStringExtra("trip_payment");
        trip_status = getIntent().getStringExtra("trip_status");

        Glide.with(this)
                .load(Endpoint.IMAGE_URL + getIntent().getStringExtra("trip_image"))
                .into(detail_trip_img);
        title.setText(TripTitle);
        des.setText(TripDes);
        depD.setText(TripDepDate);
        ArrD.setText(TripArrDate);
        DepT.setText(TripDepTime);
        ArrT.setText(TripArrTime);
        PickL.setText(TripPickup);
        DropL.setText(TripDropOff);
        TripSeats.setText(tripSeats);
        payment.setText(TripPayment);


        BookTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewTrips_User.this,
                        ShowSeatsActivity.class)
                        .putExtra("TRIP_ID", getTripID)
                        .putExtra("TRAVEL_AGENCY_ID", ta_Id)
                        .putExtra("TRIP_PAY", TripPayment)
                );
            }
        });

        ViewTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),
                        ViewTADetails_User.class)
                        .putExtra("TA_ID", ta_Id));


            }
        });

        fvrttrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddFav(getTripID);
            }
        });
    }

    private void AddFav(int tripID) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        favouriteTrips = new FavouriteTrips();

        InsertFavouriteTripAPIService service = RetrofitClient.getClient().create(InsertFavouriteTripAPIService.class);
        Call<FavouriteTrips> call = service.InsertFavTrip(tripID, tinyDB.getInt("CUSTOMER_ID"));
        call.enqueue(new Callback<FavouriteTrips>() {
            @Override
            public void onResponse(Call<FavouriteTrips> call, Response<FavouriteTrips> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    favouriteTrips = response.body();
                    if (!favouriteTrips.isError()) {
                        Toast.makeText(ViewTrips_User.this,
                                favouriteTrips.getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ViewTrips_User.this,
                                favouriteTrips.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<FavouriteTrips> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewTrips_User.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}