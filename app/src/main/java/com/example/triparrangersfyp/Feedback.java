package com.example.triparrangersfyp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.triparrangersfyp.Adapter.SeatAdapter;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.Tourist.ShowSeatsActivity;
import com.example.triparrangersfyp.model.FeedbackModel;
import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.service.AddFeedbackService;
import com.example.triparrangersfyp.service.GetSeatsService;
import com.example.triparrangersfyp.util.TinyDB;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Feedback extends AppCompatActivity {

    RatingBar ratingBar;
    Button submitFeedback;
    EditText msg;
    TinyDB tinyDb;
    FeedbackModel feedbackModel;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ratingBar=findViewById(R.id.RatingBar);
        submitFeedback=findViewById(R.id.SubmitFeedback);
        msg=findViewById(R.id.Message);
        tinyDb = new TinyDB(this);
        LayerDrawable stars=(LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        submitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(msg.getText().toString().isEmpty())
                    Toast.makeText(Feedback.this, "Required Field Missing", Toast.LENGTH_SHORT).show();
                else
                    CustomerFeedback(ratingBar.getRating(), msg.getText().toString(), tinyDb.getInt("CUSTOMER_ID"));

            }
        });

    }
    public void CustomerFeedback(float rating, String s, int c_id)
    {
        feedbackModel=new FeedbackModel();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        AddFeedbackService feedbackService= RetrofitClient.getClient().create(AddFeedbackService.class);
        Call<FeedbackModel> call = feedbackService.addFeedback(rating,s,c_id);

        call.enqueue(new Callback<FeedbackModel>()
        {
            @Override
            public void onResponse(Call<FeedbackModel> call, Response<FeedbackModel> response)
            {
                if (response.isSuccessful())
                {

                    feedbackModel = response.body();
                    if (!feedbackModel.isError())
                    {
                        progressDialog.dismiss();
                        Toast.makeText(Feedback.this,
                                feedbackModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Feedback.this,
                                feedbackModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<FeedbackModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Feedback.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}