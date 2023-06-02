package com.example.triparrangersfyp.Adapter;

import static com.example.triparrangersfyp.ChattingActivity.CHATID;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.triparrangersfyp.ChattingActivity;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.TravelAgency.Detailed_Trip;
import com.example.triparrangersfyp.model.Chat;
import com.example.triparrangersfyp.service.OnMessageClick;
import com.example.triparrangersfyp.util.Endpoint;

import java.util.List;

import retrofit2.http.Field;

public class ChatAdapter extends BaseAdapter {
    List<Chat> MessagesList;
    Context context;
    OnMessageClick onMessageClick;
    LayoutInflater inflater;


    public ChatAdapter(List<Chat> MessagesList, Context context, OnMessageClick onMessageClick) {
        this.MessagesList = MessagesList;
        this.context = context;
        this.onMessageClick = onMessageClick;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return MessagesList.size();
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
        convertView = inflater.inflate(R.layout.chat_listview, parent, false);
        TextView tv_show_cus_name = convertView.findViewById(R.id.msg);
        TextView recievermsg = convertView.findViewById(R.id.recievermsg);
        LinearLayout recieverLL, senderLL;
        recieverLL = convertView.findViewById(R.id.recieverLL);
        senderLL = convertView.findViewById(R.id.senderLL);
        ImageView imgChat = convertView.findViewById(R.id.ChatImg);
        ImageView recieverChatImg = convertView.findViewById(R.id.recieverChatImg);

        if (MessagesList.get(position).getSender_type().equals("c")) {
            senderLL.setVisibility(View.VISIBLE);
            recieverLL.setVisibility(View.GONE);
            tv_show_cus_name.setText(MessagesList.get(position).getChat_Message());

            if (MessagesList.get(position).getImagee() != null) {
                Glide.with(context).load(Endpoint.IMAGE_URL + MessagesList.get(position).getImagee())
                        .into(imgChat);
            } else {
                imgChat.setVisibility(View.GONE);
                tv_show_cus_name.setVisibility(View.VISIBLE);
            }
        } else if (MessagesList.get(position).getSender_type().equals("t")) {
            senderLL.setVisibility(View.GONE);
            recieverLL.setVisibility(View.VISIBLE);
            recievermsg.setText(MessagesList.get(position).getChat_Message());

            if (MessagesList.get(position).getImagee() != null) {
                Glide.with(context).load(Endpoint.IMAGE_URL + MessagesList.get(position).getImagee())
                        .into(recieverChatImg);
            } else {
                imgChat.setVisibility(View.GONE);
                recievermsg.setVisibility(View.VISIBLE);
            }
        }


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMessageClick.OnItemCLick(position);
            }
        });

        return convertView;
    }
}