package com.example.triparrangersfyp.Admin;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.triparrangersfyp.BuildConfig;
import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.service.UpdateTAStatusService;
import com.example.triparrangersfyp.util.Endpoint;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detailed_TA extends AppCompatActivity {
    TextView name, email, phonenum, cnic, status;
    int gettaID;
    String taname, taemail, taphone, tacnic, taStatus;
    TravelAgency TA;
    ProgressDialog progressDialog;
    Button approve, reject;
    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img, ta_img;
    Toolbar toolbar;
    TinyDB tinyDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_ta);
        name = findViewById(R.id.TA_Name);
        email = findViewById(R.id.TA_Email);
        phonenum = findViewById(R.id.TA_PhoneNum);
        cnic = findViewById(R.id.TA_CNIC);
        status = findViewById(R.id.TA_status);
        approve = findViewById(R.id.ApproveB);
        reject = findViewById(R.id.RejectB);
        ta_img=findViewById(R.id.ta_Image);

        gettaID = getIntent().getIntExtra("ta_id", 0);
        taname = getIntent().getStringExtra("ta_name");
        taemail = getIntent().getStringExtra("ta_email");
        taphone = getIntent().getStringExtra("ta_phone");
        tacnic = getIntent().getStringExtra("ta_cnic");
        taStatus = getIntent().getStringExtra("ta_status");
        Glide.with(this)
                .load(Endpoint.IMAGE_URL + getIntent().getStringExtra("ta_image"))
                .into(ta_img);
        tinyDb = new TinyDB(this);
        nav1= findViewById(R.id.Anav_menuU);
        drawer = findViewById(R.id.Admin_Drawer);
        img= findViewById(R.id.AdminNavImage);
        toolbar=findViewById(R.id.Admin_Toolbar);
        name.setText(taname);
        email.setText(taemail);
        phonenum.setText(taphone);
        cnic.setText(tacnic);
        if (taStatus.equals("A")) {
            status.setText("Approve");

        } else if (taStatus.equals("B")) {
            status.setText("Block");

        }
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
                        Toast.makeText(Detailed_TA.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                }
                else if (item.getItemId() == R.id.Admin_Menu_LogOut) {

                    new AlertDialog.Builder(Detailed_TA.this).setIcon(R.drawable.ic_baseline_warning_24)
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


        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateStatus(gettaID, "A");
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateStatus(gettaID, "B");
            }
        });


    }

    private void UpdateStatus(int gettaID, String r) {
       TA = new TravelAgency();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        UpdateTAStatusService service = RetrofitClient.getClient().create(UpdateTAStatusService.class);
        Call<TravelAgency> call = service.updateTA(gettaID, r);


        call.enqueue(new Callback<TravelAgency>() {
            @Override
            public void onResponse(Call<TravelAgency> call, Response<TravelAgency> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    TA = response.body();
                    if (!TA.isError()) {
                        Toast.makeText(Detailed_TA.this,
                                TA.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Detailed_TA.this,
                                TA.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TravelAgency> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Detailed_TA.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

