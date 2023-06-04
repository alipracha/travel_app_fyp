package com.example.triparrangersfyp.TravelAgency;

import android.app.DatePickerDialog;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.loader.content.CursorLoader;

import com.example.triparrangersfyp.BuildConfig;
import com.example.triparrangersfyp.Feedback;
import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.TermsAndConditions;
import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.service.AddTripService;
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

public class AddTrip extends AppCompatActivity {

    FloatingActionButton pickImage;
    TextInputEditText title, description, DepTV, ArrTV, DepTime, ArrTime, Pickup, Dropoff, payment, seats;
    DatePickerDialog.OnDateSetListener setListener1, setListener2;
    ImageView image;
    Button addbtn;
    ProgressDialog progressDialog;
    File file;
    private Uri filePath;
    boolean isCameraClicked = false;
    boolean isGalleryImgClicked = false;

    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img;
    Toolbar toolbar;
    TinyDB tinyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        tinyDB = new TinyDB(this);
        title = findViewById(R.id.ET_Title);
        addbtn = findViewById(R.id.TripAdd);
        description = findViewById(R.id.ET_Des);
        DepTV = findViewById(R.id.ET_DepD);
        ArrTV = findViewById(R.id.ET_ArrD);
        DepTime = findViewById(R.id.ET_DepT);
        pickImage = findViewById(R.id.PickImage);
        ArrTime = findViewById(R.id.ET_ArrT);
        Pickup = findViewById(R.id.PickupLocation);
        Dropoff = findViewById(R.id.dropoff_ET);
        seats = findViewById(R.id.Seats_ET);
        payment = findViewById(R.id.ET_Price);
        image = findViewById(R.id.LogoAdd);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        nav1 = findViewById(R.id.nav_menuU);
        drawer = findViewById(R.id.TA_Drawer);
        img = findViewById(R.id.TANavImage);
        toolbar = findViewById(R.id.TA_Toolbar);
        tinyDB = new TinyDB(this);

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
                    new android.app.AlertDialog.Builder(AddTrip.this).setIcon(R.drawable.ic_baseline_warning)
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

                } else if (item.getItemId() == R.id.TAMenu_Profile) {
                    startActivity(new Intent(getApplicationContext(), UpdateProfile_TA.class));
                } else if (item.getItemId() == R.id.TAMenu_Feedback) {
                    startActivity(new Intent(getApplicationContext(), Feedback.class));
                } else if (item.getItemId() == R.id.TATermsAndConditions) {
                    startActivity(new Intent(getApplicationContext(), TermsAndConditions.class));
                } else if (item.getItemId() == R.id.TAMenu_Share) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Share Demo");
                        String shareMessage = "https://play.google.com/store/apps/details?=" + BuildConfig.APPLICATION_ID + "\n\n";
                        intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(intent, "ShareVia"));
                    } catch (Exception e) {
                        Toast.makeText(AddTrip.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                }
                return true;
            }

        });


        DepTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTrip.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener1, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });
        setListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                DepTV.setText(date);
            }
        };

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                    AddTrip();
            }
        });
