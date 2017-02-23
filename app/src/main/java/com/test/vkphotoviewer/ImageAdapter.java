package com.test.vkphotoviewer;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.view.ViewGroup;

import java.util.ArrayList;

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

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        GridItem item = container.getItem(position);
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setAdjustViewBounds(true);
      //      imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
        } else {
            imageView = (ImageView) convertView;
        }
        //imageView.setImageDrawable(pictures.get(position));
        //imageView = new ImageView(pictures.get(i));
        Picasso.with(mContext)
                .load(item.getSmallSize())
                .into(imageView);
        //imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}
