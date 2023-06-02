package com.example.triparrangersfyp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.model.CustomTripMember;

import java.util.List;

public class CustomTripMembersAdapter extends BaseAdapter {
    List<CustomTripMember> memberList;
    Context context;
    LayoutInflater inflater;

    public CustomTripMembersAdapter(List<CustomTripMember> requestList, Context context) {
        this.memberList = requestList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        {
            return memberList.size();
        }
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.customer_list_view, parent, false);
        TextView name = convertView.findViewById(R.id.C_txtName);
        name.setText(memberList.get(position).getCustomer_Name());
        TextView phone = convertView.findViewById(R.id.C_Email);
        phone.setText(memberList.get(position).getCustomer_Phone());

        return convertView;
    }
}

