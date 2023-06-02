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
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.TravelAgency.ViewBookingRequests_TA;
import com.example.triparrangersfyp.model.RequestBooking;
import com.example.triparrangersfyp.util.Endpoint;

import java.util.List;

public class TravelAgencyBookingAdapter extends BaseAdapter {
    List<RequestBooking> requestList;
    Context context;
    LayoutInflater inflater;

    public TravelAgencyBookingAdapter(List<RequestBooking> requestList, Context context) {
        this.requestList = requestList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        {
            return requestList.size();
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
        TextView tv_show_name = convertView.findViewById(R.id.C_txtName);
        tv_show_name.setText(requestList.get(position).getTrip_title());
        TextView tv_show_CName = convertView.findViewById(R.id.C_Email);
        ImageView image = convertView.findViewById(R.id.C_Image_LV);
        tv_show_CName.setText(requestList.get(position).getC_name());
        Glide.with(context).load(Endpoint.IMAGE_URL + requestList.get(position).getTrip_image())
                .into(image);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewBookingRequests_TA.class);
                intent.putExtra("c_id", requestList.get(position).getC_id());
                intent.putExtra("c_name", requestList.get(position).getC_name());
                intent.putExtra("c_phone", requestList.get(position).getC_phone());
                intent.putExtra("c_cnic ", requestList.get(position).getC_cnic());
                intent.putExtra("trip_id", requestList.get(position).getTrip_id());
                intent.putExtra("trip_title", requestList.get(position).getTrip_title());
                intent.putExtra("trip_description", requestList.get(position).getTrip_description());
                intent.putExtra("trip_depDate", requestList.get(position).getTrip_depDate());
                intent.putExtra("trip_arrDate", requestList.get(position).getTrip_arrDate());
                intent.putExtra("trip_depTime", requestList.get(position).getTrip_depTime());
                intent.putExtra("trip_arrTime", requestList.get(position).getTrip_arrTime());
                intent.putExtra("trip_pickup", requestList.get(position).getTrip_pickup());
                intent.putExtra("trip_dropoff", requestList.get(position).getTrip_dropoff());
                intent.putExtra("trip_image", requestList.get(position).getTrip_image());
                intent.putExtra("b_id", requestList.get(position).getB_id());
                intent.putExtra("b_price", requestList.get(position).getB_price());
                intent.putExtra("b_seats", requestList.get(position).getB_seats());
                intent.putExtra("b_status", requestList.get(position).getB_status());
                intent.putExtra("b_datetime", requestList.get(position).getB_datetime());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}