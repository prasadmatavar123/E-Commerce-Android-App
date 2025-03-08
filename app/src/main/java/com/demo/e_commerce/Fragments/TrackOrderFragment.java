package com.demo.e_commerce.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.demo.e_commerce.R;

public class TrackOrderFragment extends Fragment {
    TextView trackText1,trackText2;
    ImageView trackImg;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Track Order");
        View view = layoutInflater.inflate(R.layout.fragment_track_order,viewGroup,false);
        trackText1 = view.findViewById(R.id.trackText1);
        trackText2 = view.findViewById(R.id.trackText2);
        trackImg = view.findViewById(R.id.trackImg);

        Bundle bundle1 = this.getArguments();
        String msg1 = bundle1.getString("key");
        String msg2 = bundle1.getString("key2");
        String img1 = bundle1.getString("img");

        Toast.makeText(getContext(), "My"+msg1, Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), "My"+msg2, Toast.LENGTH_SHORT).show();

        trackText1.setText(msg1);
        trackText2.setText(msg2);
        Glide.with(getActivity()).load(img1).placeholder(R.drawable.category_img).into(trackImg);
        return view;
    }
}