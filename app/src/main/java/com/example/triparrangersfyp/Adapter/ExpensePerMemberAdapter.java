package com.example.triparrangersfyp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.model.ExpenseModel;

import java.util.List;

public class ExpensePerMemberAdapter extends BaseAdapter {
    List<ExpenseModel> expenseModel;
    Context context;
    LayoutInflater inflater;

    public ExpensePerMemberAdapter(List<ExpenseModel> expenseModel, Context context) {
        this.expenseModel = expenseModel;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return expenseModel.size();
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
        TextView ExpenseTitle= convertView.findViewById(R.id.txtNamee);
        ExpenseTitle.setText(String.valueOf(expenseModel.get(position).getTitle()) );
        TextView Price= convertView.findViewById(R.id.txtDess);
        Price.setText(String.valueOf(expenseModel.get(position).getPrice()));


        return convertView;
    }
}
