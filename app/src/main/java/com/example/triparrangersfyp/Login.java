package com.example.triparrangersfyp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.triparrangersfyp.Admin.Admin_Dashboard;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.Tourist.Users_Dashboard;
import com.example.triparrangersfyp.TravelAgency.TA_Dashboard;
import com.example.triparrangersfyp.model.Admin;
import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.service.AdminService;
import com.example.triparrangersfyp.service.CusLogin;
import com.example.triparrangersfyp.service.TA_Login;
import com.example.triparrangersfyp.util.TinyDB;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    int getCheck;
    TextView Already_Acc, SignupTV;
    EditText Email, Password;
    Button LoginBtn;
    Customer customer;
    Admin admin;
    TravelAgency TA;
    TinyDB tinyDB;
    ProgressDialog progressDialog;
    RadioButton TA_RBtn, Tourist_RBtn, Admin_RBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Already_Acc = findViewById(R.id.Already);
        TA_RBtn = findViewById(R.id.RBtn_TA);
        Tourist_RBtn = findViewById(R.id.RBtn_Tourist);
        Admin_RBtn = findViewById(R.id.RBtn_Admin);
        SignupTV = findViewById(R.id.signup_tv);
        Email = findViewById(R.id.Email_ET);
        Password = findViewById(R.id.Password_ET);
        LoginBtn = findViewById(R.id.btnLogin);
        getCheck = getIntent().getIntExtra("Check", 0);
        tinyDB = new TinyDB(this);


        Already_Acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Signup.class));
            }
        });
        SignupTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Signup.class));
            }
        });


        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Admin_RBtn.isChecked()) {
                    if (validation()) {
                        AdminLogin();
                    }
                } else if (Tourist_RBtn.isChecked()) {
                    if (validation()) {
                        UserLogin();
                    }

                } else if (TA_RBtn.isChecked()) {

                    if (validation()) {
                        TALogin();

                    }
                }


            }
        });
    }

    public boolean validation() {
        if (Email.getText().toString().isEmpty()) {
            Email.setError("Please Fill this Field");
            return false;
        }
        if (Password.getText().toString().isEmpty()) {
            Password.setError("Please Fill this Field");
            return false;
        }

        return true;
    }

    private void UserLogin() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        CusLogin service = RetrofitClient.getClient().create(CusLogin.class);
        Call<Customer> call = service.Login(Email.getText().toString(), Password.getText().toString());
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful()) {
                    customer = response.body();
                    if (!customer.isError()) {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, customer.getMessage(), Toast.LENGTH_SHORT).show();
                        if (customer.getC_status().equals("A")) {
                            tinyDB.putInt("CUSTOMER_ID", customer.getC_id());
                            tinyDB.putString("CUSTOMER_NAME", customer.getC_name());
                            tinyDB.putString("CUSTOMER_EMAIL", customer.getC_email());
                            tinyDB.putString("CUSTOMER_PASSWORD", customer.getC_password());
                            tinyDB.putString("CUSTOMER_PHONE", customer.getC_phone());
                            tinyDB.putString("CUSTOMER_CNIC", customer.getC_cnic());
                            tinyDB.putInt("LOGIN_PREF", 1);
                            startActivity(new Intent(Login.this, Users_Dashboard.class));
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, "Status Not Approved", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, response.message(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void TALogin() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        TA = new TravelAgency();
        TA_Login service = RetrofitClient.getClient().create(TA_Login.class);
        Call<TravelAgency> call = service.TALogin(Email.getText().toString(), Password.getText().toString());
        call.enqueue(new Callback<TravelAgency>() {
            @Override
            public void onResponse(Call<TravelAgency> call, Response<TravelAgency> response) {
                if (response.isSuccessful()) {
                    TA = response.body();
                    if (!TA.isError()) {
                        progressDialog.dismiss();
                        if (TA.getTa_status().equals("A")) {
                            tinyDB.putInt("TRAVEL_AGENCY_ID", TA.getTa_id());
                            tinyDB.putString("ta_name", TA.getTa_name());
                            tinyDB.putString("ta_email", TA.getTa_email());
                            tinyDB.putString("ta_phone", TA.getTa_phone());
                            tinyDB.putString("ta_cnic", TA.getTa_cnic());

                            tinyDB.putInt("LOGIN_PREF", 2);
                            Toast.makeText(Login.this, TA.getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, TA_Dashboard.class));

                        } else {
                            Toast.makeText(Login.this,
                                    "Status Not Approved", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, TA.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, response.message(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<TravelAgency> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void AdminLogin() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        admin = new Admin();
        AdminService service = RetrofitClient.getClient().create(AdminService.class);
        Call<Admin> call = service.Adminlogin(Email.getText().toString(),
                Password.getText().toString());
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if (response.isSuccessful()) {
                    admin = response.body();
                    if (!admin.isError()) {
                        progressDialog.dismiss();
                        tinyDB.putInt("a_id", admin.getA_id());
                        tinyDB.putString("a_email", Email.getText().toString());
                        tinyDB.putInt("LOGIN_PREF", 3);
                        Toast.makeText(Login.this, admin.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, Admin_Dashboard.class));
                    } else {
                        progressDialog.dismiss();


                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, response.message(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });


    }
}