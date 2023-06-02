package com.example.triparrangersfyp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.model.Report;

import java.util.List;

public class TravelAgencyReportAdapter extends BaseAdapter {

    List<Report> reportList;
    Context context;
    LayoutInflater inflater;

    public TravelAgencyReportAdapter(List<Report> reportList, Context context) {
        this.reportList = reportList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return reportList.size();
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
        convertView = inflater.inflate(R.layout.item_report, parent, false);
        TextView name = convertView.findViewById(R.id.name);
        TextView total_trips = convertView.findViewById(R.id.total_trips);

        name.setText(reportList.get(position).getTravelAgenecyName());
        total_trips.setText(String.valueOf(reportList.get(position).getTotal()));

        return convertView;
    }
}
