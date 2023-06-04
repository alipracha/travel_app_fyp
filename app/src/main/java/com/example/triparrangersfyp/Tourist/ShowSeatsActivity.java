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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.triparrangersfyp.Adapter.SeatAdapter;
import com.example.triparrangersfyp.Feedback;
import com.example.triparrangersfyp.JazzCashActivity;
import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.TermsAndConditions;
import com.example.triparrangersfyp.model.Booking;
import com.example.triparrangersfyp.model.Seat;
import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.service.CheckSeatsAPIService;
import com.example.triparrangersfyp.service.GetSeatsService;
import com.example.triparrangersfyp.service.InsertBookingService;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowSeatsActivity extends AppCompatActivity {
    Trips trips;
    ProgressDialog progressDialog;
    GridView seats_GV;
    SeatAdapter adapter;
    public static List<Seat> showseatList = new ArrayList<>();
    public static List<Integer> bookedseatlist = new ArrayList<>();
    Button ConfirmBooking;
    Booking booking;
    int gettravelagencyID, gettripid;
    String gettripprice;
    TinyDB tinyDB;
    int totalNumSeats = 0;
    public static String selectedseat = "";
    public static List<Integer> selectedseatlist = new ArrayList<>();

    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_seats);
        seats_GV = findViewById(R.id.seats_GV);
        ConfirmBooking = findViewById(R.id.btn_confirm_book);
        tinyDB = new TinyDB(this);
        gettripid = getIntent().getIntExtra("TRIP_ID", 0);
        gettravelagencyID = getIntent().getIntExtra("TRAVEL_AGENCY_ID", 0);
        gettripprice = getIntent().getStringExtra("TRIP_PAY");
        tinyDB = new TinyDB(this);
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
                    new AlertDialog.Builder(ShowSeatsActivity.this).setIcon(R.drawable.ic_baseline_warning)
                            .setTitle("Exit").setMessage("Are you sure you want to exit?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tinyDB.clear();
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
                    startActivity(new Intent(ShowSeatsActivity.this, Tourists_Options.class));
                }
                else if (item.getItemId() == R.id.TouristTermsAndConditions) {
                    startActivity(new Intent(ShowSeatsActivity.this, TermsAndConditions.class));
                }
                else if (item.getItemId() == R.id.TouristAboutUs) {
                    final Dialog dialog = new Dialog(ShowSeatsActivity.this);
                    dialog.setContentView(R.layout.aboutus);
                    dialog.show();

                }

                return true;
            }
        });
        GetSeats(gettripid);
    }

    private void GetSeats(int trip_id) {
        bookedseatlist.clear();
        showseatList.clear();
        trips = new Trips();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        GetSeatsService service = RetrofitClient.getClient().create(GetSeatsService.class);
        Call<Trips> call = service.getSeatsList(trip_id);
        call.enqueue(new Callback<Trips>() {
            @Override
            public void onResponse(Call<Trips> call, Response<Trips> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    trips = response.body();
                    if (!trips.isError())
                    {
                        Toast.makeText(ShowSeatsActivity.this, trips.getMessage(), Toast.LENGTH_SHORT).show();
                        totalNumSeats = Integer.parseInt(trips.getTrip_numSeats());
                        GetBookedSeats();

                    } else {
                        Toast.makeText(ShowSeatsActivity.this,
                                trips.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Trips> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ShowSeatsActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        ConfirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendBookingRequest();
            }
        });
    }


    private void SendBookingRequest() {
        for (int i = 0; i < selectedseatlist.size(); i++) {
            ConfirmBooking(selectedseatlist.get(i));
        }
        selectedseatlist.clear();
        showseatList.clear();
        startActivity(new Intent(ShowSeatsActivity.this, Users_Dashboard.class));

    }

    private void ConfirmBooking(int seatNo) {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        InsertBookingService service = RetrofitClient.getClient().create(InsertBookingService.class);
        Call<Booking> call = service.InsertBooking(tinyDB.getInt("CUSTOMER_ID"),
                gettravelagencyID, gettripid, gettripprice, String.valueOf(seatNo));

        call.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if (response.isSuccessful()) {
                    booking = response.body();
                    if (!booking.isError()) {
                        selectedseat = "";
                        progressDialog.dismiss();
                      //  startActivity(new Intent(getApplicationContext(),JazzCashActivity.class).putExtra("AMOUNT",Integer.parseInt(gettripprice)));
                        finish();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(ShowSeatsActivity.this, booking.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(ShowSeatsActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ShowSeatsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void GetBookedSeats() {
        bookedseatlist.clear();
        showseatList.clear();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        CheckSeatsAPIService service = RetrofitClient.getClient().create(CheckSeatsAPIService.class);
        Call<JsonObject> call = service.getBookedSeats(getIntent().getIntExtra("TRIP_ID", 0));
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());

                        JSONArray jsonArray = jsonObject.getJSONArray("records");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            bookedseatlist.add(Integer.parseInt(data.getString("b_seats")));
                        }
                        AddSeatListData();
                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        AddSeatListData();
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void AddSeatListData() {
        if(bookedseatlist.size()!=0){
            for (int i = 0; i <= totalNumSeats; i++) {
                if (bookedseatlist.contains(i)) {
                    showseatList.add(new Seat(i, true));
                } else {
                    showseatList.add(new Seat(i, false));
                }
            }
        }else{
            for (int i = 0; i <= totalNumSeats; i++) {
                showseatList.add(new Seat(i, false));
            }
        }
        adapter = new SeatAdapter(showseatList, ShowSeatsActivity.this);
        seats_GV.setAdapter(adapter);

    }

} 