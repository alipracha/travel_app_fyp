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
import com.example.triparrangersfyp.Tourist.ViewTrips_User;
import com.example.triparrangersfyp.model.FavouriteTrips;
import com.example.triparrangersfyp.model.FeedbackModel;
import com.example.triparrangersfyp.util.Endpoint;

import java.util.List;

public class FavouriteTripsAdapter extends BaseAdapter {

        List<FavouriteTrips> favouriteTripsList;
        Context context;
        LayoutInflater inflater;

            public FavouriteTripsAdapter( List<FavouriteTrips> favouriteTripsList, Context context) {
            this.favouriteTripsList = favouriteTripsList;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return favouriteTripsList.size();
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
            convertView = inflater.inflate(R.layout.trips_listview, parent, false);
            TextView TripName = convertView.findViewById(R.id.txtNamee);
            TripName.setText(String.valueOf(favouriteTripsList.get(position).getTrip_title()) );
            TextView TripDescription= convertView.findViewById(R.id.txtDess);
            TripDescription.setText(favouriteTripsList.get(position).getTrip_description());
            ImageView img= convertView.findViewById(R.id.Image_LView);

            Glide.with(context).load(Endpoint.IMAGE_URL + favouriteTripsList.get(position).getTrip_image())
                    .into(img);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ViewTrips_User.class);
                    intent.putExtra("trip_id", favouriteTripsList.get(position).getTrip_id());
                    intent.putExtra("trip_title", favouriteTripsList.get(position).getTrip_title());
                    intent.putExtra("trip_description", favouriteTripsList.get(position).getTrip_description());
                    intent.putExtra("trip_depDate", favouriteTripsList.get(position).getTrip_depDate());
                    intent.putExtra("trip_arrDate", favouriteTripsList.get(position).getTrip_arrDate());
                    intent.putExtra("trip_depTime", favouriteTripsList.get(position).getTrip_depTime());
                    intent.putExtra("trip_arrTime", favouriteTripsList.get(position).getTrip_arrTime());
                    intent.putExtra("trip_pickup", favouriteTripsList.get(position).getTrip_pickup());
                    intent.putExtra("trip_dropoff", favouriteTripsList.get(position).getTrip_dropoff());
                    intent.putExtra("trip_numSeats", favouriteTripsList.get(position).getTrip_numSeats());
                    intent.putExtra("trip_payment", favouriteTripsList.get(position).getTrip_payment());
                    intent.putExtra("trip_image", favouriteTripsList.get(position).getTrip_image());
                    intent.putExtra("travel_agency_id", favouriteTripsList.get(position).getTravel_agency_id());
                    intent.putExtra("check", 1);

                    context.startActivity(intent);
                }

            });

            return convertView;
        }
}


