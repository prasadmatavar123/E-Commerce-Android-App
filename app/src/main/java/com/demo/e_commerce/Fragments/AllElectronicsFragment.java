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

import com.demo.e_commerce.Adapter.AllElectronicsAdapter;
import com.demo.e_commerce.Adapter.LatestProAdapter;
import com.demo.e_commerce.Model.AllElectronicsModel;
import com.demo.e_commerce.Model.LatestProModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;


public class AllElectronicsFragment extends Fragment implements AllElectronicsAdapter.ItemClickListener{
    RecyclerView allelectronicsRecycle;
    
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("All Electronics");
        View view = layoutInflater.inflate(R.layout.fragment_all_electronics,viewGroup,false);
        allelectronicsRecycle = view.findViewById(R.id.allelectronicsRecycle);

        ArrayList<AllElectronicsModel> list = new ArrayList<>();
        list.add(new AllElectronicsModel("https://m.media-amazon.com/images/I/41R5KcMjsaS._SX300_SY300_QL70_FMwebp_.jpg","Dell Laptop i5"," ₹16000"));
        list.add(new AllElectronicsModel("https://m.media-amazon.com/images/I/41037bXz-GL._SY445_SX342_QL70_FMwebp_.jpg","Iphone 16 Pro","₹150000"));
        list.add(new AllElectronicsModel("https://m.media-amazon.com/images/I/71QoSMBhfVL._SX679_.jpg","Analog White Dial Watch","₹200000"));
        list.add(new AllElectronicsModel("https://m.media-amazon.com/images/I/61+pdg8CfmL._SX679_.jpg","LG 242 L 3 Freez","₹1500"));
        list.add(new AllElectronicsModel("https://m.media-amazon.com/images/I/61Ka87z2DSL._SX679_.jpg","Lloyd 1.5 Ton 3 AC","₹250000"));

        AllElectronicsAdapter adapter = new AllElectronicsAdapter(getContext(), list,this);
        allelectronicsRecycle.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
        allelectronicsRecycle.setLayoutManager(layoutManager);
        
        return view;
    }
    public void onItemClick3(String name1,String name2,String img) {
        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key",name1);
        bundle.putString("key2",name2);
        bundle.putString("img",img);
        productDetailsFragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLayout, productDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}