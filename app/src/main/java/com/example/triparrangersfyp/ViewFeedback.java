package com.example.triparrangersfyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.triparrangersfyp.Adapter.FeedbackAdapter;
import com.example.triparrangersfyp.Admin.Admin_Dashboard;
import com.example.triparrangersfyp.Admin.UpdateAdminProfile;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.FeedbackModel;
import com.example.triparrangersfyp.service.GetFeedbackService;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewFeedback extends AppCompatActivity {

    ListView listView;
    List<FeedbackModel> feedbackList = new ArrayList<>();
    ProgressDialog progressDialog;
    FeedbackAdapter adapter;
    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img;
    Toolbar toolbar;
    TinyDB tinyDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);
        listView= findViewById(R.id.Feedback_ListView);
        tinyDb = new TinyDB(this);
        nav1= findViewById(R.id.Anav_menuU);
        drawer = findViewById(R.id.Admin_Drawer);
        img= findViewById(R.id.AdminNavImage);
        toolbar=findViewById(R.id.Admin_Toolbar);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });


        nav1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.Admin_Menu_Home) {

                    startActivity(new Intent(getApplicationContext(), Admin_Dashboard.class));
                }

                if (item.getItemId() == R.id.Admin_Manage_Profile) {

                    startActivity(new Intent(getApplicationContext(), UpdateAdminProfile.class));
                }

                else if (item.getItemId() == R.id.Admin_Menu_Share) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Share Demo");
                        String shareMessage = "https://play.google.com/store/apps/details?=" + BuildConfig.APPLICATION_ID + "\n\n";
                        intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(intent, "ShareVia"));
                    } catch (Exception e) {
                        Toast.makeText(ViewFeedback.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                }
                else if (item.getItemId() == R.id.Admin_Menu_LogOut) {

                    new AlertDialog.Builder(ViewFeedback.this).setIcon(R.drawable.ic_baseline_warning_24)
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
                }

                return true;
            }

        });

        GetFeedbacks();
    }

    private void GetFeedbacks() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("please wait..");
        progressDialog.show();

        GetFeedbackService service = RetrofitClient.getClient().create(GetFeedbackService.class);
        Call<JsonObject> call = service.getFeedback();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().getAsJsonObject().toString());

                        JSONArray jsonArray = jsonObject.getJSONArray("records");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject data = jsonArray.getJSONObject(i);

                            feedbackList.add(new FeedbackModel(
                                    data.getDouble("feedback_star"),
                                    data.getString("feedback_msg"),
                                    data.getString("feedback_datetime")

                            ));


                        }
                        adapter = new FeedbackAdapter(feedbackList,
                                ViewFeedback.this);
                        listView.setAdapter(adapter);

                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewFeedback.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}
