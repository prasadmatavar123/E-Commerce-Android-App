package com.demo.e_commerce.Fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.demo.e_commerce.Database.DatabaseHelper;
import com.demo.e_commerce.Model.MyCart;
import com.demo.e_commerce.R;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {
    RecyclerView mycartRecycle;
    Button checkoutBtn;
    private DatabaseHelper databaseHelper;
    private final ArrayList<MyCart> myCarts = new ArrayList<>();
    TextView title,cost;
    private LinearLayout lnrView;
    ImageButton delete_btn;
    LinearLayout myLinear;
    TextView txtCountinue;
    TextView txtEmpty;
    TextView txtItem;
    TextView totalAmount;


    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("My Cart");
        View view = layoutInflater.inflate(R.layout.fragment_my_cart,viewGroup,false);
//        mycartRecycle = view.findViewById(R.id.mycartRecycle);
        checkoutBtn = view.findViewById(R.id.checkoutBtn);
        title = view.findViewById(R.id.title);
        cost = view.findViewById(R.id.cost);
        totalAmount = view.findViewById(R.id.totalAmount);
        delete_btn = view.findViewById(R.id.delete_btn);
        myLinear = view.findViewById(R.id.myLinear);
        databaseHelper = new DatabaseHelper(getContext());
        ArrayList<MyCart> list = new ArrayList<>();
        list.add(new MyCart());
        list.add(new MyCart());
        list.add(new MyCart());

        /*MyCartAdapter adapter = new MyCartAdapter(getContext(), list);
        mycartRecycle.setAdapter(adapter);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        mycartRecycle.setLayoutManager(layoutManager2);*/

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new CheckoutFragment());
            }
        });

    //============= Add to cart =================
        Cursor res = databaseHelper.getAllData();
        if (res.getCount() == 0) {
            txtEmpty.setVisibility(View.VISIBLE);
            txtCountinue.setVisibility(View.GONE);
        }
        while (res.moveToNext()) {
            MyCart rModel = new MyCart();
            Log.d("data",res.getString(5));
            rModel.setImage(res.getString(2));
            rModel.setTitle(res.getString(3));
            rModel.setCost(res.getString(5));
            rModel.setPID(res.getString(1));
            //rModel.setProductQty(res.getString(4));
//            rModel.setCost(res.getString(5));
            rModel.setQty(res.getString(6));
//            rModel.setDiscount(res.getInt(7));
            myCarts.add(rModel);
            Log.d("MSG",res.getString(3));

        }

        myLinear(myLinear, myCarts);

    // ======================================

        return view;
    }



    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
//    ===================================================

    double total = 0;
    private void myLinear(LinearLayout myLinear, List<MyCart> myCarts) {
        myLinear.removeAllViews();
        final int[] count = {0};
        double[] totalAmount = {0};
        DatabaseHelper helper = new DatabaseHelper(myLinear.getContext());
        if (myCarts != null && myCarts.size() > 0) {
            for (int i = 0; i < myCarts.size(); i++) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                MyCart cart = myCarts.get(i);
                View view1 = inflater.inflate(R.layout.my_cart_layout, null);

               ImageView image = view1.findViewById(R.id.image);

                ImageView img_delete = view1.findViewById(R.id.delete_btn);
                TextView txt_title = view1.findViewById(R.id.title);
                TextView txt_price = view1.findViewById(R.id.cost);
                TextView txtcount = view1.findViewById(R.id.txtcount);
               @SuppressLint({"MissingInflatedId", "LocalSuppress"})
               ImageButton img_plus = view1.findViewById(R.id.img_plus);
               @SuppressLint({"MissingInflatedId", "LocalSuppress"})
               ImageButton img_mins = view1.findViewById(R.id.img_mins);
                delete_btn = view1.findViewById(R.id.delete_btn);

                MyCart myCart = new MyCart();
                myCart.setTitle(cart.getTitle());
                myCart.setPID(cart.getPID());
                myCart.setCost(cart.getCost());
                myCart.setImage(cart.getImage());
                myCart.setQty(cart.getQty());
//              myCart.setWeight(cart.getWeight());
//              myCart.setCost(cart.getCost());
//              myCart.setDiscount(cart.getDiscount());

                Glide.with(getActivity()).load(myCart.getImage()).thumbnail(Glide.with(getActivity()).load(R.drawable.category_img)).into(image);
//                double res1 = (Integer.parseInt(cart.getCost()) * myCarts.get(i).getDiscount()) / 100;
//                res1 = Integer.parseInt(cart.getCost()) - res1;
//                ProductPrice.setText("  " + cart.getWeight() + "  ");
//                txt_price.setText("₹" + res1);
                txt_title.setText(myCart.getTitle());
                txt_price.setText(myCart.getCost());
                int qrt = helper.getCard(myCart.getTitle(), myCart.getTitle());
                if (qrt != -1) {
                    count[0] = qrt;
                    txtcount.setText("" + count[0]);
                    txtcount.setVisibility(View.VISIBLE);
                } else {
                    txtcount.setVisibility(View.VISIBLE);
                    img_mins.setVisibility(View.VISIBLE);
                }
                Log.d("product price",myCart.getCost());
                double ress = (Double.parseDouble(myCart.getCost()) / 100) * 1;// myCart.getDiscount();
                ress = Double.parseDouble(myCart.getCost()) - ress;
                double temp = ress * qrt;
                totalAmount[0] = totalAmount[0] + temp;
                img_mins.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        count[0] = Integer.parseInt(txtcount.getText().toString());
                        count[0] = count[0] - 1;
                        if (count[0] <= 0) {
//                            proTypeSession.TypeClear();
                            txtcount.setVisibility(View.INVISIBLE);
                            img_mins.setVisibility(View.INVISIBLE);
                            txtcount.setText("" + count[0]);
                            helper.deleteRData(myCart.getPID(), myCart.getCost());
                            myLinear.removeView(view1);
                            myCarts.remove(cart);
                            totalAmount[0] = totalAmount[0] - Double.parseDouble(myCart.getCost());
                            Toast.makeText(getActivity(), myCart.getTitle() /*+" " + myCart.get() */ + " is Remove", Toast.LENGTH_LONG).show();
                            if (totalAmount[0] == 0) {
                                txtcount.setVisibility(View.GONE);
                            }
                            updateItem();
                        } else {
                            txtcount.setVisibility(View.VISIBLE);
                            txtcount.setText("" + count[0]);
                            myCart.setQty(String.valueOf(count[0]));
                            totalAmount[0] = totalAmount[0] - Double.parseDouble(myCart.getQty());
                            helper.insertData(myCart);
                            updateItem();
                        }
                    }
                });
                img_plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        editor.putString("type", type);
