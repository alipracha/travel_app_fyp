package com.example.triparrangersfyp.Tourist;

import static com.example.triparrangersfyp.util.Constant.C_TRIP_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.triparrangersfyp.Adapter.CustomTripMembersAdapter;
import com.example.triparrangersfyp.AddMembers;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.CustomTripMember;
import com.example.triparrangersfyp.service.GetCustomTripMembersService;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCustomTripMembers extends AppCompatActivity {

    Button btn;
    ListView listview;

    List<CustomTripMember> memberList = new ArrayList<>();
    ProgressDialog progressDialog;
    CustomTripMembersAdapter adapter;
    CustomTripMember customTripmember;

    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_custom_trip_members);

        btn=findViewById(R.id.GroupMember);
        listview=findViewById(R.id.CMembersListView);

        tinyDB = new TinyDB(this);
        GetCustomTripsMember();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewCustomTripMembers.this, AddMembers.class));
            }
        });
    }
    private void GetCustomTripsMember() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        customTripmember = new CustomTripMember();
        GetCustomTripMembersService service = RetrofitClient.getClient().create(GetCustomTripMembersService.class);
        Call<JsonObject> call = service.getCtripMembers(C_TRIP_ID);
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

                            memberList.add(new CustomTripMember
                                    (
                                            data.getString("c_name"),
                                            data.getString("c_phone")

                                    ));


                        }
                        adapter = new CustomTripMembersAdapter(memberList,
                                ViewCustomTripMembers.this);
                        listview.setAdapter(adapter);

                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ViewCustomTripMembers.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}