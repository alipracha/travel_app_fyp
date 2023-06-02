package com.example.triparrangersfyp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.triparrangersfyp.BuildConfig;
import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.Admin;
import com.example.triparrangersfyp.service.UpdateAdminProfileService;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAdminProfile extends AppCompatActivity {
    int getAdminID;
    String  Aemail;
    EditText  Admin_email;
    Button Update;
    Admin admin;
    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img;
    Toolbar toolbar;
    TinyDB tinyDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin_profile);
        Admin_email = findViewById(R.id.Admin_EmailED);
        Update = findViewById(R.id.AUpdatebtn);
        tinyDb = new TinyDB(this);

        getAdminID= tinyDb.getInt("a_id");
        Aemail = tinyDb.getString("a_email");

        Admin_email.setText(Aemail);
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
                        Toast.makeText(UpdateAdminProfile.this, "Error", Toast.LENGTH_SHORT).show();

                    }

                }
                else if (item.getItemId() == R.id.Admin_Menu_LogOut) {

                    new AlertDialog.Builder(UpdateAdminProfile.this).setIcon(R.drawable.ic_baseline_warning_24)
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

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()  )
                    Update_AdminData();

            }
        });


    }

    private void Update_AdminData() {
        admin = new Admin();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        UpdateAdminProfileService service = RetrofitClient.getClient().create(UpdateAdminProfileService.class);
        Call<Admin> call = service.updateAdminData(getAdminID, Admin_email.getText().toString());

        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    admin = response.body();
                    if (!admin.isError()) {
                        Toast.makeText(UpdateAdminProfile.this,
                                admin.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateAdminProfile.this,
                                admin.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UpdateAdminProfile.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private boolean validate()
    {

        boolean isvalid= true;
        String emailValid = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";


        if (Admin_email.getText().toString().trim().isEmpty()) {
            Admin_email.setError("please enter valid Email");
            isvalid = false;
        }
        else if (!Admin_email.getText().toString().trim().matches(emailValid)) {
            Admin_email.setError("Enter valid Email Pattern");
            isvalid = false;
        }

        return isvalid;
    }

}