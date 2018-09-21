package com.droidman.exhangeapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.droidman.exhangeapp.R;
import com.droidman.exhangeapp.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter <ItemAdapter.ViewHolder>{
    private List<Item>itemList;
    private Context context;
    private boolean isGPSEnabled;

    public ItemAdapter(Context context, List<Item> itemList, boolean isGPSEnabled) {
        this.itemList = itemList;
        this.context = context;
        this.isGPSEnabled = isGPSEnabled;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //if(isGPSEnabled)
            viewHolder.distance.setText(String.valueOf(itemList.get(i).getDistance()));
        //else {
         //   viewHolder.distance.setText("GPS is not allowed");
        //    viewHolder.distance.setTextSize(15);
       // }
        Picasso.with(context).load(itemList.get(i).getImg()).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView distance;
        private ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            distance = itemView.findViewById(R.id.distanceTxt);
            img = itemView.findViewById(R.id.itemImg);
        }
    }
}
