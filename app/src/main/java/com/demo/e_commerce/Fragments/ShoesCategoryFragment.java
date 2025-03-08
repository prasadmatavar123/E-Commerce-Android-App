package com.demo.e_commerce.Fragments;

import static com.demo.e_commerce.R.id.shoesCateRecycle;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.e_commerce.Adapter.AllLatestAdapter;
import com.demo.e_commerce.Adapter.LatestProAdapter;
import com.demo.e_commerce.Adapter.ShoesCategoryAdapter;
import com.demo.e_commerce.Model.AllLatestModel;
import com.demo.e_commerce.Model.LatestProModel;
import com.demo.e_commerce.Model.ShoesCategoryModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;


public class ShoesCategoryFragment extends Fragment implements ShoesCategoryAdapter.ItemClickListener{
    RecyclerView shoesCateRecycle;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Shoes");
        View view = layoutInflater.inflate(R.layout.fragment_shoes_category,viewGroup,false);
        shoesCateRecycle = view.findViewById(R.id.shoesCateRecycle);

        Bundle bundle = this.getArguments();
        String msg = bundle.getString("key");
        //shoesCateText1.setText(msg);
        Toast.makeText(getContext(), "My"+msg, Toast.LENGTH_SHORT).show();

//        ===============For Category use==================
        if (msg.equals("Shoes")){

            ArrayList<ShoesCategoryModel> list = new ArrayList<>();
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/71cflgAolqL._SY695_.jpg","Shoes with Natural Rubber","16000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/61BmPJM6ilL._SY695_.jpg","Sneakers,Dancing Shoes","150000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/614+cjRAPwL._SY695_.jpg","Sneaker Shoes For Men","200000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/61AfIFUBD-L._SY695_.jpg","Sports Shoes for Men","1500"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/61nmvGgSLSL._SY695_.jpg","Sneaker Shoes for Men's","250000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/71qewskXG5L._SY695_.jpg","ASIAN Men's Mexico","200000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/71uiQMMydGL._SY695_.jpg","BRUTON Men Sport Shoes","1500"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/71aFuiEntML._SY695_.jpg","Campus Mens Terminator","250000"));

            ShoesCategoryAdapter adapter = new ShoesCategoryAdapter(getContext(), list,this);
            shoesCateRecycle.setAdapter(adapter);

            GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
            shoesCateRecycle.setLayoutManager(layoutManager);
        } else if(msg.equals("Men's")){

            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Mens");
            ArrayList<ShoesCategoryModel> list = new ArrayList<>();
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/51SLANDR5+L._SX679_.jpg","Allen Solly Men's","16000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/61eKETmdq8L._SX679_.jpg","Beach Summer Shirt","150000"));
            list.add(new ShoesCategoryModel("https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcR_Zb2X14FA8WOCxvpqOGv6_ZNVELYyfrzk-WgeIBjqXCfgQ2tA5dydjpDLA--CDO4rsz8tNgqLkMFsHHYbo0pc2yXUxyogJ4FY1luDET3pfET5xLY9oyC1","Spread Full Sleeves","200000"));
            list.add(new ShoesCategoryModel("https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcScMlbMmoISKcDFRnVED5199S27qkzAX_hFSm_iMLgj4fXq8fjXaMjB4Gte9E7E31F-FURdRHEspsHANwyXjZHguRi_ARG-pG1iua1UphdP","hecks Shirt for Men","1500"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/61BjkCXUeCS._SX679_.jpg"," Sneaker Shoes for Men's","250000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/61FSum9DMHL._SX679_.jpg","ACCOX Men's","250000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/31ln42GDboL.jpg","Pinkmint Men's","250000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/51zkKVzqlcL.jpg","LookMark Men's","250000 "));


            ShoesCategoryAdapter adapter = new ShoesCategoryAdapter(getContext(), list,this);
            shoesCateRecycle.setAdapter(adapter);

            GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
            shoesCateRecycle.setLayoutManager(layoutManager);
        }
        else if(msg.equals("Watch")){

            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Watch");
            ArrayList<ShoesCategoryModel> list = new ArrayList<>();
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/61JItBgSibL._SX679_.jpg","Luminous Watch","16000"));
            list.add(new ShoesCategoryModel("https://justintime.in/cdn/shop/products/A832.jpg?v=1682009386&width=823","Steel Watch A832","150000"));
            list.add(new ShoesCategoryModel("https://justintime.in/cdn/shop/products/1B6FdTj1mozTVKeQ-CDFPyz78RykvANex_d0a9cd00-cde1-47f2-8554-0ab2a036ae6d.jpg?v=1693568725&width=823","Chronograph Watch","200000"));
            list.add(new ShoesCategoryModel("https://justintime.in/cdn/shop/products/G987.jpg?v=1682004516&width=823","G-Shock Men's Carbon","1500"));
            list.add(new ShoesCategoryModel("https://justintime.in/cdn/shop/products/1Su5BhBfYxHcPNPpl7jbt9AwEcDCi8kRw.jpg?v=1686823601&width=823","Enticer Men Analog Stainless","250000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/81kLhCWFSjL._SX679_.jpg","LOUIS DEVIN WT030","250000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/61gGGV2XPyL._SX679_.jpg","REDUX Analog Blue-Grey","250000"));
            list.add(new ShoesCategoryModel("https://dukaan.b-cdn.net/700x700/webp/media/27e4d6af-9bcd-4379-a96c-786b053df6f4.webp","Fire Boltt Smart Watch","250000"));



            ShoesCategoryAdapter adapter = new ShoesCategoryAdapter(getContext(), list,this);
            shoesCateRecycle.setAdapter(adapter);

            GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
            shoesCateRecycle.setLayoutManager(layoutManager);
        }
        else if(msg.equals("Women")){

            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Women");
            ArrayList<ShoesCategoryModel> list = new ArrayList<>();
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/51wQ5f8793L._SX679_.jpg","Kurta And Palazzo","16000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/51CDneheBeL._SY879_.jpg","KOTTY Women's","150000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/817S2-D18WL._SY879_.jpg","Cotton Woven Midi Dress","200000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/61GUaIIL9vL._SX679_.jpg","PATISOL Georgette","1500"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/61bIWVU9reL._SX679_.jpg","Tie Up Long Jumpsuit","250000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/71tF9O0WgwL._SY879_.jpg","Leriya Fashion Women","200000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/415xsbjjE2L._SY879_.jpg"," Western Dress","1500"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/61-vuffz9-L._SY879_.jpg","GRECIILOOKS","250000"));

            ShoesCategoryAdapter adapter = new ShoesCategoryAdapter(getContext(), list,this);
            shoesCateRecycle.setAdapter(adapter);

            GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
            shoesCateRecycle.setLayoutManager(layoutManager);
        }
        else if(msg.equals("Headphones")){

            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Headphones");
            ArrayList<ShoesCategoryModel> list = new ArrayList<>();
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/41wpl0J62eL._SX300_SY300_QL70_FMwebp_.jpg","boAt Rockerz 450 Pro","16000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/31ztpzzaDSL._SX300_SY300_QL70_FMwebp_.jpg","boAt Bluetooth","150000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/31eo70r2NWL._SX300_SY300_QL70_FMwebp_.jpg","Sony WH-CH520, Wireless","200000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/41pcgq-l9ML._SX300_SY300_QL70_FMwebp_.jpg","boAt 425 Bluetooth","1500"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/61u1VALn6JL._SX522_.jpg"," Ear Headphones with Mic","250000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/31riIuaxjmL._SX300_SY300_QL70_FMwebp_.jpg","OnePlus Buds 3 TWS","200000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/41+ZT99O7gL._SY300_SX300_.jpg","boAt Airdopes 141 ANC","1500"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/31pAe23ncfL._SX300_SY300_QL70_FMwebp_.jpg","Bullets Z2 Bluetooth","250000"));
            ShoesCategoryAdapter adapter = new ShoesCategoryAdapter(getContext(), list,this);
            shoesCateRecycle.setAdapter(adapter);

            GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
            shoesCateRecycle.setLayoutManager(layoutManager);
        }
        else if(msg.equals("Beauty")){

            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Beauty");
            ArrayList<ShoesCategoryModel> list = new ArrayList<>();
            list.add(new ShoesCategoryModel("https://wp-resource.s3.ap-south-1.amazonaws.com/wp-content/uploads/2020/06/18163131/Untitled-design-2024-09-02T134710.776.jpg","eclat Vitamin C Serum","16000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/519WVl8bNVL._SX522_.jpg","POND'S Bright Beauty","150000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/41SvY3O3oNL._SX300_SY300_QL70_FMwebp_.jpg","Foundation Compact","200000"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/31O8dmASF6L._SX300_SY300_QL70_FMwebp_.jpg","Maybelline New York","1500"));
            list.add(new ShoesCategoryModel("https://m.media-amazon.com/images/I/41vqh6AkH+L._SY300_SX300_.jpg","Garnier Vitamin C","250000"));
            list.add(new ShoesCategoryModel("https://himalayawellness.in/cdn/shop/files/7599951-1_Himalaya-Moisturizing-Aloe-Vera-Face-Wash_200ml_FOP_1800x1800.jpg?v=1721706809","Face Wash","200000"));
            list.add(new ShoesCategoryModel("https://cdn.tirabeauty.com/v2/billowing-snowflake-434234/tira-p/wrkr/products/pictures/item/free/resize-w:540/1102618/FnfwKkZK6h-1102618_1.jpg","Pilgrim 24K Gold Serum","1500"));
            list.add(new ShoesCategoryModel("https://products.drbatras.com/cdn/shop/products/drbatras-glowing-skin-regime-140112_400x.jpg?v=1701180780","Batra's Glowing Skin","250000"));

            ShoesCategoryAdapter adapter = new ShoesCategoryAdapter(getContext(), list,this);
            shoesCateRecycle.setAdapter(adapter);

            GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
            shoesCateRecycle.setLayoutManager(layoutManager);
        }

        else {
            Toast.makeText(getContext(), "Wrong choice", Toast.LENGTH_SHORT).show();
        }

        return view;
    }
    public void onItemClick2() {
        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLayout, productDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
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