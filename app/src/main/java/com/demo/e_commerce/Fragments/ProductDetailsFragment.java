package com.demo.e_commerce.Fragments;

import static com.demo.e_commerce.Fragments.HomeFragment.itemListFragment;
import static com.demo.e_commerce.MainActivity.countNumber;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Paint;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.demo.e_commerce.Adapter.SimilarProductsAdapter;
import com.demo.e_commerce.Database.DatabaseHelper;
import com.demo.e_commerce.MainActivity;
import com.demo.e_commerce.Model.MyCart;

import com.demo.e_commerce.Model.ProductModel;
import com.demo.e_commerce.Model.SimilarProductsModel;
import com.demo.e_commerce.R;

import java.util.ArrayList;

public class ProductDetailsFragment extends Fragment {
    private TextView faqshow1;
    TableLayout faqhide1;
    RecyclerView similarRecycle;
    Button buynowBtn,addCart;
    TextView productText1,productText2,strike,DescriptionDetails;
    ImageView productdetailsImg,lvl_addcart;
    LinearLayout customProduct,img_plus,img_mins;
    private DatabaseHelper databaseHelper;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Products Details");
        View view = layoutInflater.inflate(R.layout.fragment_product_details,viewGroup,false);
        faqshow1 = view.findViewById(R.id.faqshow1);
        faqhide1 = view.findViewById(R.id.faqhide1);
        similarRecycle = view.findViewById(R.id.similarRecycle);
        buynowBtn = view.findViewById(R.id.buynowBtn);
        productText1 = view.findViewById(R.id.productText1);
        productText2 = view.findViewById(R.id.productText2);
        DescriptionDetails = view.findViewById(R.id.DescriptionDetails);
        productdetailsImg = view.findViewById(R.id.productdetailsImg);
        addCart = view.findViewById(R.id.addCart);
        customProduct = view.findViewById(R.id.customProduct);
        productdetailsImg = view.findViewById(R.id.productdetailsImg);
        lvl_addcart = view.findViewById(R.id.lvl_addcart);
        img_plus = view.findViewById(R.id.img_plus);
        img_mins = view.findViewById(R.id.img_mins);

