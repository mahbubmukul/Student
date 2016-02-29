package com.coderstrust.student;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by MAHBUB_CT on 2/3/2016.
 */
public class ViewPagerAdapter extends PagerAdapter {
    // Declare Variables
    Context context;
    String[] big_text;
    String[] small_text;
    int[] img;
    LayoutInflater inflater;

    public ViewPagerAdapter(Context context, String[] big_text, String[] small_text,
                            int[] img) {
        this.context = context;
        this.big_text = big_text;
        this.small_text = small_text;
        this.img = img;
    }

    @Override
    public int getCount() {
        return big_text.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        TextView txt_big;
        TextView txt_small;
        ImageView imgss;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        txt_big = (TextView) itemView.findViewById(R.id.big_msg);
        //txt_small = (TextView) itemView.findViewById(R.id.small_msg);
        imgss = (ImageView) itemView.findViewById(R.id.back_image);

//        // Capture position and set to the TextViews
        txt_big.setText(big_text[position]);
//        txtcountry.setText(country[position]);
        imgss.setImageResource(img[position]);
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}