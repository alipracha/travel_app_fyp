package com.example.triparrangersfyp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.Tourist.UpdateProfile_Tourist;
import com.example.triparrangersfyp.model.Complain;
import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.model.FeedbackModel;
import com.example.triparrangersfyp.service.AddFeedbackService;
import com.example.triparrangersfyp.service.InsertComplainAPIService;
import com.example.triparrangersfyp.service.UpdateTouristProfileService;
import com.example.triparrangersfyp.util.TinyDB;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintActivity extends AppCompatActivity {
 EditText CompDescription, Subject;
 Button CompBtn;
 Complain complain;
 TinyDB tinyDb;
 int getAgainstID, getSenderID;
 String getSenderType;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);


        CompDescription=findViewById(R.id.CompDes);
        Subject=findViewById(R.id.Subject);
        CompBtn=findViewById(R.id.SubmitComp);

        tinyDb = new TinyDB(this);

        getAgainstID = tinyDb.getInt("RECEIVER_ID");
        getSenderID = tinyDb.getInt("SENDER_ID");
        getSenderType = tinyDb.getString("SENDER_TYPE");

        CompBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              AddComplaint();

            }
        });

    }
    public void AddComplaint( )
    {
        complain=new Complain();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        InsertComplainAPIService ComplainService= RetrofitClient.getClient().create(InsertComplainAPIService.class);

        Call<Complain> call = ComplainService.InsertComplain( Subject.getText().toString(), CompDescription.getText().toString(),
                getSenderID, getAgainstID, getSenderType);

        call.enqueue(new Callback<Complain>() {
            @Override
            public void onResponse(Call<Complain> call, Response<Complain> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    complain = response.body();
                    if (!complain.isError()) {
                        Toast.makeText(ComplaintActivity.this,
                                complain.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ComplaintActivity.this,
                                complain.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Complain> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ComplaintActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}