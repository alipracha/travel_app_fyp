package com.example.triparrangersfyp.Tourist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.service.GetTADetailsAgainstIDService;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewTADetails_User extends AppCompatActivity {
    TextView name, email, phonenum, cnic;
    ProgressDialog progressDialog;
    TravelAgency TA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tadetails_user);
        name = findViewById(R.id.TAName);
        email = findViewById(R.id.TAEmail);
        phonenum = findViewById(R.id.TAPhoneNum);
        cnic = findViewById(R.id.TACNIC);

        TADetails();
    }

    private void TADetails() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        TA = new TravelAgency();
        GetTADetailsAgainstIDService service = RetrofitClient.getClient().create(GetTADetailsAgainstIDService.class);
        Call<TravelAgency> call = service.getTADetailsAgainstID(getIntent().getIntExtra("TA_ID", 0));
        call.enqueue(new Callback<TravelAgency>() {
            @Override
            public void onResponse(Call<TravelAgency> call, Response<TravelAgency> response) {
                if (response.isSuccessful()) {
                    TA = response.body();
                    if (!TA.isError()) {
                        progressDialog.dismiss();
                        Toast.makeText(ViewTADetails_User.this, TA.getMessage(),
                                Toast.LENGTH_SHORT).show();
                        name.setText(TA.getTa_name());
                        email.setText(TA.getTa_email());
                        phonenum.setText(TA.getTa_phone());
                        cnic.setText(TA.getTa_cnic());


                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(ViewTADetails_User.this, TA.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(ViewTADetails_User.this, response.message(), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<TravelAgency> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewTADetails_User.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });


    }
}



