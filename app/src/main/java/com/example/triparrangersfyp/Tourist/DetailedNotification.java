package com.example.triparrangersfyp.Tourist;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.triparrangersfyp.Adapter.ExpensePerMemberAdapter;
import com.example.triparrangersfyp.BuildConfig;
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
import com.example.triparrangersfyp.model.ExpenseModel;
import com.example.triparrangersfyp.service.GetExpenseOfMemberService;
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

public class DetailedNotification extends AppCompatActivity {

    TextView  totalToBePaid, paid, payable;
    int getTripID, cust;
    String price;

    List<ExpenseModel> expenseModelList = new ArrayList<>();
    ListView LV;
    int totalExpense = 0;
    int payableAmount = 0;
    int totalPrice = 0;
    ExpensePerMemberAdapter adapter;
    ProgressDialog progressDialog;
    DrawerLayout drawer;
    NavigationView nav1;
    ImageView img;
    Toolbar toolbar;
    TinyDB tinyDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_notification);

        paid = findViewById(R.id.U_Paid);
        payable = findViewById(R.id.U_Payable);
        totalToBePaid = findViewById(R.id.U_TotalPaid);
        nav1 = findViewById(R.id.nav_menuU);
        drawer = findViewById(R.id.TA_Drawer);
        img = findViewById(R.id.TANavImage);
        toolbar = findViewById(R.id.TA_Toolbar);
        tinyDb = new TinyDB(this);
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
                } else if (item.getItemId() == R.id.TAMenu_GenerateTrip) {

                    startActivity(new Intent(getApplicationContext(), AddTrip.class));
                } else if (item.getItemId() == R.id.TAMenu_Logout) {
                    new AlertDialog.Builder(DetailedNotification.this).setIcon(R.drawable.ic_baseline_warning_24)
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
                        Toast.makeText(DetailedNotification.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                }
                return true;
            }

        });
        LV = findViewById(R.id.TotalPriceLV);

        tinyDb = new TinyDB(this);
        getTripID = getIntent().getIntExtra("typeid", 0);
        price = getIntent().getStringExtra("price");
        cust = tinyDb.getInt("CUSTOMER_ID");
        getIndividualExpense(getTripID, cust);
        totalToBePaid.setText(price);
        totalPrice = Integer.parseInt(price);


    }

    private void getIndividualExpense(int TripID, int Member_ID) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        expenseModelList.clear();
        progressDialog.show();

        GetExpenseOfMemberService service = RetrofitClient.getClient().create(GetExpenseOfMemberService.class);
        Call<JsonObject> call = service.getIndividualExpense(TripID, Member_ID);
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
                                    data.getString("Title"),
                                    data.getString("Expense_Description"),
                                    data.getString("Expense_DateTime"),
                                    data.getInt("Price")));
                        }
                        CalculatePrice();
                        adapter = new ExpensePerMemberAdapter(expenseModelList,
                                DetailedNotification.this);
                        LV.setAdapter(adapter);

                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailedNotification.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void CalculatePrice() {
        for (int i = 0; i < expenseModelList.size(); i++) {
            totalExpense = totalExpense + expenseModelList.get(i).getPrice();
        }
        paid.setText("Rs. " + totalExpense);
        payableAmount = totalPrice - totalExpense;
        payable.setText("Rs. " + payableAmount);
    }
}
