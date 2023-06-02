package com.example.triparrangersfyp.TravelAgency;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.service.ChangePasswordService;
import com.example.triparrangersfyp.service.UpdateTravelAgencyDataService;
import com.example.triparrangersfyp.util.TinyDB;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfile_TA extends AppCompatActivity {
    TextView name, email, phonenum, cnic;
    int gettaID;
    String taname, taemail, taphone, tacnic;
    EditText confirmPassword, newPassword, CurrentPassword;
    TravelAgency TA;
    ProgressDialog progressDialog;
    Button update,  ChangePassword;
    TinyDB tinyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_ta);

        name = findViewById(R.id.TA_NameED);
        email = findViewById(R.id.TA_EmailED);
        phonenum = findViewById(R.id.TA_PhoneNumED);
        cnic = findViewById(R.id.TA_CNICED);
        ChangePassword=findViewById(R.id.ChangePassword);
        update = findViewById(R.id.UpdateProfile);


        tinyDB = new TinyDB(this);

        gettaID = tinyDB.getInt("TRAVELAGENCY_ID");
        taname = tinyDB.getString("ta_name");
        taemail = tinyDB.getString("ta_email");
        taphone = tinyDB.getString("ta_phone");
        tacnic = tinyDB.getString("ta_cnic");


        name.setText(taname);
        email.setText(taemail);
        phonenum.setText(taphone);
        cnic.setText(tacnic);

        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(UpdateProfile_TA.this);
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
                TA = new TravelAgency();
                progressDialog = new ProgressDialog(UpdateProfile_TA.this);
                progressDialog.setMessage("Please Wait...");
                progressDialog.show();

                ChangePasswordService service = RetrofitClient.getClient().create(ChangePasswordService.class);
                Call<TravelAgency> call = service.UpdateTravelAgencyPassword(tinyDB.getInt("TRAVEL_AGENCY_ID"),
                        CurrentPassword.getText().toString(),
                        newPassword.getText().toString());
                call.enqueue(new Callback<TravelAgency>() {
                    @Override
                    public void onResponse(Call<TravelAgency> call, Response<TravelAgency> response) {
                        if(response.isSuccessful()){
                            progressDialog.dismiss();
                            TA=response.body();
                            if(!TA.isError()){
                                Toast.makeText(getApplicationContext(),
                                        TA.getMessage(), Toast.LENGTH_SHORT).show();
                                tinyDB.clear();
                                startActivity(new Intent(getApplicationContext(),
                                        Login.class));
                                finishAffinity();

                            }else{
                                Toast.makeText(getApplicationContext(),
                                        TA.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<TravelAgency> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),
                                t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                Update_TravelAgencyData();

            }
        });


    }

    private void Update_TravelAgencyData() {
        TA = new TravelAgency();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        UpdateTravelAgencyDataService service = RetrofitClient.getClient().create(UpdateTravelAgencyDataService.class);
        Call<TravelAgency> call = service.updateTAData(gettaID, name.getText().toString(), email.getText().toString(),
                phonenum.getText().toString(), cnic.getText().toString());

        call.enqueue(new Callback<TravelAgency>() {
            @Override
            public void onResponse(Call<TravelAgency> call, Response<TravelAgency> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    TA = response.body();
                    if (!TA.isError()) {
                        Toast.makeText(UpdateProfile_TA.this,
                                TA.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateProfile_TA.this,
                                TA.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TravelAgency> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UpdateProfile_TA.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
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

        if (name.getText().toString().trim().isEmpty()) {
            name.setError("Please Enter Name");
            isvalid = false;
        }

        else if (!name.getText().toString().trim().matches(namepattern)) {
            name.setError("Please Enter Valid Name");
            isvalid = false;
        }

        if (email.getText().toString().trim().isEmpty()) {
            email.setError("Please Enter Email");
            isvalid = false;
        }
        else if (!email.getText().toString().trim().matches(emailValid)) {
            email.setError("Please Enter Valid Email");
            isvalid = false;
        }


        if (phonenum.getText().toString().trim().isEmpty())
        {
            phonenum.setError("Please Enter Contact Number");
            isvalid = false;
        }
        else if ( phonenum.getText().toString().trim().length() <11 || phonenum.getText().toString().trim().length() >13) {
            phonenum.setError("Enter Correct phone number");
            isvalid = false;
        }

        if (cnic.getText().toString().trim().isEmpty())
        {
            cnic.setError("Please Enter CNIC");
            isvalid = false;
        }
        else if ( cnic.getText().toString().trim().length() <13 || cnic.getText().toString().trim().length() >13) {
            cnic.setError("Enter Correct CNIC");
            isvalid = false;
        }
        return isvalid;
    }

        private boolean Validation() {

            if (CurrentPassword.getText().toString().isEmpty()) {
                CurrentPassword.setError("Fill this Field");
                return false;
            } else if (newPassword.getText().toString().isEmpty()) {
                newPassword.setError("Fill this Field");
                return false;
            } else if (confirmPassword.getText().toString().isEmpty()) {
                confirmPassword.setError("Fill this Field");
                return false;
            } else if (!confirmPassword.getText().toString().equals(newPassword.getText().toString())) {
                confirmPassword.setError("Password not Matched");
                return false;
            }

            return true;
        }
    }
