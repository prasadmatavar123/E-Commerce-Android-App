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
import com.demo.e_commerce.Model.LatestProModel;
import com.demo.e_commerce.Model.MyCart;

import com.demo.e_commerce.R;

import java.util.ArrayList;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.viewHolder>{
    ArrayList<MyCart> list;
    Context context;

    public MyCartAdapter(Context context, ArrayList<MyCart> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_cart_layout,parent,false);
        return new MyCartAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        MyCart model = list.get(position);
        holder.textView1.setText(model.getTitle());
//        holder.textView2.setText(model.getProductType());
        holder.textView3.setText(model.getCost());
        Glide.with(context).load(model.getImage()).placeholder(R.drawable.category_img).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView1 = itemView.findViewById(R.id.title);
//            textView2 = itemView.findViewById(R.id.ProductType);
            textView3 = itemView.findViewById(R.id.cost);
        }
    }
}
