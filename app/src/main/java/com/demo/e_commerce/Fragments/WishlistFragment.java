package com.demo.e_commerce.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.e_commerce.Adapter.SimilarProductsAdapter;
import com.demo.e_commerce.Adapter.WishlistAdapter;
import com.demo.e_commerce.Model.SimilarProductsModel;
import com.demo.e_commerce.Model.WishlistModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;


public class WishlistFragment extends Fragment {
    RecyclerView wishlistRecycle;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Wishlist");
        View view = layoutInflater.inflate(R.layout.fragment_wishlist,viewGroup,false);
        wishlistRecycle = view.findViewById(R.id.wishlistRecycle);

        ArrayList<WishlistModel> list = new ArrayList<>();
        list.add(new WishlistModel("https://m.media-amazon.com/images/I/71cflgAolqL._SY695_.jpg","BRUTON Shoes for Trendy Shoes","S size , Blue","₹16000 "));
        list.add(new WishlistModel("https://images.samsung.com/is/image/samsung/p6pim/sa_en/2401/gallery/sa-en-galaxy-s24-s928-sm-s928bztwmea-539325443?$684_547_JPG$","Samsung Altra S22","M size , Blue","₹150000"));
        list.add(new WishlistModel("https://m.media-amazon.com/images/I/71xZY5-a1oL._SY879_.jpg","LEOTUDE Men's Regular Fit","M size , Red","₹200000"));

        WishlistAdapter adapter = new WishlistAdapter(getContext(), list);
        wishlistRecycle.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        wishlistRecycle.setLayoutManager(layoutManager);
        return view;
    }
}