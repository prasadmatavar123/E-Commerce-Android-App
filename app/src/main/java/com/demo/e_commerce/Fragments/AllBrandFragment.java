package com.demo.e_commerce.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.e_commerce.Adapter.AllBrandAdapter;
import com.demo.e_commerce.Adapter.BrandAdapter;
import com.demo.e_commerce.Model.AllBrandModel;
import com.demo.e_commerce.Model.BrandModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;


public class AllBrandFragment extends Fragment {
    RecyclerView allbrandRecycle;
    
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("All Brand");
        View view = layoutInflater.inflate(R.layout.fragment_all_brand,viewGroup,false);
        allbrandRecycle = view.findViewById(R.id.allbrandRecycle);

        ArrayList<AllBrandModel> list1 = new ArrayList<>();
        list1.add(new AllBrandModel("https://cdn.shopify.com/s/files/1/0558/6413/1764/files/Panasonic_Logo_Design_History_Evolution_0_1024x1024.jpg?v=1692879988","Panasonic"));
        list1.add(new AllBrandModel("https://i.pinimg.com/564x/e3/88/95/e38895971d2a346a45b65be9aca5658d.jpg","Adidas"));
        list1.add(new AllBrandModel("https://discovertemplate.com/wp-content/uploads/2024/03/LG.jpg","LG"));
        list1.add(new AllBrandModel("https://logowik.com/content/uploads/images/classmates8541.jpg","Classmate"));
        list1.add(new AllBrandModel("https://i.pinimg.com/564x/0e/7d/19/0e7d19a04dd482e56a436e69854943e2.jpg","Campus"));
        list1.add(new AllBrandModel("https://www.kimp.io/wp-content/uploads/2023/10/unilever-brand-featured1.png","Unilever"));

        AllBrandAdapter adapter1 = new AllBrandAdapter(getContext(), list1);
        allbrandRecycle.setAdapter(adapter1);

        GridLayoutManager layoutManager1 = new GridLayoutManager(getContext(),2,LinearLayoutManager.HORIZONTAL,false);
        allbrandRecycle.setLayoutManager(layoutManager1);
        
        return view;
    }
}