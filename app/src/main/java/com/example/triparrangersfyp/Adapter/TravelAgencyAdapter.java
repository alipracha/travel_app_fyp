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
import com.example.triparrangersfyp.Admin.Detailed_TA;
import com.example.triparrangersfyp.R;
import com.example.triparrangersfyp.model.TravelAgency;
import com.example.triparrangersfyp.util.Endpoint;

import java.util.List;

public class TravelAgencyAdapter extends BaseAdapter  {

        List<TravelAgency> taList;
        Context context;
        LayoutInflater inflater;

        public TravelAgencyAdapter(List<TravelAgency> taList, Context context) {
            this.taList = taList;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return taList.size();
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
            convertView = inflater.inflate(R.layout.list_view, parent, false);
            TextView tv_show_name = convertView.findViewById(R.id.txtName);
            tv_show_name.setText(taList.get(position).getTa_name());
            TextView tv_show_email = convertView.findViewById(R.id.txtDes);

            ImageView Image_LV=convertView.findViewById(R.id.Image_LV);

            Glide.with(context).load(Endpoint.IMAGE_URL + taList.get(position).getTa_image())
                    .into(Image_LV);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Detailed_TA.class);
                    intent.putExtra("ta_id",taList.get(position).getTa_id());
                    intent.putExtra("ta_name", taList.get(position).getTa_name());
                    intent.putExtra("ta_email", taList.get(position).getTa_email());
                    intent.putExtra("ta_phone", taList.get(position).getTa_phone());
                    intent.putExtra("ta_cnic", taList.get(position).getTa_cnic());
                    intent.putExtra("ta_status",taList.get(position).getTa_status());
                    intent.putExtra("ta_image",taList.get(position).getTa_image());

                    context.startActivity(intent);
                }
            });
            return convertView;
        }
    }