//
//Arrival Time
        ArrTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTrip.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener2, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });
        setListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                ArrTV.setText(date);
            }
        };

        DepTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddTrip.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        DepTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        ArrTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddTrip.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        ArrTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        pickImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectImage(AddTrip.this);
            }
        });
    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        isCameraClicked = true;
                        isGalleryImgClicked = false;
                        filePath = data.getData();
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");

                        image.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        isCameraClicked = false;
                        isGalleryImgClicked = true;
                        filePath = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }

    /**
     * Add Trip
     */
    Trips addTrip;

    private void AddTrip() {
        addTrip = new Trips();
        progressDialog = new ProgressDialog(AddTrip.this);
        progressDialog.setTitle("Adding Trip");
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        if (filePath == null) {
            Toast.makeText(this, "No Image Added", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        } else {
            if (isCameraClicked) {
                file = new File(String.valueOf(filePath));
            } else {
                file = new File(getRealPathFromURI(filePath));
            }
            //creating request body for file
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);


            //creating request body for name
            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), title.getText().toString());

            //creating request body for name
            RequestBody Description = RequestBody.create(MediaType.parse("text/plain"), description.getText().toString());

            //creating request body for name
            RequestBody deptv = RequestBody.create(MediaType.parse("text/plain"), DepTV.getText().toString());

            RequestBody Arrtv = RequestBody.create(MediaType.parse("text/plain"), ArrTV.getText().toString());

            RequestBody deptime = RequestBody.create(MediaType.parse("text/plain"), DepTime.getText().toString());
            RequestBody arrtime = RequestBody.create(MediaType.parse("text/plain"), ArrTime.getText().toString());
            RequestBody pickup = RequestBody.create(MediaType.parse("text/plain"), Pickup.getText().toString());
            RequestBody dropoff = RequestBody.create(MediaType.parse("text/plain"), Dropoff.getText().toString());
            RequestBody seat = RequestBody.create(MediaType.parse("text/plain"), seats.getText().toString());
            RequestBody Payment = RequestBody.create(MediaType.parse("text/plain"), payment.getText().toString());
            RequestBody travel_agency_id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(tinyDB.getInt("TRAVEL_AGENCY_ID")));

            MultipartBody.Part body = MultipartBody.Part.createFormData("trip_image", file.getName(), requestFile);

            AddTripService service = RetrofitClient.getClient().create(AddTripService.class);
            Call<Trips> call = service.AddTrip(name, Description, deptv, Arrtv,
                    deptime, arrtime, pickup, dropoff, seat, Payment, travel_agency_id, body);
            call.enqueue(new Callback<Trips>() {
                @Override
                public void onResponse(Call<Trips> call, Response<Trips> response) {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        addTrip = response.body();
                        if (addTrip != null) {
                            boolean error = addTrip.isError();
                            if (!error) {
                                Toast.makeText(AddTrip.this,
                                        addTrip.getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddTrip.this, TA_Trips.class));
                                finish();
                            } else {
                                Toast.makeText(AddTrip.this,
                                        addTrip.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(AddTrip.this,
                                    addTrip.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(AddTrip.this, addTrip.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Trips> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(AddTrip.this,
                            t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj,
                null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    private boolean validate()
    {
        String taname, taemail, taphone, tacnic;
        boolean isvalid= true;

        if (title.getText().toString().trim().isEmpty()) {
            title.setError("Please Enter Trip Title");
            isvalid = false;
        }

        if (description.getText().toString().trim().isEmpty()) {
            description.setError("Please Enter Trip Description");
            isvalid = false;
        }

        if (DepTV.getText().toString().trim().isEmpty())
        {
            DepTV.setError("Please Choose Depaarture Date");
            isvalid = false;
        }

        if (ArrTV.getText().toString().trim().isEmpty())
        {
            ArrTV.setError("Please Choose Arrival Date");
            isvalid = false;
        }
        if (DepTime.getText().toString().trim().isEmpty())
        {
            DepTime.setError("Please Choose Departure Time");
            isvalid = false;
        }
        if (ArrTime.getText().toString().trim().isEmpty())
        {
            ArrTime.setError("Please Choose Arrival Time");
            isvalid = false;
        }

        if (Pickup.getText().toString().trim().isEmpty())
        {
            Pickup.setError("Please Enter Pickup Location");
            isvalid = false;
        }

        if (Dropoff.getText().toString().trim().isEmpty())
        {
            Dropoff.setError("Please Enter Dropoff Location");
            isvalid = false;
        }
        if (payment.getText().toString().trim().isEmpty())
        {
            payment.setError("Please Enter Payment");
            isvalid = false;
        }

        if (seats.getText().toString().trim().isEmpty())
        {
            seats.setError("Please Enter seats");
            isvalid = false;
        }
        return isvalid;
    }

}




