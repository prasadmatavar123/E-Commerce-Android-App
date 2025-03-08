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
import com.demo.e_commerce.Model.AllLatestModel;
import com.demo.e_commerce.Model.LatestProModel;

import com.demo.e_commerce.R;

import java.util.ArrayList;

public class AllLatestAdapter extends RecyclerView.Adapter<AllLatestAdapter.viewHolder>{

    ArrayList<AllLatestModel> list;
    Context context;
    AllLatestAdapter.ItemClickListener itemClickListener;


    public AllLatestAdapter(Context context, ArrayList<AllLatestModel> list ,AllLatestAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.list = list;
        this.itemClickListener= itemClickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_latest_layout,parent,false);
        return new AllLatestAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        AllLatestModel model = list.get(position);
        holder.textView1.setText(model.getText1());
        holder.textView2.setText(model.getText2());
        holder.textView3.setText(model.getText3());


        Glide.with(context).load(model.getPic()).placeholder(R.drawable.category_img).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick3(model.getText1(),model.getText2(),model.getText3(), model.getPic());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView1,textView2,textView3,textView4;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.alllatestImg);
            textView1 = itemView.findViewById(R.id.alllatestText1);
            textView2 = itemView.findViewById(R.id.alllatestText2);
            textView3 = itemView.findViewById(R.id.strike);

        }
    }
    public interface ItemClickListener {
        void onItemClick3(String name1,String name2,String name3,String img2);
    }
}
