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
import com.demo.e_commerce.Model.AllBrandModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;

public class AllBrandAdapter extends RecyclerView.Adapter<AllBrandAdapter.viewHolder>{
    ArrayList<AllBrandModel> list;
    Context context;

    public AllBrandAdapter(Context context, ArrayList<AllBrandModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_brand_layout,parent,false);
        return new AllBrandAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        AllBrandModel model = list.get(position);
        holder.textView.setText(model.getText());
        Glide.with(context).load(model.getPic()).placeholder(R.drawable.category_img).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.allbrandImg);
            textView = itemView.findViewById(R.id.allbrandText);
        }
    }
}
