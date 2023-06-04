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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.triparrangersfyp.Feedback;
import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.TermsAndConditions;
import com.example.triparrangersfyp.TravelAgency.Detailed_Trip;
import com.example.triparrangersfyp.TravelAgency.UpdateProfile_TA;
import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.service.ChangePasswordService;
import com.example.triparrangersfyp.service.ChangePasswordTouristService;
import com.example.triparrangersfyp.service.UpdateTouristProfileService;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfile_Tourist extends AppCompatActivity {
    int getCID;
    String Cname, Cemail, Cphone, CPassword, Ccnic;
    EditText Cus_name, Cus_email, Cus_phone, Cus_cnic;
    ProgressDialog progressDialog;
    EditText confirmPassword, newPassword, CurrentPassword;
    Button Update, ChangePassword;
    Customer customer;
    TinyDB tinyDB;
    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_tourist);
        Cus_name = findViewById(R.id.U_NameED);
        Cus_email = findViewById(R.id.U_EmailED);
        Cus_phone = findViewById(R.id.U_PhoneNumED);
        Cus_cnic = findViewById(R.id.U_CNICED);
        Update = findViewById(R.id.Updatebtn);
        ChangePassword=findViewById(R.id.ChangePassTourist);
        tinyDB = new TinyDB(this);
        nav1 = findViewById(R.id.nav_menuu);
        drawer = findViewById(R.id.User_drawerr);
        img = findViewById(R.id.UserNavImage);
        toolbar = findViewById(R.id.User_Toolbar);


        getCID= tinyDB.getInt("CUSTOMER_ID");
        Cname= tinyDB.getString("CUSTOMER_NAME");
        Cemail = tinyDB.getString("CUSTOMER_EMAIL");
        CPassword = tinyDB.getString("CUSTOMER_PASSWORD");
        Cphone = tinyDB.getString("CUSTOMER_PHONE");
        Ccnic = tinyDB.getString("CUSTOMER_CNIC");


        Cus_name.setText(Cname);
        Cus_email.setText(Cemail);
        Cus_phone.setText(Cphone);
        Cus_cnic.setText(Ccnic);

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
                    new AlertDialog.Builder(UpdateProfile_Tourist.this).setIcon(R.drawable.ic_baseline_warning)
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
                    startActivity(new Intent(UpdateProfile_Tourist.this, Tourists_Options.class));
                }
                else if (item.getItemId() == R.id.TouristTermsAndConditions) {
                    startActivity(new Intent(UpdateProfile_Tourist.this, TermsAndConditions.class));
                }
                else if (item.getItemId() == R.id.TouristAboutUs) {
                    final Dialog dialog = new Dialog(UpdateProfile_Tourist.this);
                    dialog.setContentView(R.layout.aboutus);
                    dialog.show();

                }

                return true;
            }
        });
        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(UpdateProfile_Tourist.this);
                dialog.setContentView(R.layout.changepassword);
                dialog.show();
                CurrentPassword = dialog.findViewById(R.id.CurrentPassword);
                newPassword = dialog.findViewById(R.id.NewPassword);
                confirmPassword = dialog.findViewById(R.id.ConfirmPassword);
                Button btn_change_password = dialog.findViewById(R.id.passChange);


                btn_change_password.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Validation())
                            Changepassword();

                    }
                });
            }


            private void Changepassword() {
                customer = new Customer();
                progressDialog = new ProgressDialog(UpdateProfile_Tourist.this);
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();

                ChangePasswordTouristService service = RetrofitClient.getClient().create(ChangePasswordTouristService.class);
                Call<Customer> call = service.UpdateCustomerPassword(tinyDB.getInt("CUSTOMER_ID"),
                        CurrentPassword.getText().toString(),
                        newPassword.getText().toString());
                call.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        if(response.isSuccessful()){
                            progressDialog.dismiss();
                            customer=response.body();
                            if(!customer.isError()){
                                Toast.makeText(getApplicationContext(),
                                        customer.getMessage(), Toast.LENGTH_SHORT).show();
                                tinyDB.clear();
                                startActivity(new Intent(getApplicationContext(),
                                        Login.class));
                                finishAffinity();

                            }else{
                                Toast.makeText(getApplicationContext(),
                                        customer.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),
                                t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    Update_TouristData();
            }
            }
        });


    }
    private boolean validate()
    {
        String taname, taemail, taphone, tacnic;
        boolean isvalid= true;
        String emailValid = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
        String namepattern="^[a-zA-Z]+$";

        String password="^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";

        if (Cus_name.getText().toString().trim().isEmpty()) {
            Cus_name.setError("Please Enter Name");
            isvalid = false;
        }

        else if (!Cus_name.getText().toString().trim().matches(namepattern)) {
            Cus_name.setError("Please Enter Valid Name");
            isvalid = false;
        }

        if (Cus_email.getText().toString().trim().isEmpty()) {
            Cus_email.setError("Please Enter Email");
            isvalid = false;
        }
        else if (!Cus_email.getText().toString().trim().matches(emailValid)) {
            Cus_email.setError("Please Enter Valid Email");
            isvalid = false;
        }


        if (Cus_phone.getText().toString().trim().isEmpty())
        {
            Cus_phone.setError("Please Enter Contact Number");
            isvalid = false;
        }
        else if ( Cus_phone.getText().toString().trim().length() <11 || Cus_phone.getText().toString().trim().length() >13) {
            Cus_phone.setError("Enter Correct phone number");
            isvalid = false;
        }

        if (Cus_cnic.getText().toString().trim().isEmpty())
        {
            Cus_cnic.setError("Please Enter CNIC");
            isvalid = false;
        }
        else if ( Cus_cnic.getText().toString().trim().length() <13 || Cus_cnic.getText().toString().trim().length() >13) {
            Cus_cnic.setError("Enter Correct CNIC");
            isvalid = false;
        }
        return isvalid;
    }

    private void Update_TouristData() {
        customer = new Customer();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        UpdateTouristProfileService service = RetrofitClient.getClient().create(UpdateTouristProfileService.class);
        Call<Customer> call = service.updateTouristData(getCID, Cus_name.getText().toString(), Cus_email.getText().toString(),
                Cus_phone.getText().toString(), Cus_cnic.getText().toString());

        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    customer = response.body();
                    if (!customer.isError()) {
                        Toast.makeText(UpdateProfile_Tourist.this,
                                customer.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateProfile_Tourist.this,
                                customer.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UpdateProfile_Tourist.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    } private boolean Validation() {

        if (CurrentPassword.getText().toString().isEmpty()) {
            CurrentPassword.setError("fill this field");
            return false;
        } else if (newPassword.getText().toString().isEmpty()) {
            newPassword.setError("fill this field");
            return false;
        } else if (confirmPassword.getText().toString().isEmpty()) {
            confirmPassword.setError("fill this field");
            return false;
        } else if (!confirmPassword.getText().toString().equals(newPassword.getText().toString())) {
            confirmPassword.setError("password not matched");
            return false;
        }

        return true;
    }
}