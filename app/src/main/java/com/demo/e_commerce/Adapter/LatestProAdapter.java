package com.demo.e_commerce.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demo.e_commerce.Fragments.ProductDetailsFragment;
import com.demo.e_commerce.Model.LatestProModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;

public class LatestProAdapter extends RecyclerView.Adapter<LatestProAdapter.viewHolder>{
    ArrayList<LatestProModel> list;
    Context context;
    LatestProAdapter.ItemClickListener itemClickListener;

    public LatestProAdapter(Context context, ArrayList<LatestProModel> list,ItemClickListener itemClickListener) {
        this.context = context;
        this.list = list;
        this.itemClickListener= itemClickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.latest_pro_layout,parent,false);
        return new LatestProAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        LatestProModel model = list.get(position);
        holder.textView1.setText(model.getText1());
        holder.textView2.setText("₹"+model.getText2());

        holder.description.setText(model.getDescription());
        Glide.with(context).load(model.getPic()).placeholder(R.drawable.category_img).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               itemClickListener.onItemClick4(model.getText1(),model.getText2(), model.getPic(),model.getDescription());

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1,textView2,description;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.latestImg);
            textView1 = itemView.findViewById(R.id.latestText1);
            textView2 = itemView.findViewById(R.id.latestText2);
            description = itemView.findViewById(R.id.description);
        }
    }
    public interface ItemClickListener {
        void onItemClick4(String name1,String name2 ,String img, String description);
    }
}
