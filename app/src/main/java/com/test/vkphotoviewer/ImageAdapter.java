package com.test.vkphotoviewer;

import android.util.DisplayMetrics;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.app.Activity;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ImageContainer container;

    ImageAdapter(Context c, ImageContainer container) {
        this.container = new ImageContainer(container);
        mContext = c;
    }

    public int getCount() {
        return container.length();
    }

    public Object getItem(int position) {
        return container.getItem(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        GridItem item = container.getItem(position);
        ImageView imageView;
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels/3;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
       //     imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new GridView.LayoutParams(width,width));
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.with(mContext)
                .load(item.getSmallSize())
                .into(imageView);
        return imageView;
    }
}
