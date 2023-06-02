package com.example.triparrangersfyp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.triparrangersfyp.Admin.Detailed_User;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.model.Customer;

import java.util.List;


public class CustomerAdapter extends BaseAdapter {

    List<Customer> userList;
    Context context;
    LayoutInflater inflater;
    TextView user_name,user_email;

    public CustomerAdapter(List<Customer> userList, Context context) {
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
        convertView = inflater.inflate(R.layout.customer_list_view, parent, false);
        TextView tv_show_cus_name = convertView.findViewById(R.id.C_txtName);
        tv_show_cus_name.setText(userList.get(position).getC_name());
        TextView tv_show_cus_email = convertView.findViewById(R.id.C_Email);
        tv_show_cus_email.setText(userList.get(position).getC_email());

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
}