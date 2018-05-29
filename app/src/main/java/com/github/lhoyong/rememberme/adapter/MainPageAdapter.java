package com.github.lhoyong.rememberme.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.lhoyong.rememberme.R;
import com.github.lhoyong.rememberme.model.mainItem;

import java.util.ArrayList;
import java.util.List;

public class MainPageAdapter extends PagerAdapter {

    private List<mainItem> item;
    private Context context;


    public MainPageAdapter(Context context) {
        this.context = context;
        item = new ArrayList<>();
        dummy();

    }

    private void dummy() {
        for (int i = 0; i < 10; i++) {
            item.add(new mainItem(i, "123", "오늘도 치킨이닭" + String.valueOf(i), 0L));
        }
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.main_item, container, false);
        ImageView imageView = itemView
                .findViewById(R.id.main_item_image);

        TextView message = itemView.findViewById(R.id.main_item_message);
        TextView date = itemView.findViewById(R.id.main_item_date);

        Glide.with(context)
                .load(R.drawable.ic_launcher_background)
                .into(imageView);
        /*Glide.with(context)
                .load(item.get(position).getImage())
                .into(imageView);*/

        message.setText(item.get(position).getMessage());
        date.setText(item.get(position).getDate().toString());

        container.addView(itemView);
        return itemView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void addItem(mainItem e) {
        item.add(e);
        notifyDataSetChanged();
    }

    public void removeItem(mainItem e) {
        item.remove(e);
        notifyDataSetChanged();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View v = (View) object;
        viewPager.removeView(v);
    }
}
