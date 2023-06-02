package com.example.triparrangersfyp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Tourist.Detailed_CustomTrip;
import com.example.triparrangersfyp.model.CustomTrip;
import java.util.List;

public class CustomTripAdapter extends BaseAdapter {


    private Context mContext;
    List<CustomTrip> tripsList;
    LayoutInflater inflater;

    public CustomTripAdapter(Context mContext, List<CustomTrip> tripsList) {
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
        convertView = inflater.inflate(R.layout.trips_listview, parent, false);
        ImageView imageView = convertView.findViewById(R.id.Image_LView);
        TextView txtName = convertView.findViewById(R.id.txtNamee);
        TextView txtDes = convertView.findViewById(R.id.txtDess);
        txtName.setText(tripsList.get(position).getCtrip_title());
        txtDes.setText(tripsList.get(position).getCtrip_description());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Detailed_CustomTrip.class);
                intent.putExtra("ctrip_id",tripsList.get(position).getCtrip_id());
                intent.putExtra("ctrip_title", tripsList.get(position).getCtrip_title());
                intent.putExtra("ctrip_description", tripsList.get(position).getCtrip_description());
                intent.putExtra("ctrip_depDate", tripsList.get(position).getCtrip_depDate());
                intent.putExtra("ctrip_arrDate", tripsList.get(position).getCtrip_arrDate());
                intent.putExtra("ctrip_depTime", tripsList.get(position).getCtrip_depTime());
                intent.putExtra("ctrip_arrTime", tripsList.get(position).getCtrip_arrTime());
                intent.putExtra("ctrip_pickup", tripsList.get(position).getCtrip_pickup());
                intent.putExtra("ctrip_dropoff", tripsList.get(position).getCtrip_dropoff());
                intent.putExtra("STATUS", tripsList.get(position).getCtrip_status());

                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
}