//                        editor.commit();
                        txtcount.setVisibility(View.VISIBLE);
                        img_plus.setVisibility(View.VISIBLE);
                        count[0] = Integer.parseInt(txtcount.getText().toString());
                        totalAmount[0] = totalAmount[0] + Integer.parseInt("1"); //myCart.getQty());
                        count[0] = count[0] + 1;
                        txtcount.setText("" + count[0]);
                        myCart.setQty(String.valueOf(count[0]));
                        helper.insertData(myCart);
                        updateItem();
                    }
                });
                img_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog myDelete = new AlertDialog.Builder(getActivity())
                                .setTitle("Delete")
                                .setMessage("Do you want to Delete")
                                .setIcon(R.drawable.category_img)
                                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Log.d("sdj", "" + whichButton);
                                        dialog.dismiss();
                                        totalAmount[0] = totalAmount[0] - Double.parseDouble(myCart.getCost());
                                        helper.deleteRData(myCart.getPID(), myCart.getCost());
                                        Log.d("delete", String.valueOf(helper.deleteRData(myCart.getPID(), myCart.getCost())));
                                        myCarts.remove(cart);
                                        updateItem();
                                        myLinear.removeView(view1);
                                        //Toast.makeText(getContext(), ""+totalAmount[0], Toast.LENGTH_SHORT).show();
                                        if(totalAmount[0] == 0.0){
//                                            proTypeSession.TypeClear();
                                        }
                                    }
                                })
                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.d("sdj", "" + which);
                                        dialog.dismiss();
                                    }
                                })
                                .create();
                        myDelete.show();
                    }
                });
                myLinear.addView(view1);
            }
        }
        total = totalAmount[0];
        updateItem();
    }

    public void updateItem() {
        Cursor res = databaseHelper.getAllData();
        double totalRs = 0;
        double ress = 0;
        int totalItem = 0;
        if (res.getCount() == 0) {
            txtEmpty.setVisibility(View.VISIBLE);
        }
        while (res.moveToNext()) {
            MyCart rModel = new MyCart();
//          rModel.setCost(res.getString(5));
//          rModel.setQty(res.getString(6));
//          rModel.setDiscount(res.getInt(7));
            ress = (Double.parseDouble(res.getString(5)) * rModel.getDiscount()) / 100;
            ress = Double.parseDouble(res.getString(5)) - ress;
            double temp = Double.parseDouble(res.getString(6)) * ress;
            totalRs = totalRs + temp;
            totalItem = totalItem + Integer.parseInt(res.getString(6));
        }
        total = Double.parseDouble(String.valueOf(totalRs));
//        txtItem.setText(totalItem + " Items");
            totalAmount.setText("₹" + totalRs);
//        HomeActivity.getInstance().setFrameMargin(60);
    }
//=========================================================================
}