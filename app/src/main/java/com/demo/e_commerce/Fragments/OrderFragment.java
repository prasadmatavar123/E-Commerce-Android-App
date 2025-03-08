package com.demo.e_commerce.Fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.demo.e_commerce.Adapter.AllLatestAdapter;
import com.demo.e_commerce.Adapter.LatestProAdapter;
import com.demo.e_commerce.Adapter.OrderAdapter;
import com.demo.e_commerce.Model.AllLatestModel;
import com.demo.e_commerce.Model.OrderModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;

public class OrderFragment extends Fragment implements OrderAdapter.ItemClickListener{
    RecyclerView orderRecycle;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("My Orders");
        View view = layoutInflater.inflate(R.layout.fragment_order,viewGroup,false);
        orderRecycle = view.findViewById(R.id.orderRecycle);

        ArrayList<OrderModel> list = new ArrayList<>();
        list.add(new OrderModel("https://m.media-amazon.com/images/I/316ArzLeJ2L._SY445_SX342_QL70_FMwebp_.jpg","Apple MacBook Air","Delivery On May 30 2024"));
        list.add(new OrderModel("https://images.samsung.com/is/image/samsung/p6pim/sa_en/2401/gallery/sa-en-galaxy-s24-s928-sm-s928bztwmea-539325443?$684_547_JPG$","Samsung Altra S22","Delivery On Oct 17 2024"));
        list.add(new OrderModel("https://m.media-amazon.com/images/I/31x-Xz8TkbL._SX300_SY300_QL70_FMwebp_.jpg","Headphone","Delivery On Oct 18 2024"));
        list.add(new OrderModel("https://m.media-amazon.com/images/I/81WzIbilc9L._SY741_.jpg","Lymio Men Jeans","Delivery On Oct 19 2024"));
        list.add(new OrderModel("https://m.media-amazon.com/images/I/61+u7BBiaxL._SY695_.jpg","Campus Running Shoes","Delivery On Oct 20 2028"));

        OrderAdapter adapter = new OrderAdapter(getContext(), list,this);
        orderRecycle.setAdapter(adapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        orderRecycle.setLayoutManager(layoutManager2);

        return view;
    }
    public void onItemClick2(String name1,String name2,String img) {
        TrackOrderFragment trackOrderFragment = new TrackOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key",name1);
        bundle.putString("key2",name2);
        bundle.putString("img",img);
        trackOrderFragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLayout, trackOrderFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}