        //***** For Strike throught start *********
        strike = view.findViewById(R.id.strike);
        strike.setPaintFlags(strike.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        //***** For Strike throught end **********

//      ================For Latest Product==================
        Bundle bundle = this.getArguments();
        String ProdName = bundle.getString("key");
        String ProdPrice = bundle.getString("key2");
        String ProdImg = bundle.getString("img");
        String description = bundle.getString("description");
//      Toast.makeText(getContext(), "My"+msg, Toast.LENGTH_SHORT).show();
//      Toast.makeText(getContext(), "My"+msg2, Toast.LENGTH_SHORT).show();
        productText1.setText(ProdName);
        productText2.setText(ProdPrice);
        DescriptionDetails.setText(description);

        Glide.with(getActivity()).load(ProdImg).placeholder(R.drawable.category_img).into(productdetailsImg);

        databaseHelper = new DatabaseHelper(getContext());
        Cursor res = databaseHelper.getAllData();
        if (res.getCount() == 0) {

        } else {
            updateItem();
        }

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

//        ======Add to Cart ======
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setJoinPlayrList(customProduct,ProdName,ProdPrice,ProdImg);
                openFragment(new MyCartFragment());

            }
        });

        setJoinPlayrList(customProduct,ProdName,ProdPrice,ProdImg);

        ArrayList<SimilarProductsModel> list = new ArrayList<>();
        list.add(new SimilarProductsModel("https://m.media-amazon.com/images/I/316ArzLeJ2L._SY445_SX342_QL70_FMwebp_.jpg","Campus Running Shoes","16000"));
        list.add(new SimilarProductsModel("https://images.samsung.com/is/image/samsung/p6pim/sa_en/2401/gallery/sa-en-galaxy-s24-s928-sm-s928bztwmea-539325443?$684_547_JPG$","Samsung Altra S22","150000"));
        list.add(new SimilarProductsModel("https://m.media-amazon.com/images/I/31x-Xz8TkbL._SX300_SY300_QL70_FMwebp_.jpg","Headphone","200000"));
        list.add(new SimilarProductsModel("https://m.media-amazon.com/images/I/61+u7BBiaxL._SY695_.jpg","Apple MacBook Air","250000"));

        SimilarProductsAdapter adapter = new SimilarProductsAdapter(getContext(), list);
        similarRecycle.setAdapter(adapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        similarRecycle.setLayoutManager(layoutManager2);

        buynowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new OrderSummaryFragment());
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

    //=============start===============
    @SuppressLint("MissingInflatedId")
    private void setJoinPlayrList(LinearLayout lnrView, String ProdName, String ProdPrice, String ProdImg) {
        lnrView.removeAllViews();
        final int[] count = {0};
        DatabaseHelper helper = new DatabaseHelper(lnrView.getContext());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.custome_prize, null);
        TextView txtcount = view.findViewById(R.id.txtcount);
        LinearLayout lvl_addremove = view.findViewById(R.id.lvl_addremove);
        LinearLayout lvl_addcart = view.findViewById(R.id.lvl_addcart);
        LinearLayout img_mins = view.findViewById(R.id.img_mins);
        LinearLayout img_plus = view.findViewById(R.id.img_plus);


        // Toast.makeText(mContext, ""+price, Toast.LENGTH_SHORT).show();
        //Log.d("price",price);
        MyCart myCart = new MyCart();
        myCart.setPID("1");
        myCart.setImage(ProdImg);
        myCart.setTitle(ProdName);
        myCart.setWeight("");
        myCart.setCost(ProdPrice);
        myCart.setQty("1");
        myCart.setDiscount(Integer.parseInt("10"));

        int qrt = helper.getCard(myCart.getPID(), myCart.getCost());
        if (qrt != -1) {
            count[0] = qrt;
            txtcount.setText("" + count[0]);
            lvl_addremove.setVisibility(View.VISIBLE);
            lvl_addcart.setVisibility(View.GONE);
        } else {
            lvl_addremove.setVisibility(View.GONE);
            lvl_addcart.setVisibility(View.VISIBLE);
        }
        if (ProdPrice.equals("")||ProdPrice.equals("0")){
            lvl_addcart.setVisibility(View.GONE);
            //Toast.makeText(mContext, "hi" , Toast.LENGTH_SHORT).show();
        }
        img_mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count[0] = Integer.parseInt(txtcount.getText().toString());

                count[0] = count[0] - 1;
                if (count[0] <= 0) {
                    txtcount.setText("" + count[0]);
                    lvl_addremove.setVisibility(View.GONE);
                    lvl_addcart.setVisibility(View.VISIBLE);
                    helper.deleteRData(myCart.getPID(), myCart.getCost());

//                    Log.d("MSG",myCart.getID());

                } else {
                    txtcount.setVisibility(View.VISIBLE);
                    txtcount.setText("" + count[0]);
                    myCart.setQty(String.valueOf(count[0]));
                    helper.insertData(myCart);
                }
//                HomeFragment.itemListFragment.updateItem();
                MainActivity.itemListMain.updateItem();
            }
        });

        img_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count[0] = Integer.parseInt(txtcount.getText().toString());
                count[0] = count[0] + 1;
                txtcount.setText("" + count[0]);
                myCart.setQty(String.valueOf(count[0]));
                Log.e("INsert", "--> " + helper.insertData(myCart));
//                HomeFragment.itemListFragment.updateItem();
                MainActivity.itemListMain.updateItem();
            }
        });
        lvl_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lvl_addcart.setVisibility(View.GONE);
                lvl_addremove.setVisibility(View.VISIBLE);
                count[0] = Integer.parseInt(countNumber.getText().toString());
                count[0] = count[0] + 1;
                countNumber.setText("" + count[0]);
                myCart.setQty(String.valueOf(count[0]));
                Log.e("INsert", "--> " + helper.insertData(myCart));
//                HomeFragment.itemListFragment.updateItem();
                MainActivity.itemListMain.updateItem();
                itemListFragment.updateItem();
            }
        });
        lnrView.addView(view);

    }

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
                    ress = (Integer.parseInt(res.getString(5))) / 100;
                    ress = Integer.parseInt(res.getString(5)) - ress;
                    double temp = Integer.parseInt(res.getString(6)) * ress;
                    totalItem = totalItem + Integer.parseInt(res.getString(6));
                    totalRs = totalRs + temp;
                }

                countNumber.setText(res.getCount()+" Item Added");
                while (res.moveToNext()) {
//                    item_name.setText(res.getString(3)+" ");
                }
                //txtItem.setText(totalItem + " Items");
                //txt_countcard.setText("₹" + totalRs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}