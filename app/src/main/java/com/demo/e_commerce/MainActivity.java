package com.demo.e_commerce;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.demo.e_commerce.Database.DatabaseHelper;
import com.demo.e_commerce.Fragments.AllBrandFragment;
import com.demo.e_commerce.Fragments.FAQFragment;
import com.demo.e_commerce.Fragments.HomeFragment;
import com.demo.e_commerce.Fragments.MyCartFragment;
import com.demo.e_commerce.Fragments.OrderFragment;
import com.demo.e_commerce.Fragments.ReviewProductFragment;
import com.demo.e_commerce.Fragments.UpdateProfileFragment;
import com.demo.e_commerce.Fragments.WishlistFragment;
import com.demo.e_commerce.Model.MyCart;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    DatabaseHelper databaseHelper;
    public static TextView countNumber;
    ImageView countImg;
    RelativeLayout countCart;
    public static MainActivity itemListMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        countNumber = new TextView(this);
        countCart = findViewById(R.id.countCart);
        Toast.makeText(this, "Cart="+databaseHelper.getAllData().getCount(), Toast.LENGTH_SHORT).show();

//        Fragment fragment = new SignupFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.drawerLayout, fragment).addToBackStack("HomePage").commit();

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        countNumber = findViewById(R.id.countNumber);
        countImg = findViewById(R.id.countImg);

        itemListMain = this;

        countImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "My Cart", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, new MyCartFragment()).addToBackStack(null).commit();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

       Cursor res = databaseHelper.getAllData();
        if (res.getCount() == 0) {
            countNumber.setText("0");
        } else {
            countNumber.setText("" + res.getCount());
        }

        if (savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.row_home);
        }

        NavClick();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, new HomeFragment()).commit();
    }
    private void NavClick() {
        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment frag = null;
            switch (item.getItemId()) {

                case R.id.row_home:
                    Toast.makeText(this, "Home Page", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, new HomeFragment()).addToBackStack(null).commit();
                    break;

                case R.id.row_myorder:
                    Toast.makeText(this, "My Orders", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, new OrderFragment()).addToBackStack(null).commit();
                    break;

                case R.id.row_updateprofile:
                    Toast.makeText(this, "Update Profile", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, new UpdateProfileFragment()).addToBackStack(null).commit();
                    break;

                case R.id.row_brand:
                    Toast.makeText(this, "Brand", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, new AllBrandFragment()).addToBackStack(null).commit();
                    break;

                case R.id.row_my_cart:
                    Toast.makeText(this, "My Cart", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, new MyCartFragment()).addToBackStack(null).commit();
                    break;

                case R.id.row_wishlist:
                    Toast.makeText(this, "Wishlist", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, new WishlistFragment()).addToBackStack(null).commit();
                    break;

                case R.id.row_faq:
                    Toast.makeText(this, "FAQ", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, new FAQFragment()).addToBackStack(null).commit();
                    break;

                case R.id.review:
                    Toast.makeText(this, "Review", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, new ReviewProductFragment()).addToBackStack(null).commit();
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        Fragment currFrag = getSupportFragmentManager().findFragmentById(R.id.fragmentLayout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

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
                //txt_countcard.setText("₹" + totalRs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}