package com.example.triparrangersfyp.TravelAgency;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.triparrangersfyp.BuildConfig;
import com.example.triparrangersfyp.Feedback;
import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.TermsAndConditions;
import com.example.triparrangersfyp.Tourist.ApprovedTrips;
import com.example.triparrangersfyp.Tourist.Users_Dashboard;
import com.example.triparrangersfyp.WeatherActivity;
import com.example.triparrangersfyp.nearbyareas.NearByAreasActivity;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.android.material.navigation.NavigationView;


public class TA_Dashboard extends AppCompatActivity {

    CardView OthersTrips, YourTrips, Request;
    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img;
    Toolbar toolbar;
    TinyDB tinyDb;
    Button Location,Weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_ta_dashboard);

        OthersTrips = findViewById(R.id.TATrips);
        YourTrips = findViewById(R.id.YourTrips);
        Request = findViewById(R.id.ManageRequest);
        nav1 = findViewById(R.id.nav_menuU);
        drawer = findViewById(R.id.TA_Drawer);
        img = findViewById(R.id.TANavImage);
        toolbar = findViewById(R.id.TA_Toolbar);
        tinyDb = new TinyDB(this);
        Location = findViewById(R.id.Location);
        Weather = findViewById(R.id.Weather);


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
                    new AlertDialog.Builder(TA_Dashboard.this).setIcon(R.drawable.ic_baseline_warning_24)
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
                    final Dialog dialog = new Dialog(TA_Dashboard.this);
                    dialog.setContentView(R.layout.aboutus);
                    dialog.show();

                } else if (item.getItemId() == R.id.TAMenu_Share) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Share Demo");
                        String shareMessage = "https://play.google.com/store/apps/details?=" + BuildConfig.APPLICATION_ID + "\n\n";
                        intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(intent.createChooser(intent, "ShareVia"));
                    } catch (Exception e) {
                        Toast.makeText(TA_Dashboard.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                }
                return true;
            }

        });

        OthersTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TA_Dashboard.this, ApprovedTrips.class));
            }
        });
        YourTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TA_Dashboard.this, TA_Trips.class));
            }
        });

        Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TA_Dashboard.this, RequestedTrips_TA.class));
            }
        });


    }
}