package com.demo.e_commerce.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.e_commerce.R;

public class FAQFragment extends Fragment {
    private TextView faqshow1,faqhide1,faqshow2,faqhide2,faqshow3,faqhide3;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("FAQ");
        View view = layoutInflater.inflate(R.layout.fragment_f_a_q,viewGroup,false);
        faqshow1 = view.findViewById(R.id.faqshow1);
        faqhide1 = view.findViewById(R.id.faqhide1);

        faqshow2 = view.findViewById(R.id.faqshow2);
        faqhide2 = view.findViewById(R.id.faqhide2);

        faqshow3 = view.findViewById(R.id.faqshow3);
        faqhide3 = view.findViewById(R.id.faqhide3);

        faqshow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int isvisible = faqhide1.getVisibility();
                if (isvisible==View.VISIBLE){
                    faqhide1.setVisibility(View.GONE);
                }else {
                    faqhide1.setVisibility(View.VISIBLE);
                }
            }
        });

        faqshow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int isvisible = faqhide2.getVisibility();
                if (isvisible==View.VISIBLE){
                    faqhide2.setVisibility(View.GONE);
                }else {
                    faqhide2.setVisibility(View.VISIBLE);
                }
            }
        });

        faqshow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int isvisible = faqhide3.getVisibility();
                if (isvisible==View.VISIBLE){
                    faqhide3.setVisibility(View.GONE);
                }else {
                    faqhide3.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }
}