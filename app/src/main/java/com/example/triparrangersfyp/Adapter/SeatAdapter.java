package com.example.triparrangersfyp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import androidx.core.content.ContextCompat;

import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Tourist.ShowSeatsActivity;
import com.example.triparrangersfyp.model.Seat;

import java.util.List;

public class SeatAdapter extends BaseAdapter {

    List<Seat> seatlist;
    Context context;
    LayoutInflater inflater;
    boolean clicked=false;


    public SeatAdapter(List<Seat> seatlist, Context context) {
        this.seatlist = seatlist;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return seatlist.size();
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
        convertView = inflater.inflate(R.layout.item_seats, parent, false);
        Button btn_item_seat_num = convertView.findViewById(R.id.btn_item_seat_num);
        btn_item_seat_num.setText(String.valueOf(seatlist.get(position).getSeatNo()));
        if(seatlist.get(position).isAvaialble()){
            btn_item_seat_num.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_700));
        }
        btn_item_seat_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!seatlist.get(position).isAvaialble()){
                    ShowSeatsActivity.selectedseatlist.add(seatlist.get(position).getSeatNo());
                    ShowSeatsActivity.selectedseat = String.valueOf(seatlist.get(position));
                    btn_item_seat_num.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_200));

                }
            }
        });
        return convertView;
    }
}
