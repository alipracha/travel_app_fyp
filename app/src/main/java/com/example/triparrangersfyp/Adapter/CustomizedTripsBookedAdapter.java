package com.example.triparrangersfyp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Tourist.ViewCustomTripDetailss;
import com.example.triparrangersfyp.model.CustomTrip;

import java.util.List;

public class CustomizedTripsBookedAdapter extends BaseAdapter {

    List<CustomTrip> customList;
    Context context;
    LayoutInflater inflater;

    public CustomizedTripsBookedAdapter(List<CustomTrip> requestList, Context context) {
        this.customList = requestList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public int getCount() {
        {
            return customList.size();
        }
    }

    public Object getItem(int i) {
        return i;
    }

    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.trips_listview, parent, false);
        TextView tv_show_name = convertView.findViewById(R.id.txtNamee);
        tv_show_name.setText(customList.get(position).getCtrip_title());
        TextView des = convertView.findViewById(R.id.txtDess);
        des.setText(customList.get(position).getCtrip_description());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewCustomTripDetailss.class);

                intent.putExtra("ctrip_id", customList.get(position).getCtrip_id());
                intent.putExtra("REQ_ID", customList.get(position).getCTripRequest_ID());
                intent.putExtra("ctrip_title", customList.get(position).getCtrip_title());
                intent.putExtra("ctrip_description", customList.get(position).getCtrip_description());
                intent.putExtra("ctrip_depDate", customList.get(position).getCtrip_depDate());
                intent.putExtra("ctrip_arrDate", customList.get(position).getCtrip_arrDate());
                intent.putExtra("ctrip_depTime", customList.get(position).getCtrip_depTime());
                intent.putExtra("ctrip_arrTime", customList.get(position).getCtrip_arrTime());
                intent.putExtra("ctrip_pickup", customList.get(position).getCtrip_pickup());
                intent.putExtra("ctrip_dropoff", customList.get(position).getCtrip_dropoff());
                intent.putExtra("STATUS", customList.get(position).getStatuss());

                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
