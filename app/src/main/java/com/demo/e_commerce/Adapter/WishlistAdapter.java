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
import com.demo.e_commerce.Model.WishlistModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.viewHolder>{
    ArrayList<WishlistModel> list;
    Context context;

    public WishlistAdapter(Context context, ArrayList<WishlistModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wishlist_layout,parent,false);
        return new WishlistAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        WishlistModel model = list.get(position);
        holder.textView1.setText(model.getText1());
        holder.textView2.setText(model.getText2());
        holder.textView3.setText(model.getText3());
        Glide.with(context).load(model.getPic()).placeholder(R.drawable.shoes).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1,textView2,textView3;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.wishImg);
            textView1 = itemView.findViewById(R.id.wishText1);
            textView2 = itemView.findViewById(R.id.wishText2);
            textView3 = itemView.findViewById(R.id.wishText3);

        }
    }
}
