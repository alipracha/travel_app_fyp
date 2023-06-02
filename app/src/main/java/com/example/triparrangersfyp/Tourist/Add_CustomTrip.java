package com.example.triparrangersfyp.Tourist;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.loader.content.CursorLoader;

import com.example.triparrangersfyp.BuildConfig;
import com.example.triparrangersfyp.ComplaintActivity;
import com.example.triparrangersfyp.Feedback;
import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.TermsAndConditions;
import com.example.triparrangersfyp.TravelAgency.AddTrip;
import com.example.triparrangersfyp.TravelAgency.Detailed_Trip;
import com.example.triparrangersfyp.TravelAgency.TA_Dashboard;
import com.example.triparrangersfyp.TravelAgency.TA_Trips;
import com.example.triparrangersfyp.TravelAgency.UpdateProfile_TA;
import com.example.triparrangersfyp.model.Complain;
import com.example.triparrangersfyp.model.CustomTrip;
import com.example.triparrangersfyp.service.AddCustomTripService;
import com.example.triparrangersfyp.service.InsertComplainAPIService;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_CustomTrip extends AppCompatActivity {

    TextInputEditText ctitle, cdescription, cDepTV, cArrTV, cDepTime, cArrTime, cPickup, cDropoff ;
    DatePickerDialog.OnDateSetListener csetListener1, csetListener2;
    Button caddbtn;
    ProgressDialog progressDialog;
    CustomTrip customTrip;
    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img;
    Toolbar toolbar;
    TinyDB tinyDb;


    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.icon_menu_ta, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_trip);
        ctitle = findViewById(R.id.ET_Title_Custom);
        caddbtn = findViewById(R.id.Add_CustomTrip);
        cdescription = findViewById(R.id.ET_Des_Custom);
        cDepTV = findViewById(R.id.ET_DepD_Custom);
        cArrTV = findViewById(R.id.ET_ArrD_Custom);
        cDepTime = findViewById(R.id.ET_DepT_Custom);
        cArrTime = findViewById(R.id.ET_ArrT_Custom);
        cPickup = findViewById(R.id.ET_Pickup_Custom);
        cDropoff = findViewById(R.id.ET_Dropoff_Custom);

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
                    new AlertDialog.Builder(Add_CustomTrip.this).setIcon(R.drawable.ic_baseline_warning_24)
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
                    startActivity(new Intent(Add_CustomTrip.this, Tourists_Options.class));
                }
                else if (item.getItemId() == R.id.TouristTermsAndConditions) {
                    startActivity(new Intent(Add_CustomTrip.this, TermsAndConditions.class));
                }
                else if (item.getItemId() == R.id.TouristAboutUs) {
                    final Dialog dialog = new Dialog(Add_CustomTrip.this);
                    dialog.setContentView(R.layout.aboutus);
                    dialog.show();

                }

                return true;
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        cDepTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_CustomTrip.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, csetListener1, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });
        csetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                cDepTV.setText(date);
            }
        };

        caddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate())
                Add_Custom_Trip();
            }
        });
//
//Arrival Time
        cArrTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_CustomTrip.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, csetListener2, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });
        csetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                cArrTV.setText(date);
            }
        };

        cDepTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Add_CustomTrip.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        cDepTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        cArrTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Add_CustomTrip.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        cArrTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

    }

    private boolean validate()
    {

        boolean isvalid= true;

        if (ctitle.getText().toString().trim().isEmpty()) {
            ctitle.setError("Please Enter Trip Title");
            isvalid = false;
        }

        if (cdescription.getText().toString().trim().isEmpty()) {
            cdescription.setError("Please Enter Trip Description");
            isvalid = false;
        }

        if (cDepTV.getText().toString().trim().isEmpty())
        {
            cDepTV.setError("Please Choose Depaarture Date");
            isvalid = false;
        }

        if (cArrTV.getText().toString().trim().isEmpty())
        {
            cArrTV.setError("Please Choose Arrival Date");
            isvalid = false;
        }
        if (cDepTime.getText().toString().trim().isEmpty())
        {
            cDepTime.setError("Please Choose Departure Time");
            isvalid = false;
        }
        if (cArrTime.getText().toString().trim().isEmpty())
        {
            cArrTime.setError("Please Choose Arrival Time");
            isvalid = false;
        }

        if (cPickup.getText().toString().trim().isEmpty())
        {
            cPickup.setError("Please Enter Pickup Location");
            isvalid = false;
        }

        if (cDropoff.getText().toString().trim().isEmpty())
        {
            cDropoff.setError("Please Enter Dropoff Location");
            isvalid = false;
        }

        return isvalid;
    }
    public void Add_Custom_Trip( )
    {
        customTrip=new CustomTrip();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        AddCustomTripService Servicee= RetrofitClient.getClient().create(AddCustomTripService.class);

        Call<CustomTrip> call = Servicee.Addcustomtrip(ctitle.getText().toString(),
                cdescription.getText().toString(),  cDepTV.getText().toString(),cArrTV.getText().toString(),
                cDepTime.getText().toString(),cArrTime.getText().toString(), cPickup.getText().toString(),
                cDropoff.getText().toString(),tinyDb.getInt("CUSTOMER_ID"));

        call.enqueue(new Callback<CustomTrip>() {
            @Override
            public void onResponse(Call<CustomTrip> call, Response<CustomTrip> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    customTrip = response.body();
                    if (!customTrip.isError()) {
                        Toast.makeText(Add_CustomTrip.this,
                                customTrip.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Add_CustomTrip.this,
                                customTrip.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomTrip> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Add_CustomTrip.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}



