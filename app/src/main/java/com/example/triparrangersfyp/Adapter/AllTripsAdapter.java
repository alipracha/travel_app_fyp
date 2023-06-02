package com.example.triparrangersfyp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.triparrangersfyp.Admin.Detailed_AllTrips;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.util.Endpoint;

import java.util.List;

public class AllTripsAdapter extends BaseAdapter {

    List<Trips> tripList;
    Context context;
    LayoutInflater inflater;

    public AllTripsAdapter(List<Trips> tripList, Context context) {
        this.tripList = tripList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tripList.size();
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
        TextView tv_show_name = convertView.findViewById(R.id.C_txtName);
        tv_show_name.setText(tripList.get(position).getTrip_title());
        TextView tv_show_Des = convertView.findViewById(R.id.C_Email);
        tv_show_Des.setText(tripList.get(position).getTrip_description());
        ImageView image= convertView.findViewById(R.id.C_Image_LV);


        Glide.with(context).load(Endpoint.IMAGE_URL + tripList.get(position).getTrip_image())
                .into(image);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detailed_AllTrips.class);
                intent.putExtra("trip_id", tripList.get(position).getTrip_id());
                intent.putExtra("trip_title", tripList.get(position).getTrip_title());
                intent.putExtra("trip_description", tripList.get(position).getTrip_description());
                intent.putExtra("trip_depDate", tripList.get(position).getTrip_depDate());
                intent.putExtra("trip_arrDate", tripList.get(position).getTrip_arrDate());
                intent.putExtra("trip_depTime", tripList.get(position).getTrip_depTime());
                intent.putExtra("trip_arrTime", tripList.get(position).getTrip_arrTime());
                intent.putExtra("trip_pickup", tripList.get(position).getTrip_pickup());
                intent.putExtra("trip_dropoff", tripList.get(position).getTrip_dropoff());
                intent.putExtra("trip_numSeats", tripList.get(position).getTrip_numSeats());
                intent.putExtra("trip_payment", tripList.get(position).getTrip_payment());
                intent.putExtra("trip_status", tripList.get(position).getTrip_status());
                intent.putExtra("trip_image", tripList.get(position).getTrip_image());
                intent.putExtra("travel_agency_id", tripList.get(position).getTravel_agency_id());
                intent.putExtra("ta_name", tripList.get(position).getTa_name());
                intent.putExtra("ta_phone", tripList.get(position).getTa_phone());


                context.startActivity(intent);
            }
        });

        return convertView;
    }
}

