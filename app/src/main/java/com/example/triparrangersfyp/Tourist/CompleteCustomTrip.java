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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.triparrangersfyp.Adapter.ExpenseAdapter;
import com.example.triparrangersfyp.Feedback;
import com.example.triparrangersfyp.Login;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.TermsAndConditions;
import com.example.triparrangersfyp.model.ExpenseModel;
import com.example.triparrangersfyp.model.NotificationModel;
import com.example.triparrangersfyp.service.GetExpenseService;
import com.example.triparrangersfyp.service.InsertNotificationService;
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


public class CompleteCustomTrip extends AppCompatActivity {

    ListView listView;
    TextView tv;
    Button btn;
    int tripID;
    List<ExpenseModel> expenseModelList = new ArrayList<>();
    ProgressDialog progressDialog;
    ExpenseAdapter adapter;
    int totalExpense = 0;
    List<Integer> totalMembers = new ArrayList<>();
    String individualPrice;
    NotificationModel notificationModel;
   /* DrawerLayout drawer;
    NavigationView nav1;*/
    ImageView img;
    Toolbar toolbar;
    TinyDB tinyDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_custom_trip);

        listView = findViewById(R.id.TotalExpenseListView);
        tv = findViewById(R.id.TotalExpense);
        btn = findViewById(R.id.submitTotalExpense);
        tinyDb = new TinyDB(this);
    /*    nav1 = findViewById(R.id.nav_menuu);
        drawer = findViewById(R.id.User_drawerr);*/
        img = findViewById(R.id.UserNavImage);
        toolbar = findViewById(R.id.User_Toolbar);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // drawer.openDrawer(GravityCompat.START);
            }
        });
       /* nav1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.Menu_Home) {
                    startActivity(new Intent(getApplicationContext(), Users_Dashboard.class));
                } else if (item.getItemId() == R.id.MyBookings) {
                    startActivity(new Intent(getApplicationContext(), ConfirmedBookings_User.class));
                } else if (item.getItemId() == R.id.Menu_LogOut) {
                    new AlertDialog.Builder(CompleteCustomTrip.this).setIcon(R.drawable.ic_baseline_warning_24)
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
                    startActivity(new Intent(CompleteCustomTrip.this, Tourists_Options.class));
                }
                else if (item.getItemId() == R.id.TouristTermsAndConditions) {
                    startActivity(new Intent(CompleteCustomTrip.this, TermsAndConditions.class));
                }
                else if (item.getItemId() == R.id.TouristAboutUs) {
                    final Dialog dialog = new Dialog(CompleteCustomTrip.this);
                    dialog.setContentView(R.layout.aboutus);
                    dialog.show();

                }

                return true;
            }
        });
*/

        tripID = getIntent().getIntExtra("ctrip_id", 0);
        GetExpense(tripID);

        tv.setText(String.valueOf(totalExpense));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalMembers.size() != 0) {
                    individualPrice = String.valueOf(totalExpense / totalMembers.size());
                    for (int i = 0; i < totalMembers.size(); i++) {
                        InsertNotificationn("Trip Completed", "B",
                                tripID, "T",
                                totalMembers.get(i), individualPrice);

                    }
                }

            }
        });
    }

    private void InsertNotificationn(String notic_title, String typee, int typeid, String notic_for,
                                     int notic_for_id, String price) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        notificationModel = new NotificationModel();

        InsertNotificationService service = RetrofitClient.getClient().create(InsertNotificationService.class);
        Call<NotificationModel> call = service.InsertNotification(notic_title, typee, typeid, notic_for,
                notic_for_id, price);
        call.enqueue(new Callback<NotificationModel>() {
            @Override
            public void onResponse(Call<NotificationModel> call, Response<NotificationModel> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    notificationModel = response.body();
                    if (!notificationModel.isError()) {
                        Toast.makeText(CompleteCustomTrip.this,
                                notificationModel.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CompleteCustomTrip.this,
                                notificationModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<NotificationModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CompleteCustomTrip.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void GetExpense(int TripID) {
        totalMembers.clear();
        expenseModelList.clear();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        GetExpenseService service = RetrofitClient.getClient().create(GetExpenseService.class);
        Call<JsonObject> call = service.getExpense(TripID);
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
                            expenseModelList.add(new ExpenseModel(
                                    data.getInt("Texpense_ID"),
                                    data.getInt("Trip_ID"),
                                    data.getInt("Member_ID"),
                                    data.getString("Title"),
                                    data.getString("Expense_Description"),
                                    data.getInt("Price"),
                                    data.getString("Expense_DateTime"),
                                    data.getInt("c_id"),
                                    data.getString("c_name"),
                                    data.getString("c_phone"),
                                    data.getString("c_cnic")
                            ));
                            if (!totalMembers.contains(data.getInt("Member_ID"))) {
                                totalMembers.add(data.getInt("Member_ID"));
                            }
                        }
                        adapter = new ExpenseAdapter(expenseModelList,
                                CompleteCustomTrip.this);
                        listView.setAdapter(adapter);
                        CalculatePrice();

                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CompleteCustomTrip.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void CalculatePrice() {
        for (int i = 0; i < expenseModelList.size(); i++) {
            totalExpense = totalExpense + expenseModelList.get(i).getPrice();
        }
        tv.setText("Rs. " + totalExpense);
    }
}