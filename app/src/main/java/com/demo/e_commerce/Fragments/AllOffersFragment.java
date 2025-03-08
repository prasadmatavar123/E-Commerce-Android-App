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

import com.demo.e_commerce.Adapter.AllOfferAdapter;
import com.demo.e_commerce.Adapter.OfferAdapter;
import com.demo.e_commerce.Model.AllOfferModel;
import com.demo.e_commerce.Model.OfferModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;

public class AllOffersFragment extends Fragment {
    RecyclerView alloffersRecycle;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("All Offers");
        View view = layoutInflater.inflate(R.layout.fragment_all_offers,viewGroup,false);
        alloffersRecycle = view.findViewById(R.id.alloffersRecycle);

        ArrayList<AllOfferModel> list5 = new ArrayList<>();
        list5.add(new AllOfferModel("https://img.freepik.com/free-vector/big-sale-diwali-festival-shiny-web-banner_1017-34312.jpg?t=st=1728286304~exp=1728289904~hmac=9f14a877b86b42baf9d1ffb60d3c692a3491d5bd85a9f1e260292def89231e74&w=1380"));
        list5.add(new AllOfferModel("https://img.freepik.com/free-vector/cultural-diwali-offer-banner-with-discount-details-diya-design_1017-39860.jpg?t=st=1728286356~exp=1728289956~hmac=40d5c926096b5176084c9fbb5e721605c8c40704ada38936ab1f11c12b03267a&w=1380"));
        list5.add(new AllOfferModel("https://img.freepik.com/free-vector/nice-happy-diwali-festival-sale-offer-banner-with-glowing-diya_1017-47040.jpg?t=st=1728286523~exp=1728290123~hmac=81976e46b15d71dd9b4d5d24f337bc3b0f09b761783c276009abbcc8c90f0574&w=1380"));
        list5.add(new AllOfferModel("https://img.freepik.com/free-vector/nice-happy-diwali-festival-sale-offer-banner-with-glowing-diya_1017-47040.jpg?t=st=1728286523~exp=1728290123~hmac=81976e46b15d71dd9b4d5d24f337bc3b0f09b761783c276009abbcc8c90f0574&w=1380"));



        AllOfferAdapter adapter5 = new AllOfferAdapter(getContext(), list5);
        alloffersRecycle.setAdapter(adapter5);

        GridLayoutManager layoutManager5 = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
        alloffersRecycle.setLayoutManager(layoutManager5);
        return view;
    }
}