package com.example.triparrangersfyp.Adapter;

import static com.example.triparrangersfyp.util.Constant.C_TRIP_ID;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.triparrangersfyp.Admin.Detailed_User;
import com.example.triparrangersfyp.Feedback;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.model.CustomTripMember;
import com.example.triparrangersfyp.model.Customer;
import com.example.triparrangersfyp.model.FeedbackModel;
import com.example.triparrangersfyp.service.AddFeedbackService;
import com.example.triparrangersfyp.service.InsertMemberInGroupService;
import com.example.triparrangersfyp.util.TinyDB;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApprovedUsersAdapter extends BaseAdapter {
    List<Customer> userList;
    Context context;
    LayoutInflater inflater;
    TinyDB tinyDb;
    CustomTripMember customTripMember;
    ProgressDialog progressDialog;


    public ApprovedUsersAdapter(List<Customer> userList, Context context) {
        this.userList = userList;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.custom_member_listitem, parent, false);
        TextView tv_show_cus_name = convertView.findViewById(R.id.MemberName);
        tv_show_cus_name.setText(userList.get(position).getC_name());

        Button btn = convertView.findViewById(R.id.CMemberAdd);
        tinyDb = new TinyDB(context.getApplicationContext());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addMemberinGroup(C_TRIP_ID,tinyDb.getInt("CUSTOMER_ID"),
                        userList.get(position).getC_id(),"P");

            }
        });


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detailed_User.class);
                intent.putExtra("c_id",userList.get(position).getC_id());
                intent.putExtra("c_name", userList.get(position).getC_name());
                intent.putExtra("c_email", userList.get(position).getC_email());
                intent.putExtra("c_phone", userList.get(position).getC_phone());
                intent.putExtra("c_cnic", userList.get(position).getC_cnic());
                intent.putExtra("c_status",userList.get(position).getC_status());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    public void addMemberinGroup(int Trip_ID , int GroupAdmin_ID, int Member_ID,String Statuss)
    {
        customTripMember=new CustomTripMember();
        progressDialog = new ProgressDialog(context.getApplicationContext());
        progressDialog.setMessage("Please Wait...");
        //progressDialog.show();

        InsertMemberInGroupService service= RetrofitClient.getClient().create(InsertMemberInGroupService.class);
        Call<CustomTripMember> call = service.insertMemberInGroup(Trip_ID,GroupAdmin_ID,Member_ID,Statuss);

        call.enqueue(new Callback<CustomTripMember>()
        {
            @Override
            public void onResponse(Call<CustomTripMember> call, Response<CustomTripMember> response)
            {
                if (response.isSuccessful())
                {

                    customTripMember = response.body();
                    if (!customTripMember.isError())
                    {
                        progressDialog.dismiss();
                        Toast.makeText(context.getApplicationContext(),
                                customTripMember.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(context.getApplicationContext(),
                                customTripMember.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<CustomTripMember> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context.getApplicationContext(),
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
