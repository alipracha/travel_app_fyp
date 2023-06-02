package com.example.triparrangersfyp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.triparrangersfyp.Tourist.DetailedNotification;
import com.example.triparrangersfyp.R;

import com.example.triparrangersfyp.model.NotificationModel;
import java.util.List;

public class NotificationAdapter extends BaseAdapter {

    private Context mContext;
    List<NotificationModel> notificationList;
    LayoutInflater inflater;

    public NotificationAdapter (Context mContext, List<NotificationModel> notificationList) {
        this.mContext = mContext;
        this.notificationList = notificationList;
        inflater = LayoutInflater.from(mContext);
    }


    public int getCount() {
        return notificationList.size();
    }


    public Object getItem(int position) {
        return position;
    }


    public long getItemId(int position) {
        return 0;
    }

    @NonNull

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(R.layout.notification, parent, false);

        TextView txtName = convertView.findViewById(R.id.C_txtName);
        TextView Expense = convertView.findViewById(R.id.C_Expense);
        txtName.setText(notificationList.get(position).getNotic_title());
        Expense.setText(" Rs. " +notificationList.get(position).getPrice());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailedNotification.class);
                intent.putExtra("notic_title",notificationList.get(position).getNotic_title());
                intent.putExtra("typee", notificationList.get(position).getTypee());
                intent.putExtra("typeid", notificationList.get(position).getTypeid());
                intent.putExtra("notic_for", notificationList.get(position).getNotic_for());
                intent.putExtra("notic_for_id", notificationList.get(position).getNotic_for_id());
                intent.putExtra("price", notificationList.get(position).getPrice());
                intent.putExtra("dateTime", notificationList.get(position).getDateTime());

                mContext.startActivity(intent);
            }

        });
        return convertView;
    }
}






