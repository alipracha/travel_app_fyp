package com.example.triparrangersfyp.Admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.triparrangersfyp.BuildConfig;
import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.ViewFeedback;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.android.material.navigation.NavigationView;

public class
Admin_Dashboard extends AppCompatActivity {

    CardView ManageUser, ManageTA, ManageTrips, ManageReports, ManageFeedback;
    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img;
    TinyDB tinyDb;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        ManageUser = findViewById(R.id.Manage_User);
        ManageTA = findViewById(R.id.Manage_TA);
        ManageTrips = findViewById(R.id.Manage_Trips);
        tinyDb = new TinyDB(this);
        ManageFeedback=findViewById(R.id.Manage_Feedback);
        ManageReports= findViewById(R.id.Manage_Reports);

        nav1= findViewById(R.id.Anav_menuU);
        drawer = findViewById(R.id.Admin_Drawer);
        img= findViewById(R.id.AdminNavImage);
        toolbar=findViewById(R.id.Admin_Toolbar);

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
                        Toast.makeText(Admin_Dashboard.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                }
                else if (item.getItemId() == R.id.Admin_Menu_LogOut) {

                    new AlertDialog.Builder(Admin_Dashboard.this).setIcon(R.drawable.ic_baseline_warning_24)
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

        ManageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Dashboard.this, Manage_Users.class));
            }
        });
        ManageTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Dashboard.this, TA_Details.class));
            }
        });
        ManageTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Dashboard.this, ManageAllTrips.class));
            }
        });
        ManageFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Dashboard.this, ViewFeedback.class));
            }
        });

        ManageReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Dashboard.this, Reports.class));
            }
        });


    }
}