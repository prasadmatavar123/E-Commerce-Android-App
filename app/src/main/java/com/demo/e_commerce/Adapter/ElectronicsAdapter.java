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
import com.demo.e_commerce.R;

import java.util.ArrayList;

public class ElectronicsAdapter extends RecyclerView.Adapter<ElectronicsAdapter.viewHolder>{
    ArrayList<ElectronicsModel> list;
    Context context;
    ElectronicsAdapter.ItemClickListener itemClickListener;

    public ElectronicsAdapter(Context context, ArrayList<ElectronicsModel> list, ItemClickListener itemClickListener) {
        this.context = context;
        this.list = list;
        this.itemClickListener= itemClickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.electronics_layout,parent,false);
        return new ElectronicsAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        ElectronicsModel model = list.get(position);
        holder.textView1.setText(model.getText1());
        holder.textView2.setText("₹"+model.getText2());
        holder.description.setText(model.getDescription());
        Glide.with(context).load(model.getPic()).placeholder(R.drawable.category_img).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick5(model.getText1(),model.getText2(), model.getPic(),model.getDescription());
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
            imageView = itemView.findViewById(R.id.electronicsImg);
            textView1 = itemView.findViewById(R.id.electronicsText1);
            textView2 = itemView.findViewById(R.id.electronicsText2);
            description = itemView.findViewById(R.id.description);

        }
    }
    public interface ItemClickListener {
        void onItemClick5(String name1, String name2, String img, String description);
    }
}
