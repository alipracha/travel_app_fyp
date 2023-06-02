package com.example.triparrangersfyp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.triparrangersfyp.Admin.Detailed_TA;
import com.example.triparrangersfyp.Feedback;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.ViewFeedback;
import com.example.triparrangersfyp.model.FeedbackModel;
import com.example.triparrangersfyp.model.TravelAgency;

import java.util.List;

public class FeedbackAdapter extends BaseAdapter {

    List<FeedbackModel> feedbackList;
    Context context;
    LayoutInflater inflater;

    public FeedbackAdapter(List<FeedbackModel> feedbackList, Context context) {
        this.feedbackList = feedbackList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return feedbackList.size();
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
        convertView = inflater.inflate(R.layout.feedback_listview, parent, false);
        TextView tv_show_star = convertView.findViewById(R.id.Feedback_Star);
        tv_show_star.setText(String.valueOf(feedbackList.get(position).getFeedback_star()) );
        TextView tv_show_message = convertView.findViewById(R.id.Feedback_Message);
        tv_show_message.setText(feedbackList.get(position).getFeedback_msg());
        TextView tv_show_datetime = convertView.findViewById(R.id.Datetime);
        tv_show_datetime.setText(feedbackList.get(position).getFeedback_datetime());
        return convertView;
    }
}
