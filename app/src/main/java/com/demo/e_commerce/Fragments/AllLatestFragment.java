package com.demo.e_commerce.Fragments;

import android.annotation.SuppressLint;
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

import com.demo.e_commerce.Adapter.AllLatestAdapter;
import com.demo.e_commerce.Adapter.LatestProAdapter;
import com.demo.e_commerce.Model.AllLatestModel;
import com.demo.e_commerce.Model.LatestProModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;


public class AllLatestFragment extends Fragment implements AllLatestAdapter.ItemClickListener {
    RecyclerView alllatestRecycle;
    
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("All Latest");
        View view = layoutInflater.inflate(R.layout.fragment_all_latest,viewGroup,false);
        alllatestRecycle = view.findViewById(R.id.alllatestRecycle);

        ArrayList<AllLatestModel> list = new ArrayList<>();
        list.add(new AllLatestModel("https://m.media-amazon.com/images/I/316ArzLeJ2L._SY445_SX342_QL70_FMwebp_.jpg","Apple MacBook Air","₹80,000","120000"));
        list.add(new AllLatestModel("https://images.samsung.com/is/image/samsung/p6pim/sa_en/2401/gallery/sa-en-galaxy-s24-s928-sm-s928bztwmea-539325443?$684_547_JPG$","Samsung Altra S22","₹70,000","₹80,000"));
        list.add(new AllLatestModel("https://m.media-amazon.com/images/I/31x-Xz8TkbL._SX300_SY300_QL70_FMwebp_.jpg","Headphone","₹1200","₹1500"));
        list.add(new AllLatestModel("https://m.media-amazon.com/images/I/81WzIbilc9L._SY741_.jpg","Lymio Men Jeans","₹800 ","₹10,000"));
        list.add(new AllLatestModel("https://m.media-amazon.com/images/I/61+u7BBiaxL._SY695_.jpg","Campus Running Shoes","₹2500","30000"));

        AllLatestAdapter adapter = new AllLatestAdapter(getContext(), list, this);
        alllatestRecycle.setAdapter(adapter);

        GridLayoutManager layoutManager2 = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
        alllatestRecycle.setLayoutManager(layoutManager2);
        
        return view;
    }


//    @Override
//    public void onItemClick2() {
//        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragmentLayout, productDetailsFragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
    public void onItemClick3(String name1,String name2,String name3,String img2) {
        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key",name1);
        bundle.putString("key2",name2);
        bundle.putString("key3",name3);
        bundle.putString("img",img2);
        productDetailsFragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLayout, productDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}