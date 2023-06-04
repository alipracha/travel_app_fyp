package com.example.triparrangersfyp.Tourist;

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
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.triparrangersfyp.Adapter.ApprovedTripsAdapter;
import com.example.triparrangersfyp.Feedback;
import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.TermsAndConditions;
import com.example.triparrangersfyp.WeatherActivity;

import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.nearbyareas.NearByAreasActivity;
import com.example.triparrangersfyp.service.GetAllApprovedTripsService;
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

public class Users_Dashboard extends AppCompatActivity {

    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img;
    Toolbar toolbar;
    TinyDB tinyDb;
    Button Location, Weather;
    SearchView trip_search_view;
    ImageView   notificationn;
    List<Trips> tripList = new ArrayList<>();
    ProgressDialog progressDialog;
    ApprovedTripsAdapter adapter;
    ListView manage_ApprovedTrips_LV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_dashboard);

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
                    new AlertDialog.Builder(Users_Dashboard.this).setIcon(R.drawable.ic_baseline_warning)
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
                    startActivity(new Intent(Users_Dashboard.this, Tourists_Options.class));
                }
                else if (item.getItemId() == R.id.TouristTermsAndConditions) {
                    startActivity(new Intent(Users_Dashboard.this, TermsAndConditions.class));
                }
                else if (item.getItemId() == R.id.TouristAboutUs) {
                    final Dialog dialog = new Dialog(Users_Dashboard.this);
                    dialog.setContentView(R.layout.aboutus);
                    dialog.show();

                }

                return true;
            }
        });


        Location = findViewById(R.id.LocationU);
        Weather = findViewById(R.id.WeatherU);
        trip_search_view = findViewById(R.id.trip_search_view);
        manage_ApprovedTrips_LV = findViewById(R.id.manage_approved_trip_LV);
        notificationn = findViewById(R.id.notification);

        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NearByAreasActivity.class));
            }
        });

        Weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WeatherActivity.class));
            }
        });

        notificationn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Notifications.class));
            }

        });


        GetTrips();

    }


    private void GetTrips() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        GetAllApprovedTripsService service = RetrofitClient.getClient().create(GetAllApprovedTripsService.class);
        Call<JsonObject> call = service.getApprovedTripsList();
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

                            if (new TinyDB(Users_Dashboard.this).getInt("TRAVEL_AGENCY_ID") !=
                                    data.getInt("travel_agency_id")) {
                                tripList.add(new Trips(
                                        data.getInt("trip_id"),
                                        data.getString("trip_title"),
                                        data.getString("trip_description"),
                                        data.getString("trip_depDate"),
                                        data.getString("trip_arrDate"),
                                        data.getString("trip_depTime"),
                                        data.getString("trip_arrTime"),
                                        data.getString("trip_pickup"),
                                        data.getString("trip_dropoff"),
                                        data.getString("trip_numSeats"),
                                        data.getString("trip_payment"),
                                        data.getString("trip_status"),
                                        data.getString("trip_image"),
                                        data.getInt("travel_agency_id")
                                ));

                            }
                        }
                        adapter = new ApprovedTripsAdapter(tripList,
                                Users_Dashboard.this);
                        manage_ApprovedTrips_LV.setAdapter(adapter);

                        trip_search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                adapter.getFilter().filter(newText);
                                return true;
                            }
                        });

                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Users_Dashboard.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}