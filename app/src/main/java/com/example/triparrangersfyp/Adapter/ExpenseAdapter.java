package com.example.triparrangersfyp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.Tourist.ViewTrips_User;
import com.example.triparrangersfyp.model.ExpenseModel;


import java.util.List;

public class ExpenseAdapter extends BaseAdapter {
    List<ExpenseModel> expenseModel;
    Context context;
    LayoutInflater inflater;

    public ExpenseAdapter(List<ExpenseModel> expenseModel, Context context) {
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

        convertView = inflater.inflate(R.layout.expense_listview, parent, false);
        TextView ExpenseTitle= convertView.findViewById(R.id.E_Title);
        ExpenseTitle.setText(String.valueOf(expenseModel.get(position).getTitle()) );
        TextView Des= convertView.findViewById(R.id.E_Des);
        Des.setText(String.valueOf(expenseModel.get(position).getExpense_Description()));
        TextView Price= convertView.findViewById(R.id. E_Price);
        Price.setText(String.valueOf(expenseModel.get(position).getPrice()));
        TextView Name= convertView.findViewById(R.id.E_Cust);
        Name.setText(String.valueOf(expenseModel.get(position).getC_name()));
        TextView Phone= convertView.findViewById(R.id.E_Phone);
        Phone.setText(String.valueOf(expenseModel.get(position).getC_phone()));

        return convertView;
    }
}
