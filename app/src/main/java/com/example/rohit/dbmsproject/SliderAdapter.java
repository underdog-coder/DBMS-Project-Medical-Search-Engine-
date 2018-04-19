package com.example.rohit.dbmsproject;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class SliderAdapter extends PagerAdapter {
    private Context context;

    SliderAdapter(Context context){
        this.context = context;
    }

    private int[] image = {R.drawable.eat, R.drawable.pray, R.drawable.work };
    private String [] headings = {"EAT", "SLEEP", "CODE"};
    private String[] messages = {"Lorem ipsum dolor. Sit amet in mollis class eros quis."
            , "Lorem ipsum dolor. Sit amet in mollis class eros quis."
            , "Lorem ipsum dolor. Sit amet in mollis class eros quis."
    };
    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView heading = view.findViewById(R.id.heading);
        TextView message = view.findViewById(R.id.message);
        imageView.setImageResource(image[position]);
        heading.setText(headings[position]);
        message.setText(messages[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
