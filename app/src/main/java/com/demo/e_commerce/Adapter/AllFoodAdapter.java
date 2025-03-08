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
import com.demo.e_commerce.Model.AllFoodModel;
import com.demo.e_commerce.Model.AllLatestModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;

public class AllFoodAdapter extends RecyclerView.Adapter<AllFoodAdapter.viewHolder>{
    ArrayList<AllFoodModel> list;
    Context context;
    AllFoodAdapter.ItemClickListener itemClickListener;

    public AllFoodAdapter(Context context, ArrayList<AllFoodModel> list ,AllFoodAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.list = list;
        this.itemClickListener= itemClickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_food_layout,parent,false);
        return new AllFoodAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        AllFoodModel model = list.get(position);
        holder.textView1.setText(model.getText1());
        holder.textView2.setText(model.getText2());
        Glide.with(context).load(model.getPic()).placeholder(R.drawable.category_img).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick3(model.getText1(),model.getText2(), model.getPic());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1,textView2;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.allfoodImg);
            textView1 = itemView.findViewById(R.id.allfoodText1);
            textView2 = itemView.findViewById(R.id.allfoodText2);
        }
    }
    public interface ItemClickListener {
        void onItemClick3(String name1,String name2,String img3);
    }
}
