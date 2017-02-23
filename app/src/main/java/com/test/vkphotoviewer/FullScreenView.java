package com.test.vkphotoviewer;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class FullScreenView extends AppCompatActivity {
    ViewPager viewPager;
    CustomSwipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_view);

        ArrayList<String> photos = new ArrayList<String>((ArrayList<String>)getIntent().getExtras().get("PHOTOS"));
        ArrayList<String> titles = new ArrayList<String>((ArrayList<String>)getIntent().getExtras().get("TITLES"));
        final int position = (int)getIntent().getExtras().get("POSITION");

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        adapter = new CustomSwipeAdapter(this, photos,titles);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);

    }

}
