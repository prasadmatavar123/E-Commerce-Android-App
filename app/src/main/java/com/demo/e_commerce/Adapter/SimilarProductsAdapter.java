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
import com.demo.e_commerce.Model.SimilarProductsModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;

public class SimilarProductsAdapter extends RecyclerView.Adapter<SimilarProductsAdapter.viewHolder>{
    ArrayList<SimilarProductsModel> list;
    Context context;


    public SimilarProductsAdapter(Context context, ArrayList<SimilarProductsModel> list ) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.similar_products_layout,parent,false);
        return new SimilarProductsAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        SimilarProductsModel model = list.get(position);
        holder.textView1.setText(model.getText1());
        holder.textView2.setText(model.getText2());
        Glide.with(context).load(model.getPic()).placeholder(R.drawable.category_img).into(holder.imageView);
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
            imageView = itemView.findViewById(R.id.similarImg);
            textView1 = itemView.findViewById(R.id.similarText1);
            textView2 = itemView.findViewById(R.id.similarText2);
        }
    }
}
