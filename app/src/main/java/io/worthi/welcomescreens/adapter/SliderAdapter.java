package io.worthi.welcomescreens.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import io.worthi.R;

public class SliderAdapter extends PagerAdapter {


    public int[] slide_images = {
            R.drawable.frame,
            R.drawable.frame1,
            R.drawable.frame2,
            R.drawable.frame3
    };
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    // Arrays


    public int[] logo_ = {
            R.drawable.logo,
            R.drawable.logo,
            R.drawable.logo
    };

    public int[] pagenumber= {
            R.string.one,
            R.string.two,
            R.string.three,
            R.string.four
    };

    public int[] slide_descs = {
            R.string.skdetwo,
            R.string.slideone,
            R.string.slidethree,
            R.string.slidefour

    };

    public int[] slide_text = {

            R.string.slideonetext,
            R.string.slidetwotext,
            R.string.slidethreetext,
            R.string.slidefourtext
    };


    @Override
    public int getCount() {
        return slide_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);
        TextView pagenumbertv = view.findViewById(R.id.pagenumbertv);
        ImageView frameimage = view.findViewById(R.id.frameimage);
        TextView heading1 = view.findViewById(R.id.heading1);
        TextView heading2 = view.findViewById(R.id.heading2);


        pagenumbertv.setText(pagenumber[position]);
        frameimage.setImageResource(slide_images[position]);
        heading1.setText(slide_text[position]);
        heading2.setText(slide_descs[position]);



        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);


    }
}