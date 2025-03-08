package com.demo.e_commerce.Fragments;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.demo.e_commerce.Adapter.BrandAdapter;
import com.demo.e_commerce.Adapter.CategoryAdapter;
import com.demo.e_commerce.Adapter.ElectronicsAdapter;
import com.demo.e_commerce.Adapter.FoodAdapter;
import com.demo.e_commerce.Adapter.LatestProAdapter;
import com.demo.e_commerce.Adapter.MyCustomPagerAdapter;
import com.demo.e_commerce.Adapter.OfferAdapter;
import com.demo.e_commerce.Database.DatabaseHelper;
import com.demo.e_commerce.Model.BannerItem;
import com.demo.e_commerce.Model.BrandModel;
import com.demo.e_commerce.Model.CategoryModel;
import com.demo.e_commerce.Model.ElectronicsModel;
import com.demo.e_commerce.Model.FoodModel;
import com.demo.e_commerce.Model.LatestProModel;
import com.demo.e_commerce.Model.MyCart;
import com.demo.e_commerce.Model.OfferModel;
import com.demo.e_commerce.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment implements LatestProAdapter.ItemClickListener,ElectronicsAdapter.ItemClickListener,FoodAdapter.ItemClickListener,CategoryAdapter.ItemClickListener{
    ViewPager viewPager;
    private TabLayout tabview;
    private ArrayList<BannerItem> bannerDatumList;
    Timer timer;
    RecyclerView categoryRecycle,brandRecycle,latestRecycle,electronicsRecycle,foodRecycle,offerRecycle;
    TextView seeAllBrand,seeAllLatest,seeAllElectronics,seeAllFood,seeAllOffer;
    DatabaseHelper databaseHelper;
    public static HomeFragment itemListFragment;
    private String txt_countcard;

    private LatestProAdapter latestProAdapter;
    private List<LatestProModel> productList;

    private ElectronicsAdapter electronicsAdapter;
    private  List<ElectronicsModel> electronicsModels;

    private FoodAdapter foodAdapter;
    private  List<FoodModel> foodModels;

    LatestProAdapter adapter2;
    ElectronicsAdapter adapter3;
    FoodAdapter adapter4;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");
        View view = layoutInflater.inflate(R.layout.fragment_home,viewGroup,false);
        tabview = view.findViewById(R.id.tabview);
        viewPager = view.findViewById(R.id.viewPager);
        categoryRecycle = view.findViewById(R.id.categoryRecycle);
        brandRecycle = view.findViewById(R.id.brandRecycle);
        latestRecycle = view.findViewById(R.id.latestRecycle);
        electronicsRecycle = view.findViewById(R.id.electronicsRecycle);
        foodRecycle = view.findViewById(R.id.foodRecycle);
        offerRecycle = view.findViewById(R.id.offerRecycle);
        seeAllBrand = view.findViewById(R.id.seeAllBrand);
        seeAllLatest = view.findViewById(R.id.seeAllLatest);
        seeAllElectronics = view.findViewById(R.id.seeAllElectronics);
        seeAllFood = view.findViewById(R.id.seeAllFood);
        seeAllOffer = view.findViewById(R.id.seeAllOffer);

        latestRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        productList = new ArrayList<>();

        electronicsRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        electronicsModels = new ArrayList<>();
        foodRecycle.setLayoutManager(new LinearLayoutManager(getContext()));
        foodModels = new ArrayList<>();

        adapter2 = new LatestProAdapter(getContext(), (ArrayList<LatestProModel>) productList,this);
        latestRecycle.setAdapter(adapter2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        latestRecycle.setLayoutManager(layoutManager2);

        adapter3 = new ElectronicsAdapter(getContext(), (ArrayList<ElectronicsModel>) electronicsModels,this);
        electronicsRecycle.setAdapter(adapter3);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        electronicsRecycle.setLayoutManager(layoutManager3);

        adapter4 = new FoodAdapter(getContext(), (ArrayList<FoodModel>) foodModels,this);
        foodRecycle.setAdapter(adapter4);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        foodRecycle.setLayoutManager(layoutManager4);

        //=============== API Calling For Latest Product Start==============
        String url = "https://fakestoreapi.com/products";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object = response.getJSONObject(i);

                                LatestProModel catItem = new LatestProModel();
                                catItem.setPic(object.getString("image"));
                                catItem.setText1(object.getString("title"));
                                catItem.setText2(object.getString("price"));
                                catItem.setDescription(object.getString("description"));
                                productList.add(catItem);
                                adapter2.notifyDataSetChanged();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(getContext()).add(request);
//     ====================API Calling For Latest Product End================


//     ====================API Calling For Electronics Product Start================
        JsonArrayRequest request1 = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object = response.getJSONObject(i);

                                ElectronicsModel catItem1 = new ElectronicsModel();
                                catItem1.setPic(object.getString("image"));
                                catItem1.setText1(object.getString("title"));
                                catItem1.setText2(object.getString("price"));
                                catItem1.setDescription(object.getString("description"));
                                electronicsModels.add(catItem1);
                                adapter3.notifyDataSetChanged();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(getContext()).add(request1);
//     ====================API Calling For Electronics Product End================


//     ====================API Calling For Food and Nutrition Start================
        JsonArrayRequest request2 = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object = response.getJSONObject(i);
                                FoodModel catItem2 = new FoodModel();
                                catItem2.setPic(object.getString("image"));
                                catItem2.setText1(object.getString("title"));
                                catItem2.setText2(object.getString("price"));
                                catItem2.setDescription(object.getString("description"));
                                foodModels.add(catItem2);
                                adapter4.notifyDataSetChanged();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(getContext()).add(request2);
//     ====================API Calling For Food and Nutrition End================

        itemListFragment = this;
        databaseHelper = new DatabaseHelper(getContext());

        bannerDatumList = new ArrayList<>();
        BannerItem[] bannerItem1 = new BannerItem[]{
                new BannerItem("","https://as1.ftcdn.net/v2/jpg/04/65/46/52/1000_F_465465254_1pN9MGrA831idD6zIBL7q8rnZZpUCQTy.jpg",""),
                new BannerItem("","https://as2.ftcdn.net/v2/jpg/03/21/50/79/1000_F_321507943_Vc5WJ9SbmsCNt91VRePAa8KZFkv4dQkz.jpg",""),
                new BannerItem("","https://as2.ftcdn.net/v2/jpg/06/22/74/79/1000_F_622747997_4s5nw9y2WG3LJyQ5iRF4KRGLbySGRd82.jpg",""),
        };
        bannerDatumList.addAll(Arrays.asList(bannerItem1));
        MyCustomPagerAdapter myCustomPagerAdapter = new MyCustomPagerAdapter(getContext(), bannerDatumList);
        viewPager.setAdapter(myCustomPagerAdapter);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(40, 0, 70, 0);
        viewPager.setPageMargin(20);
        tabview.setupWithViewPager(viewPager, true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable(){
                    @Override
                    public void run() {
                        viewPager.setCurrentItem((viewPager.getCurrentItem()+1)%bannerItem1.length);
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 3000, 3000);

       // ********Category Start ********
        ArrayList<CategoryModel> list = new ArrayList<>();
        list.add(new CategoryModel("https://m.media-amazon.com/images/I/71bfSLiEkpL._SY695_.jpg","Shoes"));
        list.add(new CategoryModel("https://m.media-amazon.com/images/I/71ShBw4g6GL._SX679_.jpg","Men's"));
        list.add(new CategoryModel("https://m.media-amazon.com/images/I/71QoSMBhfVL._SX679_.jpg","Watch"));
        list.add(new CategoryModel("https://m.media-amazon.com/images/I/712naEHTSUL._SY879_.jpg","Women"));
        list.add(new CategoryModel("https://m.media-amazon.com/images/I/31x-Xz8TkbL._SX300_SY300_QL70_FMwebp_.jpg","Headphones"));
        list.add(new CategoryModel("https://m.media-amazon.com/images/I/519WVl8bNVL._SX522_.jpg","Beauty"));

        CategoryAdapter adapter = new CategoryAdapter(getContext(), list,this);
        categoryRecycle.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        categoryRecycle.setLayoutManager(layoutManager);
        //******Category End********

        //******Brand Start********
        ArrayList<BrandModel> list1 = new ArrayList<>();
        list1.add(new BrandModel("https://cdn.shopify.com/s/files/1/0558/6413/1764/files/Panasonic_Logo_Design_History_Evolution_0_1024x1024.jpg?v=1692879988","Panasonic"));
        list1.add(new BrandModel("https://i.pinimg.com/564x/e3/88/95/e38895971d2a346a45b65be9aca5658d.jpg","Adidas"));
        list1.add(new BrandModel("https://discovertemplate.com/wp-content/uploads/2024/03/LG.jpg","LG"));
        list1.add(new BrandModel("https://logowik.com/content/uploads/images/classmates8541.jpg","Classmate"));
        list1.add(new BrandModel("https://i.pinimg.com/564x/0e/7d/19/0e7d19a04dd482e56a436e69854943e2.jpg","Campus"));
        list1.add(new BrandModel("https://www.kimp.io/wp-content/uploads/2023/10/unilever-brand-featured1.png","Unilever"));

        BrandAdapter adapter1 = new BrandAdapter(getContext(), list1);
        brandRecycle.setAdapter(adapter1);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        brandRecycle.setLayoutManager(layoutManager1);
        //******Brand End********

        //******Latest Product Start********
   /*     ArrayList<LatestProModel> list2 = new ArrayList<>();
        list2.add(new LatestProModel("https://m.media-amazon.com/images/I/316ArzLeJ2L._SY445_SX342_QL70_FMwebp_.jpg","Apple MacBook Air","16000"));
        list2.add(new LatestProModel("https://images.samsung.com/is/image/samsung/p6pim/sa_en/2401/gallery/sa-en-galaxy-s24-s928-sm-s928bztwmea-539325443?$684_547_JPG$","Samsung Altra S22","150000"));
        list2.add(new LatestProModel("https://m.media-amazon.com/images/I/31x-Xz8TkbL._SX300_SY300_QL70_FMwebp_.jpg","Headphone","200000"));
        list2.add(new LatestProModel("https://m.media-amazon.com/images/I/81WzIbilc9L._SY741_.jpg","Lymio Men Jeans","1500"));
        list2.add(new LatestProModel("https://m.media-amazon.com/images/I/61+u7BBiaxL._SY695_.jpg","Campus Running Shoes","250000"));

        LatestProAdapter adapter2 = new LatestProAdapter(getContext(), list2,this);
        latestRecycle.setAdapter(adapter2);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        latestRecycle.setLayoutManager(layoutManager2);

        */

        //******Latest Product End********

        //******Electronics Product start********
//        ArrayList<ElectronicsModel> list3 = new ArrayList<>();
//        list3.add(new ElectronicsModel("https://m.media-amazon.com/images/I/41R5KcMjsaS._SX300_SY300_QL70_FMwebp_.jpg","Dell Laptop i5","16000"));
//        list3.add(new ElectronicsModel("https://m.media-amazon.com/images/I/41037bXz-GL._SY445_SX342_QL70_FMwebp_.jpg","Iphone 16 Pro","150000"));
//        list3.add(new ElectronicsModel("https://m.media-amazon.com/images/I/71QoSMBhfVL._SX679_.jpg","Analog White Dial Watch","200000"));
//        list3.add(new ElectronicsModel("https://m.media-amazon.com/images/I/61+pdg8CfmL._SX679_.jpg","LG 242 L 3 Freez","1500"));
//        list3.add(new ElectronicsModel("https://m.media-amazon.com/images/I/61Ka87z2DSL._SX679_.jpg","Lloyd 1.5 Ton 3 AC","250000"));
//
//        ElectronicsAdapter adapter3 = new ElectronicsAdapter(getContext(), list3,this);
//        electronicsRecycle.setAdapter(adapter3);
//
//        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
//        electronicsRecycle.setLayoutManager(layoutManager3);
        //******Electronics Product end********

        //******Food Product start********
//        ArrayList<FoodModel> list4 = new ArrayList<>();
//        list4.add(new FoodModel("https://m.media-amazon.com/images/I/61R4xeXrZkL._SX679_.jpg","Assorted Cookies","16000"));
//        list4.add(new FoodModel("https://5.imimg.com/data5/VZ/JE/ZK/GLADMIN-12602941/fgzfg-1000x1000.jpg","Bikano Aloo Bhujia","150000"));
//        list4.add(new FoodModel("https://m.media-amazon.com/images/I/71bXACE4UBL._SX679_.jpg","Sunfeast Dark Fantasy","200000"));
//        list4.add(new FoodModel("https://m.media-amazon.com/images/I/41T7VpvO19L._SX300_SY300_QL70_FMwebp_.jpg","Dairy Milk","1500"));
//        list4.add(new FoodModel("https://m.media-amazon.com/images/I/51AlMHIHd6L._SX300_SY300_QL70_FMwebp_.jpg","Haldiram's Nagpur","250000"));
//
//        FoodAdapter adapter4 = new FoodAdapter(getContext(), list4,this);
//        foodRecycle.setAdapter(adapter4);
//
//        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
//        foodRecycle.setLayoutManager(layoutManager4);
        //******Food Product end********

        //******Offer start********

        ArrayList<OfferModel> list5 = new ArrayList<>();
        list5.add(new OfferModel("https://img.freepik.com/free-vector/big-sale-diwali-festival-shiny-web-banner_1017-34312.jpg?t=st=1728286304~exp=1728289904~hmac=9f14a877b86b42baf9d1ffb60d3c692a3491d5bd85a9f1e260292def89231e74&w=1380"));
        list5.add(new OfferModel("https://img.freepik.com/free-vector/cultural-diwali-offer-banner-with-discount-details-diya-design_1017-39860.jpg?t=st=1728286356~exp=1728289956~hmac=40d5c926096b5176084c9fbb5e721605c8c40704ada38936ab1f11c12b03267a&w=1380"));
        list5.add(new OfferModel("https://img.freepik.com/free-vector/nice-happy-diwali-festival-sale-offer-banner-with-glowing-diya_1017-47040.jpg?t=st=1728286523~exp=1728290123~hmac=81976e46b15d71dd9b4d5d24f337bc3b0f09b761783c276009abbcc8c90f0574&w=1380"));

        OfferAdapter adapter5 = new OfferAdapter(getContext(), list5);
        offerRecycle.setAdapter(adapter5);

        LinearLayoutManager layoutManager5 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        offerRecycle.setLayoutManager(layoutManager5);
        //******Offer end********

        seeAllBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new AllBrandFragment());
            }
        });



        seeAllLatest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new AllLatestFragment());
            }
        });

        seeAllElectronics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new AllElectronicsFragment());
            }
        });

        seeAllFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new AllFoodFragment());

            }
        });

        seeAllOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new AllOffersFragment());

            }
        });

        return view;
    }
    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

