package com.example.triparrangersfyp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.service.Customer_Signup;
import com.example.triparrangersfyp.service.TravelAgencySignupService;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    int getCheck;
    EditText Name, Email, Password, Phone, CNIC;
    TextView  Already_Acc, SignInTV;
    Button UploadImg, SignUpBtn;
    ImageView cover;
    RadioButton TA_RBtn, Tourist_RBtn;
    LinearLayout TravelAgencyLL;
    Customer customer;
    ProgressDialog progressDialog;
    File file;
    private Uri filePath;
    boolean isCameraClicked = false;
    boolean isGalleryImgClicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Name = findViewById(R.id.ET_Name);
        Email = findViewById(R.id.ET_Email);
        Password = findViewById(R.id.ET_Password);
        Phone = findViewById(R.id.ET_Phone);

        CNIC = findViewById(R.id.ET_Cnic);
        cover = findViewById(R.id.coverImg);
        UploadImg = findViewById(R.id.TV_Upload);
        Already_Acc = findViewById(R.id.AlreadyAcc);
        SignInTV = findViewById(R.id.SignInn);
        TA_RBtn = findViewById(R.id.TA_RBtn);
        Tourist_RBtn = findViewById(R.id.Tourist_RBtn);
        TravelAgencyLL = findViewById(R.id.TravelAgencyLLayout);
        SignUpBtn = findViewById(R.id.btnSignUp);

        TA_RBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    TravelAgencyLL.setVisibility(View.VISIBLE);
                } else {
                    TravelAgencyLL.setVisibility(View.GONE);
                }
            }
        });

        Tourist_RBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    TravelAgencyLL.setVisibility(View.GONE);
                }
            }
        });


        getCheck = getIntent().getIntExtra("Check", 0);

        Already_Acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this, Login.class));
            }
        });
        SignInTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this, Login.class));
            }
        });

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()  ) {
                    if (TA_RBtn.isChecked()) {
                        {
                            TASignup();
                        }
                    } else if (Tourist_RBtn.isChecked()) {

                        AddCustomers();
                    } else
                        Toast.makeText(Signup.this, "Please Select One Option", Toast.LENGTH_SHORT).show();
                }
            }
        });

        UploadImg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectImage(Signup.this);
            }
        });

    }

    private boolean validate()
    {

        boolean isvalid= true;
        String emailValid = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
        String namepattern="^[a-zA-Z]+$";


        String password="^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";

        if (Name.getText().toString().trim().isEmpty()) {
            Name.setError("please enter valid name");
            isvalid = false;
        }

       else if (!Name.getText().toString().trim().matches(namepattern)) {
            Name.setError("please enter valid name");
            isvalid = false;
        }

        if (Email.getText().toString().trim().isEmpty()) {
            Email.setError("please enter valid Email");
            isvalid = false;
        }
            else if (!Email.getText().toString().trim().matches(emailValid)) {
                Email.setError("Enter valid Email Pattern");
                isvalid = false;
            }



        if (Password.getText().toString().trim().isEmpty()) {
            Password.setError("please enter valid Password");
            isvalid = false;
        }

            else if (!Password.getText().toString().trim().matches(password))
            {
                Password.setError("Your Password is too weak. Please enter capital lettter, small letter, number and a symbol");
                isvalid = false;
            }
            
        if (Phone.getText().toString().trim().isEmpty())
        {
            Phone.setError("Please Enter Contact Number");
            isvalid = false;
        }
        else if ( Phone.getText().toString().trim().length() <11 || Phone.getText().toString().trim().length() >13) {
            Phone.setError("Enter Correct phone number");
            isvalid = false;
        }

        if (CNIC.getText().toString().trim().isEmpty())
        {
            CNIC.setError("Please Enter CNIC");
            isvalid = false;
        }
        else if ( CNIC.getText().toString().trim().length() <13 || CNIC.getText().toString().trim().length() >13) {
             CNIC.setError("Enter Correct CNIC");
            isvalid = false;
        }
        return isvalid;
    }

    private void AddCustomers() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        Customer_Signup service = RetrofitClient.getClient().create(Customer_Signup.class);
        Call<Customer> call = service.AddCustomer(Name.getText().toString(), Email.getText().toString(), Password.getText().toString(),
                Phone.getText().toString(), CNIC.getText().toString());
        call.enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                if (response.isSuccessful()) {
                    customer = response.body();
                    if (!customer.isError()) {
                        progressDialog.dismiss();
                        Toast.makeText(Signup.this, customer.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(Signup.this, customer.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(Signup.this, response.message(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Signup.this, t.getMessage(), Toast.LENGTH_SHORT).show();

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

                        cover.setImageBitmap(selectedImage);
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
                                cover.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }

    /**
     * Travel Agency Signup
     */
    TravelAgency travelAgency;

    private void TASignup() {
        travelAgency = new TravelAgency();
        progressDialog = new ProgressDialog(Signup.this);
        progressDialog.setTitle("Signing up");
        progressDialog.setMessage("please wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        if (filePath == null) {
            Toast.makeText(this, "no image", Toast.LENGTH_SHORT).show();
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
            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), Name.getText().toString());

            //creating request body for name
            RequestBody email = RequestBody.create(MediaType.parse("text/plain"), Email.getText().toString());

            //creating request body for name
            RequestBody password = RequestBody.create(MediaType.parse("text/plain"), Password.getText().toString());

            RequestBody phone = RequestBody.create(MediaType.parse("text/plain"), Phone.getText().toString());

            RequestBody cnic = RequestBody.create(MediaType.parse("text/plain"), CNIC.getText().toString());

            MultipartBody.Part body = MultipartBody.Part.createFormData("ta_image", file.getName(), requestFile);

            TravelAgencySignupService service = RetrofitClient.getClient().create(TravelAgencySignupService.class);
            Call<TravelAgency> call = service.TAnewAccount(name, email, password, phone, cnic, body);
            call.enqueue(new Callback<TravelAgency>() {
                @Override
                public void onResponse(Call<TravelAgency> call, Response<TravelAgency> response) {
                    if (response.isSuccessful()) {
                        progressDialog.dismiss();
                        travelAgency = response.body();
                        if (travelAgency != null) {
                            boolean error = travelAgency.isError();
                            if (!error) {
                                Toast.makeText(Signup.this,
                                        travelAgency.getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Signup.this, Login.class));
                                finish();
                            } else {
                                Toast.makeText(Signup.this,
                                        travelAgency.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Signup.this,
                                    travelAgency.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(Signup.this, travelAgency.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<TravelAgency> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(Signup.this,
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


}





