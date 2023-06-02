package com.example.triparrangersfyp.TravelAgency;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.Booking;
import com.example.triparrangersfyp.service.UpdateBookingStatusService;
import com.example.triparrangersfyp.util.Endpoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewBookingRequests_TA extends AppCompatActivity {

    TextView title, name;
    String TripTitle, CName;
    int getBookingID;
    ImageView image;
    Button approve, reject;
    Booking booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking_requests_ta);

        title = findViewById(R.id.title_TripB);
        name = findViewById(R.id.descriptionB);
        approve = findViewById(R.id.Approvebtnn);
        reject = findViewById(R.id.Rejectbtnn);
        image=findViewById(R.id.requestPic);

        getBookingID = getIntent().getIntExtra("b_id", 0);
        TripTitle = getIntent().getStringExtra("trip_title");
        CName = getIntent().getStringExtra("c_name");

        Glide.with(this)
                .load(Endpoint.IMAGE_URL + getIntent().getStringExtra("trip_image"))
                .into(image);
        title.setText(TripTitle);
        name.setText(CName);


        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateStatus(getBookingID, "A");
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateStatus(getBookingID, "B");
            }
        });


    }
    private void UpdateStatus(int getBookingID, String r) {
        booking = new Booking();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait...");
        progressDialog.show();

        UpdateBookingStatusService service = RetrofitClient.getClient().create( UpdateBookingStatusService.class);
        Call<Booking> call = service.updateBookingStatus(getBookingID, r);


        call.enqueue(new Callback<Booking>() {
            @Override
            public void onResponse(Call<Booking> call, Response<Booking> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    booking = response.body();
                    if (! booking.isError()) {
                        Toast.makeText(ViewBookingRequests_TA.this,
                                booking.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ViewBookingRequests_TA.this,
                                booking.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Booking> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewBookingRequests_TA.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}