//  ===============For Category==============
    public void onItemClick3(String name) {
        ShoesCategoryFragment shoesCategoryFragment = new ShoesCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key",name);
        bundle.putString("strike",name);
        shoesCategoryFragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLayout, shoesCategoryFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

//  ================For Latest Products===============
    public void onItemClick4(String name1,String name2,String img,String description) {
        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key",name1);
        bundle.putString("key2",name2);
        bundle.putString("description",description);
        bundle.putString("img",img);
        productDetailsFragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLayout, productDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // ==============For Electronics===============
    public void onItemClick5(String name1,String name2,String img,String description) {
        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key",name1);
        bundle.putString("key2",name2);
        bundle.putString("description",description);
        bundle.putString("img",img);
        productDetailsFragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLayout, productDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // ==============For Food Products==================
    public void onItemClick6(String name1,String name2,String img,String description) {
        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key",name1);
        bundle.putString("key2",name2);
        bundle.putString("description",description);
        bundle.putString("img",img);
        productDetailsFragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLayout, productDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //=============start===============
    public void updateItem() {
        try {
            Cursor res = databaseHelper.getAllData();
            if (res.getCount() == 0) {
//                cart.setVisibility(View.GONE);
            } else {
//                cart.setVisibility(View.VISIBLE);

                double totalRs = 0;
                double ress = 0;
                int totalItem = 0;
                while (res.moveToNext()) {
                    MyCart rModel = new MyCart();
                    rModel.setCost(res.getString(5));
                    rModel.setQty(res.getString(6));
                    rModel.setDiscount(res.getInt(7));
                    ress = (Integer.parseInt(res.getString(5)) * rModel.getDiscount()) / 100;
                    ress = Integer.parseInt(res.getString(5)) - ress;
                    double temp = Integer.parseInt(res.getString(6)) * ress;
                    totalItem = totalItem + Integer.parseInt(res.getString(6));
                    totalRs = totalRs + temp;
                }

                //txt_countcard.setText("" + res.getCount()+" Item Added");
                while (res.moveToNext()) {
                    //item_name.setText(res.getString(3)+" ");
                }
                //txtItem.setText(totalItem + " Items");
                //txt_countcard.setText("" + totalRs);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}