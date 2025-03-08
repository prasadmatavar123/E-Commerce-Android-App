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
import com.demo.e_commerce.Model.CategoryModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder>{
    ArrayList<CategoryModel> list;
    Context context;
    CategoryAdapter.ItemClickListener itemClickListener;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> list ,CategoryAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.list = list;
        this.itemClickListener= itemClickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_layout,parent,false);
        return new CategoryAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CategoryModel model = list.get(position);
        holder.textView.setText(model.getText());
        Glide.with(context).load(model.getPic()).placeholder(R.drawable.category_img).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick3(model.getText());
            }
        });


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
            imageView = itemView.findViewById(R.id.categoryImg);
            textView = itemView.findViewById(R.id.categoryText);
        }
    }
    public interface ItemClickListener {
        void onItemClick3(String name);
    }
}
