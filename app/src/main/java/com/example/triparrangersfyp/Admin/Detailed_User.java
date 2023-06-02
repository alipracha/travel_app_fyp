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

import com.example.triparrangersfyp.BuildConfig;
import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.service.UpdateUserStatusService;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detailed_User extends AppCompatActivity {

    int getCID;
    String Cname, Cemail, Cphone, CStatus, Ccnic;
    TextView Cus_name, Cus_email, Cus_phone, Cus_cnic, Cus_Status;
    Button approve, reject;
    Customer customer;

    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img;
    Toolbar toolbar;
    TinyDB tinyDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_user);

        Cus_name = findViewById(R.id.U_Name);
        Cus_email = findViewById(R.id.U_Email);
        Cus_phone = findViewById(R.id.U_PhoneNum);
        Cus_cnic = findViewById(R.id.U_CNIC);
        approve = findViewById(R.id.Approvebtn);
        reject = findViewById(R.id.Rejectbtn);
        Cus_Status= findViewById(R.id.U_Status);
        tinyDb = new TinyDB(this);
        nav1= findViewById(R.id.Anav_menuU);
        drawer = findViewById(R.id.Admin_Drawer);
        img= findViewById(R.id.AdminNavImage);
        toolbar=findViewById(R.id.Admin_Toolbar);

        getCID = getIntent().getIntExtra("c_id", 0);
        Cname = getIntent().getStringExtra("c_name");
        Cemail = getIntent().getStringExtra("c_email");
        Cphone = getIntent().getStringExtra("c_phone");
        Ccnic = getIntent().getStringExtra("c_cnic");
        CStatus = getIntent().getStringExtra("c_status");


        Cus_name.setText(Cname);
        Cus_email.setText(Cemail);
        Cus_phone.setText(Cphone);
        Cus_cnic.setText(Ccnic);

        if (CStatus.equals("A")) {
            Cus_Status.setText("Approve");

        } else if (CStatus.equals("B")) {
            Cus_Status.setText("Block");

        }

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateStatus(getCID, "A");
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateStatus(getCID, "B");
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
                        startActivity(intent.createChooser(intent, "ShareVia"));
                    } catch (Exception e) {
                        Toast.makeText(Detailed_User.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                }
                else if (item.getItemId() == R.id.Admin_Menu_LogOut) {

                    new AlertDialog.Builder(Detailed_User.this).setIcon(R.drawable.ic_baseline_warning_24)
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

    }
private void UpdateStatus(int getCID, String r) {
    customer = new Customer();
    ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("please wait...");
    progressDialog.show();

    UpdateUserStatusService service = RetrofitClient.getClient().create(UpdateUserStatusService.class);
    Call<Customer> call = service.updateUser(getCID, r);


    call.enqueue(new Callback<Customer>() {
        @Override
        public void onResponse(Call<Customer> call, Response<Customer> response) {
            if (response.isSuccessful()) {
                progressDialog.dismiss();
                customer = response.body();
                if (!customer.isError()) {
                    Toast.makeText(Detailed_User.this,
                            customer.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Detailed_User.this,
                            customer.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Customer> call, Throwable t) {
            progressDialog.dismiss();
            Toast.makeText(Detailed_User.this,
                    t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
}

}

