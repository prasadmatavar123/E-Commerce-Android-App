package com.demo.e_commerce.Adapter;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.demo.e_commerce.Model.BannerItem;
import com.demo.e_commerce.R;

import java.util.List;
import java.util.Timer;

public class MyCustomPagerAdapter extends PagerAdapter {

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;

    Context context;
    List<BannerItem> bannerDatumList;
    LayoutInflater layoutInflater;

    public MyCustomPagerAdapter(Context context, List<BannerItem> bannerDatumList) {
        this.context = context;
        this.bannerDatumList = bannerDatumList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return bannerDatumList.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.viewpager_layout, container, false);
        ImageView imageView = itemView.findViewById(R.id.myImageView);
        Glide.with(context).load(bannerDatumList.get(position).getmBimg()).placeholder(R.drawable.banner_1).into(imageView);
        container.addView(itemView);

        return itemView;
    }


}
