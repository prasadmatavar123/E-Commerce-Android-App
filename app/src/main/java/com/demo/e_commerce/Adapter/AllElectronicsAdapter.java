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
import com.demo.e_commerce.Model.AllElectronicsModel;
import com.demo.e_commerce.Model.ElectronicsModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;

public class AllElectronicsAdapter extends RecyclerView.Adapter<AllElectronicsAdapter.viewHolder>{
    ArrayList<AllElectronicsModel> list;
    Context context;
    AllElectronicsAdapter.ItemClickListener itemClickListener;

    public AllElectronicsAdapter(Context context, ArrayList<AllElectronicsModel> list ,AllElectronicsAdapter.ItemClickListener itemClickListener) {
        this.context = context;
        this.list = list;
        this.itemClickListener= itemClickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_electronics_layout,parent,false);
        return new AllElectronicsAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        AllElectronicsModel model = list.get(position);
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
            imageView = itemView.findViewById(R.id.allelectronicsImg);
            textView1 = itemView.findViewById(R.id.allelectronicsText1);
            textView2 = itemView.findViewById(R.id.allelectronicsText2);

        }
    }
    public interface ItemClickListener {
        void onItemClick3(String name1,String name2,String img3);
    }
}
