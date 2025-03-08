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

import com.demo.e_commerce.Adapter.AllFoodAdapter;
import com.demo.e_commerce.Adapter.FoodAdapter;
import com.demo.e_commerce.Model.AllFoodModel;
import com.demo.e_commerce.Model.FoodModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;


public class AllFoodFragment extends Fragment implements AllFoodAdapter.ItemClickListener{
    
    RecyclerView allfoodRecycle;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("All Food");
        View view = layoutInflater.inflate(R.layout.fragment_all_food,viewGroup,false);
        allfoodRecycle = view.findViewById(R.id.allfoodRecycle);

        ArrayList<AllFoodModel> list = new ArrayList<>();
        list.add(new AllFoodModel("https://m.media-amazon.com/images/I/61R4xeXrZkL._SX679_.jpg","Assorted Cookies"," ₹16000"));
        list.add(new AllFoodModel("https://5.imimg.com/data5/VZ/JE/ZK/GLADMIN-12602941/fgzfg-1000x1000.jpg","Bikano Aloo Bhujia","₹150000"));
        list.add(new AllFoodModel("https://m.media-amazon.com/images/I/71bXACE4UBL._SX679_.jpg","Sunfeast Dark Fantasy","₹200000"));
        list.add(new AllFoodModel("https://m.media-amazon.com/images/I/41T7VpvO19L._SX300_SY300_QL70_FMwebp_.jpg","Dairy Milk","₹1500"));
        list.add(new AllFoodModel("https://m.media-amazon.com/images/I/51AlMHIHd6L._SX300_SY300_QL70_FMwebp_.jpg","Haldiram's Nagpur","₹250000"));

        AllFoodAdapter adapter = new AllFoodAdapter(getContext(), list,this);
        allfoodRecycle.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
        allfoodRecycle.setLayoutManager(layoutManager);
        return view;
    }

    @Override
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