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

import com.bumptech.glide.Glide;
import com.example.triparrangersfyp.TravelAgency.Detailed_Trip;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.model.Trips;
import com.example.triparrangersfyp.util.Endpoint;

import java.util.List;

public class TripAdapter extends BaseAdapter {

    private final Context mContext;
    List<Trips> tripsList;
    LayoutInflater inflater;

    public TripAdapter(Context mContext, List<Trips> tripsList) {
        this.mContext = mContext;
        this.tripsList = tripsList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return tripsList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(R.layout.list_view, parent, false);
        ImageView imageView = convertView.findViewById(R.id.Image_LV);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtDes = convertView.findViewById(R.id.txtDes);
        txtName.setText(tripsList.get(position).getTrip_title());
        txtDes.setText(tripsList.get(position).getTrip_description());

        ImageView image= convertView.findViewById(R.id.Image_LV);


        Glide.with(mContext).load(Endpoint.IMAGE_URL + tripsList.get(position).getTrip_image())
                .into(image);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(mContext, Detailed_Trip.class);
                    intent.putExtra("trip_id",tripsList.get(position).getTrip_id());
                    intent.putExtra("trip_title", tripsList.get(position).getTrip_title());
                    intent.putExtra("trip_description", tripsList.get(position).getTrip_description());
                    intent.putExtra("trip_depDate", tripsList.get(position).getTrip_depDate());
                    intent.putExtra("trip_arrDate", tripsList.get(position).getTrip_arrDate());
                    intent.putExtra("trip_depTime", tripsList.get(position).getTrip_depTime());
                    intent.putExtra("trip_arrTime", tripsList.get(position).getTrip_arrTime());
                    intent.putExtra("trip_pickup", tripsList.get(position).getTrip_pickup());
                    intent.putExtra("trip_dropoff", tripsList.get(position).getTrip_dropoff());
                    intent.putExtra("trip_numSeats", tripsList.get(position).getTrip_numSeats());
                    intent.putExtra("trip_payment",tripsList.get(position).getTrip_payment());
                intent.putExtra("trip_image",tripsList.get(position).getTrip_image());
                    intent.putExtra("trip_status", tripsList.get(position).getTrip_status());
                    mContext.startActivity(intent);
            }
        });
        return convertView;
    }
}





