package com.demo.e_commerce.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demo.e_commerce.Model.ElectronicsModel;
import com.demo.e_commerce.Model.OfferModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.viewHolder>{
    ArrayList<OfferModel> list;
    Context context;

    public OfferAdapter(Context context, ArrayList<OfferModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.offer_layout,parent,false);
        return new OfferAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        OfferModel model = list.get(position);
        Glide.with(context).load(model.getPic()).placeholder(R.drawable.category_img).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.offerImg);

        }
    }
}
